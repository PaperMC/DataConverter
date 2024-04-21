package ca.spottedleaf.dataconverter.minecraft.versions;

import ca.spottedleaf.dataconverter.converters.DataConverter;
import ca.spottedleaf.dataconverter.minecraft.MCVersions;
import ca.spottedleaf.dataconverter.minecraft.datatypes.MCTypeRegistry;
import ca.spottedleaf.dataconverter.types.ListType;
import ca.spottedleaf.dataconverter.types.MapType;
import ca.spottedleaf.dataconverter.types.ObjectType;
import ca.spottedleaf.dataconverter.types.Types;

import java.util.Set;
import java.util.UUID;

public final class V2514 {

    private static final int VERSION = MCVersions.V20W11A + 1;

    private static final Set<String> ABSTRACT_HORSES = Set.of(
            "minecraft:donkey",
            "minecraft:horse",
            "minecraft:llama",
            "minecraft:mule",
            "minecraft:skeleton_horse",
            "minecraft:trader_llama",
            "minecraft:zombie_horse"
    );
    private static final Set<String> TAMEABLE_ANIMALS = Set.of(
            "minecraft:cat",
            "minecraft:parrot",
            "minecraft:wolf"
    );
    private static final Set<String> ANIMALS = Set.of(
            "minecraft:bee",
            "minecraft:chicken",
            "minecraft:cow",
            "minecraft:fox",
            "minecraft:mooshroom",
            "minecraft:ocelot",
            "minecraft:panda",
            "minecraft:pig",
            "minecraft:polar_bear",
            "minecraft:rabbit",
            "minecraft:sheep",
            "minecraft:turtle",
            "minecraft:hoglin"
    );
    private static final Set<String> MOBS = Set.of(
            "minecraft:bat",
            "minecraft:blaze",
            "minecraft:cave_spider",
            "minecraft:cod",
            "minecraft:creeper",
            "minecraft:dolphin",
            "minecraft:drowned",
            "minecraft:elder_guardian",
            "minecraft:ender_dragon",
            "minecraft:enderman",
            "minecraft:endermite",
            "minecraft:evoker",
            "minecraft:ghast",
            "minecraft:giant",
            "minecraft:guardian",
            "minecraft:husk",
            "minecraft:illusioner",
            "minecraft:magma_cube",
            "minecraft:pufferfish",
            "minecraft:zombified_piglin",
            "minecraft:salmon",
            "minecraft:shulker",
            "minecraft:silverfish",
            "minecraft:skeleton",
            "minecraft:slime",
            "minecraft:snow_golem",
            "minecraft:spider",
            "minecraft:squid",
            "minecraft:stray",
            "minecraft:tropical_fish",
            "minecraft:vex",
            "minecraft:villager",
            "minecraft:iron_golem",
            "minecraft:vindicator",
            "minecraft:pillager",
            "minecraft:wandering_trader",
            "minecraft:witch",
            "minecraft:wither",
            "minecraft:wither_skeleton",
            "minecraft:zombie",
            "minecraft:zombie_villager",
            "minecraft:phantom",
            "minecraft:ravager",
            "minecraft:piglin"
    );
    private static final Set<String> LIVING_ENTITIES = Set.of("minecraft:armor_stand");
    private static final Set<String> PROJECTILES = Set.of(
            "minecraft:arrow",
            "minecraft:dragon_fireball",
            "minecraft:firework_rocket",
            "minecraft:fireball",
            "minecraft:llama_spit",
            "minecraft:small_fireball",
            "minecraft:snowball",
            "minecraft:spectral_arrow",
            "minecraft:egg",
            "minecraft:ender_pearl",
            "minecraft:experience_bottle",
            "minecraft:potion",
            "minecraft:trident",
            "minecraft:wither_skull"
    );

    static int[] createUUIDArray(final long most, final long least) {
        return new int[]{
                (int) (most >>> 32),
                (int) most,
                (int) (least >>> 32),
                (int) least
        };
    }

