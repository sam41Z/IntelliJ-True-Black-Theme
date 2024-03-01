import org.jetbrains.intellij.tasks.RunPluginVerifierTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("org.jetbrains.intellij") version "1.17.2"
    id("org.jetbrains.changelog") version "2.2.0"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

repositories {
    mavenCentral()
}

intellij {
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))
    updateSinceUntilBuild.set(false)
}

changelog {
    version.set(properties("platformVersion"))
    path.set("${project.projectDir}/CHANGELOG.md")
    header.set(version)
    itemPrefix.set("-")
    keepUnreleasedSection.set(false)
    groups.set(emptyList())
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    buildSearchableOptions {
        enabled = false
    }

    patchPluginXml {
        version.set(properties("pluginVersion"))

        val description = """
<div>
  <h2>Truely Black Theme</h2>
  <h2>Color Palette 🎨</h2>
  <p>
    <img alt="Color Palette" src="https://raw.githubusercontent.com/ahmed3elshaer/IntelliJ-True-Black-Theme/main/assets/code-color-scheme.png" height="600" width="300" />
  </p>
</div>
        """.trimIndent()

        pluginDescription.set(description)
        changeNotes.set(provider { changelog.getLatest().toHTML() })
    }

    runPluginVerifier {
        ideVersions.set(
                properties("pluginVerifierIdeVersions")
                        .split(",")
                        .map(String::trim)
                        .filter(String::isNotEmpty)
        )
        failureLevel.set(
                listOf(
                        RunPluginVerifierTask.FailureLevel.COMPATIBILITY_PROBLEMS,
                        RunPluginVerifierTask.FailureLevel.INVALID_PLUGIN
                )
        )
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(properties("TRUE_DARK_THEME_PLUGIN_TOKEN"))
    }
}
