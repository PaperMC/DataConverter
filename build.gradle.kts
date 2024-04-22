plugins {
    `java-library`

    `maven-publish`
    signing
    alias(libs.plugins.nexuspublish)
}

group = "dev.hollowcube"
version = System.getenv("TAG_VERSION") ?: "dev"
description = "Standalone Minecraft data fixes based on Paper DFU rewrite."

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(libs.slf4j)
    implementation(libs.gson)
    implementation(libs.fastutil)
    implementation(libs.datafixerupper)
    implementation(libs.bundles.adventure)

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

// ensure that the encoding is set to UTF-8, no matter what the system default is
// this fixes some edge cases with special characters not displaying correctly
// see http://yodaconditions.net/blog/fix-for-java-file-encoding-problems-with-gradle.html
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    withSourcesJar()
    withJavadocJar()

    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks.test {
    maxHeapSize = "2g"
    useJUnitPlatform()
}

nexusPublishing {
    this.packageGroup.set(project.group.toString())

    repositories.sonatype {
        nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
        snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))

        if (System.getenv("SONATYPE_USERNAME") != null) {
            username.set(System.getenv("SONATYPE_USERNAME"))
            password.set(System.getenv("SONATYPE_PASSWORD"))
        }
    }
}

publishing.publications.create<MavenPublication>("maven") {
    groupId = "dev.hollowcube"
    artifactId = "dataconverter"
    version = project.version.toString()

    from(project.components["java"])

    pom {
        name.set(artifactId)
        description.set(project.description)
        url.set("https://github.com/hollow-cube/dataconverter")

        licenses {
            license {
                name.set("LGPL-3.0")
                url.set("https://github.com/hollow-cube/dataconverter/blob/main/LICENSE")
            }
        }

        developers {
            developer {
                id.set("mworzala")
                name.set("Matt Worzala")
                email.set("matt@hollowcube.dev")
            }
        }

        issueManagement {
            system.set("GitHub")
            url.set("https://github.com/hollow-cube/dataconverter/issues")
        }

        scm {
            connection.set("scm:git:git://github.com/hollow-cube/dataconverter.git")
            developerConnection.set("scm:git:git@github.com:hollow-cube/dataconverter.git")
            url.set("https://github.com/hollow-cube/dataconverter")
            tag.set(System.getenv("TAG_VERSION") ?: "HEAD")
        }

        ciManagement {
            system.set("Github Actions")
            url.set("https://github.com/hollow-cube/dataconverter/actions")
        }
    }
}

signing {
    isRequired = System.getenv("CI") != null

    val privateKey = System.getenv("GPG_PRIVATE_KEY")
    val keyPassphrase = System.getenv()["GPG_PASSPHRASE"]
    useInMemoryPgpKeys(privateKey, keyPassphrase)

    sign(publishing.publications)
}
