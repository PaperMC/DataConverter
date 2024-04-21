package ca.spottedleaf.dataconverter.util;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContextBuilder;
import com.mojang.brigadier.context.ParsedArgument;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.serialization.Lifecycle;
import it.unimi.dsi.fastutil.Pair;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.commands.ExecuteCommand;
import net.minecraft.server.commands.ReturnCommand;
import net.minecraft.tags.TagKey;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public final class CommandArgumentUpgrader {
	private final CommandDispatcher<CommandSourceStack> dispatcher;
	private final CommandBuildContext context;
	private final CommandSourceStack source;

	public CommandArgumentUpgrader(final int functionPermissionLevel) {
		this(
				new Commands(Commands.CommandSelection.DEDICATED, makeDummyContext()).getDispatcher(),
				functionPermissionLevel
		);
	}

	private CommandArgumentUpgrader(
			final CommandDispatcher<CommandSourceStack> dispatcher,
			final int functionPermissionLevel
	) {
		final CommandBuildContext context = makeDummyContext();
		this.dispatcher = new CommandDispatcher<>();
		this.context = context;
		final List<CommandNode<CommandSourceStack>> aliases = new ArrayList<>();
		for (final CommandNode<CommandSourceStack> child : dispatcher.getRoot().getChildren()) {
			final CopyResult result = this.copyCommand(this.dispatcher.getRoot(), child, null);
			if (result.replaced()) {
				this.dispatcher.getRoot().addChild(result.root);
			}
			aliases.addAll(result.aliases);
		}
		aliases.forEach(redirectNode -> {
			final CommandNode<CommandSourceStack> toNode = this.dispatcher.getRoot()
					.getChild(redirectNode.getRedirect().getName());
			if (toNode != null) {
				this.dispatcher.getRoot().addChild(
						new LiteralCommandNode<>(
								redirectNode.getName(),
								null,
								null,
								toNode,
								redirectNode.getRedirectModifier(),
								redirectNode.isFork()
						)
				);
			}
		});
		ExecuteCommand.register(this.dispatcher, context);
		ReturnCommand.register(this.dispatcher);
		// This looks weird, but it's what vanilla does when loading functions for datapacks
		this.source = new CommandSourceStack(
				CommandSource.NULL,
				Vec3.ZERO,
				Vec2.ZERO,
				null,
				functionPermissionLevel,
				"",
				CommonComponents.EMPTY,
				null,
				null
		);
	}

	private static CommandBuildContext makeDummyContext() {
		return Commands.createValidationContext(
				new HolderLookup.Provider() {

					@Override
					public Stream<ResourceKey<? extends Registry<?>>> listRegistries() {
						return Stream.of();
					}

					@Override
					public <T> Optional<HolderLookup.RegistryLookup<T>> lookup(
							final ResourceKey<? extends Registry<? extends T>> registryRef
					) {
						return Optional.of(new HolderLookup.RegistryLookup<T>() {
							@Override
							public ResourceKey<? extends Registry<? extends T>> key() {
								return registryRef;
							}

							@Override
							public Lifecycle registryLifecycle() {
								return Lifecycle.stable();
							}

							@Override
							public Stream<Holder.Reference<T>> listElements() {
								return Stream.of();
							}

							@Override
							public Stream<HolderSet.Named<T>> listTags() {
								return Stream.of();
							}

							@Override
							public Optional<Holder.Reference<T>> get(final ResourceKey<T> key) {
								return Optional.of(Holder.Reference.createStandAlone(this, key));
							}

							@Override
							public Optional<HolderSet.Named<T>> get(final TagKey<T> tag) {
								return Optional.of(HolderSet.emptyNamed(this, tag));
							}
						});
					}
				}
		);
	}

	// important: leadingSlash should not just be the result of a startsWith on command,
	// it should reflect whether the command use is in a place that will skip a leading slash when parsing
	public String upgradeCommandArguments(final String command, final boolean leadingSlash) {
		final StringReader reader = new StringReader(command);
		if (leadingSlash && reader.peek() == '/') {
			reader.skip();
		}
		final ParseResults<CommandSourceStack> parseResult = this.dispatcher.parse(reader, this.source);
		if (!parseResult.getExceptions().isEmpty()) {
			return command;
		}
		final Map<StringRange, String> replacements = new LinkedHashMap<>();
		final List<Pair<String, ParsedArgument<CommandSourceStack, ?>>> mergedArguments = new ArrayList<>();
		addArguments(mergedArguments, parseResult.getContext());
		mergedArguments.forEach(pair -> {
			if (pair.value().getResult() instanceof UpgradedArgument upgraded) {
				replacements.put(pair.value().getRange(), upgraded.upgraded());
			}
		});
		String upgradedCommand = command;
		while (!replacements.isEmpty()) {
			final Map.Entry<StringRange, String> next = replacements.entrySet().iterator().next();
			replacements.remove(next.getKey());
			upgradedCommand = upgradedCommand.substring(0, next.getKey().getStart()) + next.getValue() + upgradedCommand.substring(next.getKey().getEnd());
			// Update the offsets for the remaining replacements
			final int diff = next.getValue().length() - next.getKey().getLength();
			final Map<StringRange, String> replacementsCopy = new LinkedHashMap<>(replacements);
			replacements.clear();
			replacementsCopy.forEach((range, value) -> {
				replacements.put(new StringRange(range.getStart() + diff, range.getEnd() + diff), value);
			});
		}
		return upgradedCommand;
	}

	private static void addArguments(
			final List<Pair<String, ParsedArgument<CommandSourceStack, ?>>> mergedArguments,
			final @Nullable CommandContextBuilder<CommandSourceStack> context
	) {
		if (context == null) {
			return;
		}
		context.getArguments().forEach((name, argument) -> mergedArguments.add(Pair.of(name, argument)));
		addArguments(mergedArguments, context.getChild());
	}

	private record UpgradedArgument(String upgraded) {}

	private static final class ItemParser_1_20_4 implements ArgumentType<UpgradedArgument> {
		@Override
		public UpgradedArgument parse(final StringReader reader) throws CommandSyntaxException {
			return new UpgradedArgument("upgraded_item_input{" + reader.readString() + "}");
		}
	}

	private ArgumentType<?> replaceArgumentType(final CommandBuildContext ctx, final ArgumentType<?> type) {
		if (type instanceof ItemArgument) {
			return new ItemParser_1_20_4();
		}
		return type;
	}

	record CopyResult(
			CommandNode<CommandSourceStack> root,
			boolean replaced,
			List<CommandNode<CommandSourceStack>> aliases
	) {
		CopyResult replacedResult() {
			if (this.replaced) {
				return this;
			}
			return new CopyResult(this.root, true, new ArrayList<>(this.aliases));
		}
	}

	private CopyResult copyCommand(
			final CommandNode<CommandSourceStack> parent,
			final CommandNode<CommandSourceStack> node,
			@Nullable CopyResult result
	) {
		final CommandNode<CommandSourceStack> copy;
		final boolean replaced;
		if (node instanceof LiteralCommandNode<?> literal) {
			if (node.getName().equals("execute") || node.getName().equals("return")) {
				return new CopyResult(parent, false, new ArrayList<>());
			}
			if (node.getRedirect() != null) {
				if (result != null) {
					throw new IllegalStateException("Cannot handle non-root redirects");
				}
				final List<CommandNode<CommandSourceStack>> aliases = new ArrayList<>();
				aliases.add(node);
				return new CopyResult(parent, false, aliases);
			}
			copy = new LiteralCommandNode<>(
					literal.getLiteral(),
					node.getCommand(),
					node.getRequirement(),
					null,
					node.getRedirectModifier(),
					node.isFork()
			);
			replaced = false;
		} else if (node instanceof ArgumentCommandNode<?, ?>) {
			final ArgumentCommandNode<CommandSourceStack, ?> argument =
					(ArgumentCommandNode<CommandSourceStack, ?>) node;
			final ArgumentType<?> replacedType = this.replaceArgumentType(this.context, argument.getType());
			replaced = replacedType != argument.getType();
			copy = new ArgumentCommandNode<>(
					node.getName(),
					replacedType,
					node.getCommand(),
					node.getRequirement(),
					null,
					node.getRedirectModifier(),
					node.isFork(),
					argument.getCustomSuggestions()
			);
		} else {
			throw new UnsupportedOperationException();
		}
		if (result == null) {
			result = new CopyResult(copy, false, new ArrayList<>());
		}
		if (replaced) {
			result = result.replacedResult();
		}
		for (final CommandNode<CommandSourceStack> child : node.getChildren()) {
			result = this.copyCommand(copy, child, result);
		}
		if (parent != this.dispatcher.getRoot()) {
			parent.addChild(copy);
		}
		return result;
	}
}