    static int[] createUUIDFromString(final MapType<String> data, final String path) {
        if (data == null) {
            return null;
        }

        final String uuidString = data.getString(path);
        if (uuidString == null) {
            return null;
        }

        try {
            final UUID uuid = UUID.fromString(uuidString);
            return createUUIDArray(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
        } catch (final IllegalArgumentException ignore) {
            return null;
        }
    }

    static int[] createUUIDFromLongs(final MapType<String> data, final String most, final String least) {
        if (data == null) {
            return null;
        }

        final long mostBits = data.getLong(most);
        final long leastBits = data.getLong(least);

        return (mostBits != 0 || leastBits != 0) ? createUUIDArray(mostBits, leastBits) : null;
    }

    static void replaceUUIDString(final MapType<String> data, final String oldPath, final String newPath) {
        final int[] newUUID = createUUIDFromString(data, oldPath);
        if (newUUID != null) {
            data.remove(oldPath);
            data.setInts(newPath, newUUID);
        }
    }

    static void replaceUUIDMLTag(final MapType<String> data, final String oldPath, final String newPath) {
        final int[] uuid = createUUIDFromLongs(data.getMap(oldPath), "M", "L");
        if (uuid != null) {
            data.remove(oldPath);
            data.setInts(newPath, uuid);
        }
    }

    static void replaceUUIDLeastMost(final MapType<String> data, final String prefix, final String newPath) {
        final String mostPath = prefix.concat("Most");
        final String leastPath = prefix.concat("Least");

        final int[] uuid = createUUIDFromLongs(data, mostPath, leastPath);
        if (uuid != null) {
            data.remove(mostPath);
            data.remove(leastPath);
            data.setInts(newPath, uuid);
        }
    }

    private static void updatePiglin(final MapType<String> data) {
        final MapType<String> brain = data.getMap("Brain");
        if (brain == null) {
            return;
        }

        final MapType<String> memories = brain.getMap("memories");
        if (memories == null) {
            return;
        }

        final MapType<String> angryAt = memories.getMap("minecraft:angry_at");

        replaceUUIDString(angryAt, "value", "value");
    }

    private static void updateEvokerFangs(final MapType<String> data) {
        replaceUUIDLeastMost(data, "OwnerUUID", "Owner");
    }

    private static void updateZombieVillager(final MapType<String> data) {
        replaceUUIDLeastMost(data, "ConversionPlayer", "ConversionPlayer");
    }

    private static void updateAreaEffectCloud(final MapType<String> data) {
        replaceUUIDLeastMost(data, "OwnerUUID", "Owner");
    }

    private static void updateShulkerBullet(final MapType<String> data) {
        replaceUUIDMLTag(data, "Owner", "Owner");
        replaceUUIDMLTag(data, "Target", "Target");
    }

    private static void updateItem(final MapType<String> data) {
        replaceUUIDMLTag(data, "Owner", "Owner");
        replaceUUIDMLTag(data, "Thrower", "Thrower");
    }

    private static void updateFox(final MapType<String> data) {
        final ListType trustedUUIDS = data.getList("TrustedUUIDs", ObjectType.MAP);
        if (trustedUUIDS == null) {
            return;
        }

        final ListType newUUIDs = Types.NBT.createEmptyList();
        data.remove("TrustedUUIDs");
        data.setList("Trusted", newUUIDs);

        for (int i = 0, len = trustedUUIDS.size(); i < len; ++i) {
            final MapType<String> uuid = trustedUUIDS.getMap(i);
            final int[] newUUID = createUUIDFromLongs(uuid, "M", "L");
            if (newUUID != null) {
                newUUIDs.addIntArray(newUUID);
            }
        }
    }

    private static void updateHurtBy(final MapType<String> data) {
        replaceUUIDString(data, "HurtBy", "HurtBy");
    }

    private static void updateAnimalOwner(final MapType<String> data) {
        updateAnimal(data);

        replaceUUIDString(data, "OwnerUUID", "Owner");
    }

    private static void updateAnimal(final MapType<String> data) {
        updateMob(data);

        replaceUUIDLeastMost(data, "LoveCause", "LoveCause");
    }

    private static void updateMob(final MapType<String> data) {
        updateLivingEntity(data);

        final MapType<String> leash = data.getMap("Leash");
        if (leash == null) {
            return;
        }

        replaceUUIDLeastMost(leash, "UUID", "UUID");
    }

    private static void updateLivingEntity(final MapType<String> data) {
        final ListType attributes = data.getList("Attributes", ObjectType.MAP);
        if (attributes == null) {
            return;
        }

        for (int i = 0, len = attributes.size(); i < len; ++i) {
            final MapType<String> attribute = attributes.getMap(i);

            final ListType modifiers = attribute.getList("Modifiers", ObjectType.MAP);
            if (modifiers == null) {
                continue;
            }

            for (int k = 0; k < modifiers.size(); ++k) {
                replaceUUIDLeastMost(modifiers.getMap(k), "UUID", "UUID");
            }
        }
    }

    private static void updateProjectile(final MapType<String> data) {
        final Object ownerUUID = data.getGeneric("OwnerUUID");
        if (ownerUUID != null) {
            data.remove("OwnerUUID");
            data.setGeneric("Owner", ownerUUID);
        }
    }

    private static void updateEntityUUID(final MapType<String> data) {
        replaceUUIDLeastMost(data, "UUID", "UUID");
    }

    public static void register() {
        // Entity UUID fixes

        MCTypeRegistry.ENTITY.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateEntityUUID(data);
                return null;
            }
        });

        final DataConverter<MapType<String>, MapType<String>> animalOwnerConverter = new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateAnimalOwner(data);
                return null;
            }
        };
        final DataConverter<MapType<String>, MapType<String>> animalConverter = new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateAnimal(data);
                return null;
            }
        };
        final DataConverter<MapType<String>, MapType<String>> mobConverter = new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateMob(data);
                return null;
            }
        };
        final DataConverter<MapType<String>, MapType<String>> livingEntityConverter = new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateLivingEntity(data);
                return null;
            }
        };
        final DataConverter<MapType<String>, MapType<String>> projectileConverter = new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateProjectile(data);
                return null;
            }
        };
        for (final String id : ABSTRACT_HORSES) {
            MCTypeRegistry.ENTITY.addConverterForId(id, animalOwnerConverter);
        }
        for (final String id : TAMEABLE_ANIMALS) {
            MCTypeRegistry.ENTITY.addConverterForId(id, animalOwnerConverter);
        }
        for (final String id : ANIMALS) {
            MCTypeRegistry.ENTITY.addConverterForId(id, animalConverter);
        }
        for (final String id : MOBS) {
            MCTypeRegistry.ENTITY.addConverterForId(id, mobConverter);
        }
        for (final String id : LIVING_ENTITIES) {
            MCTypeRegistry.ENTITY.addConverterForId(id, livingEntityConverter);
        }
        for (final String id : PROJECTILES) {
            MCTypeRegistry.ENTITY.addConverterForId(id, projectileConverter);
        }


        MCTypeRegistry.ENTITY.addConverterForId("minecraft:bee", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateHurtBy(data);
                return null;
            }
        });
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:zombified_piglin", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateHurtBy(data);
                return null;
            }
        });
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:fox", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateFox(data);
                return null;
            }
        });
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:item", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateItem(data);
                return null;
            }
        });
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:shulker_bullet", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateShulkerBullet(data);
                return null;
            }
        });
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:area_effect_cloud", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateAreaEffectCloud(data);
                return null;
            }
        });
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:zombie_villager", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateZombieVillager(data);
                return null;
            }
        });
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:evoker_fangs", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateEvokerFangs(data);
                return null;
            }
        });
        MCTypeRegistry.ENTITY.addConverterForId("minecraft:piglin", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updatePiglin(data);
                return null;
            }
        });


        // Update TE
        MCTypeRegistry.TILE_ENTITY.addConverterForId("minecraft:conduit", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                replaceUUIDMLTag(data, "target_uuid", "Target");
                return null;
            }
        });
        MCTypeRegistry.TILE_ENTITY.addConverterForId("minecraft:skull", new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final MapType<String> owner = data.getMap("Owner");
                if (owner == null) {
                    return null;
                }

                data.remove("Owner");

                replaceUUIDString(owner, "Id", "Id");

                data.setMap("SkullOwner", owner);

                return null;
            }
        });

        // Player UUID
        MCTypeRegistry.PLAYER.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                updateLivingEntity(data);
                updateEntityUUID(data);

                final MapType<String> rootVehicle = data.getMap("RootVehicle");
                if (rootVehicle == null) {
                    return null;
                }

                replaceUUIDLeastMost(rootVehicle, "Attach", "Attach");

                return null;
            }
        });

        // Level.dat
        MCTypeRegistry.LEVEL.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                replaceUUIDString(data, "WanderingTraderId", "WanderingTraderId");

                final MapType<String> dimensionData = data.getMap("DimensionData");
                if (dimensionData != null) {
                    for (final String key : dimensionData.keys()) {
                        final MapType<String> dimension = dimensionData.getMap(key);

                        final MapType<String> dragonFight = dimension.getMap("DragonFight");
                        if (dragonFight == null) {
                            continue;
                        }

                        replaceUUIDLeastMost(dragonFight, "DragonUUID", "Dragon");
                    }
                }

                final MapType<String> customBossEvents = data.getMap("CustomBossEvents");
                if (customBossEvents != null) {
                    for (final String key : customBossEvents.keys()) {
                        final MapType<String> customBossEvent = customBossEvents.getMap(key);

                        final ListType players = customBossEvent.getList("Players", ObjectType.MAP);
                        if (players == null) {
                            continue;
                        }

                        final ListType newPlayers = Types.NBT.createEmptyList();
                        customBossEvent.setList("Players", newPlayers);

                        for (int i = 0, len = players.size(); i < len; ++i) {
                            final int[] newUUID = createUUIDFromLongs(players.getMap(i), "M", "L");
                            if (newUUID != null) {
                                newPlayers.addIntArray(newUUID);
                            }
                        }
                    }
                }

                return null;
            }
        });

        MCTypeRegistry.SAVED_DATA_RAIDS.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> root, final long sourceVersion, final long toVersion) {
                final MapType<String> data = root.getMap("data");
                if (data == null) {
                    return null;
                }

                final ListType raids = data.getList("Raids", ObjectType.MAP);
                if (raids == null) {
                    return null;
                }

                for (int i = 0, len = raids.size(); i < len; ++i) {
                    final MapType<String> raid = raids.getMap(i);

                    final ListType heros = raid.getList("HeroesOfTheVillage", ObjectType.MAP);

                    if (heros == null) {
                        continue;
                    }

                    final ListType newHeros = Types.NBT.createEmptyList();
                    raid.setList("HeroesOfTheVillage", newHeros);

                    for (int k = 0, klen = heros.size(); k < klen; ++k) {
                        final MapType<String> uuidOld = heros.getMap(i);
                        final int[] uuidNew = createUUIDFromLongs(uuidOld, "UUIDMost", "UUIDLeast");
                        if (uuidNew != null) {
                            newHeros.addIntArray(uuidNew);
                        }
                    }
                }

                return null;
            }
        });

        MCTypeRegistry.ITEM_STACK.addStructureConverter(new DataConverter<>(VERSION) {
            @Override
            public MapType<String> convert(final MapType<String> data, final long sourceVersion, final long toVersion) {
                final MapType<String> tag = data.getMap("tag");
                if (tag == null) {
                    return null;
                }

                updateAttributeModifiers(tag);

                if ("minecraft:player_head".equals(data.getString("id"))) {
                    updateSkullOwner(tag);
                }

                return null;
            }
        });
    }

    private static void updateAttributeModifiers(final MapType<String> tag) {
        final ListType attributes = tag.getList("AttributeModifiers", ObjectType.MAP);
        if (attributes == null) {
            return;
        }

        for (int i = 0, len = attributes.size(); i < len; ++i) {
            replaceUUIDLeastMost(attributes.getMap(i), "UUID", "UUID");
        }
    }

    private static void updateSkullOwner(final MapType<String> tag) {
        replaceUUIDString(tag.getMap("SkullOwner"), "Id", "Id");
    }

    private V2514() {}
}
