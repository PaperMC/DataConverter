plugins {
    id("net.fabricmc.fabric-loom")
    id("maven-publish")
}

val getGitCommit = providers.exec {
    commandLine("git", "rev-parse", "--short=7", "HEAD")
}.standardOutput.asText.map { it.trim() }

version = providers.gradleProperty("mod_version").get() + "+fabric." + getGitCommit.get()
group = providers.gradleProperty("maven_group").get()

base {
    archivesName = providers.gradleProperty("archives_base_name")
}

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:${providers.gradleProperty("minecraft_version").get()}")
    implementation("net.fabricmc:fabric-loader:${providers.gradleProperty("loader_version").get()}")
}

tasks.processResources {
    inputs.property("version", version)

    filesMatching("fabric.mod.json") {
        expand(
            "version" to version,
            "minecraft_version" to providers.gradleProperty("minecraft_version").get(),
            "loader_version" to providers.gradleProperty("loader_version").get()
        )
    }
}

loom {
    accessWidenerPath = file("src/main/resources/dataconverter.accesswidener")
}

// make build reproducible
tasks.withType<AbstractArchiveTask>().configureEach {
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 25
}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

tasks.jar {
    inputs.property("archivesName", base.archivesName)

    from("LICENSE") {
        rename { "${it}_${base.archivesName.get()}" }
    }
}

// configure the maven publication
publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            artifactId = base.archivesName.get()
            from(components["java"])
        }
    }

    // See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
    repositories {
        // Add repositories to publish to here.
        // Notice: This block does NOT have the same function as the block in the top level.
        // The repositories here will be used for publishing your artifact, not for
        // retrieving dependencies.
    }
}
