plugins {
    id("com.android.application").version("7.4.2").apply(false)
    id("com.android.library").version("7.4.2").apply(false)
    id("org.jetbrains.kotlin.android").version("1.8.0").apply(false)
    id("org.jetbrains.kotlin.jvm").version("1.8.0").apply(false)
    id("org.jetbrains.kotlin.kapt").version("1.8.0").apply(false)
    id("com.squareup.sqldelight").version("1.5.4").apply(false)
    id("io.gitlab.arturbosch.detekt").version("1.23.0-RC1")
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1"
    kotlin("plugin.serialization") version "1.8.10"
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent

    repositories {
        // Required to download KtLint
        mavenCentral()
    }

    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
