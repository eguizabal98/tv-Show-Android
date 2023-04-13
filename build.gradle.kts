plugins {
    id("com.android.application").version("7.4.2").apply(false)
    id("com.android.library").version("7.4.2").apply(false)
    id("org.jetbrains.kotlin.android").version("1.8.0").apply(false)
    id("org.jetbrains.kotlin.jvm").version("1.8.0").apply(false)
    id("org.jetbrains.kotlin.kapt").version("1.8.0").apply(false)
    id("com.squareup.sqldelight").version("1.5.4").apply(false)
    kotlin("plugin.serialization") version "1.8.10"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}