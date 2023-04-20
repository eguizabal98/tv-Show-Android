import org.gradle.api.artifacts.dsl.DependencyHandler

object Apps {
    const val compileSdk = 33
    const val minSdk = 27
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "7.4.2"
    const val kotlinPlugin = "1.8.0"
    const val kotlin = "1.9.0"
    const val kotlinSerialization = "1.5.0"
    const val lifecycle = "2.6.1"
    const val activityCompose = "1.7.0"
    const val coroutines = "1.6.4"
    const val compose = "1.4.0"
    const val compose_material = "1.1.0-rc01"
    const val koin = "3.4.0"
    const val ktor = "2.2.4"
    const val sqlDelight = "1.5.4"
    const val timber = "5.0.1"
    const val hilt = "2.45"

    const val junit = "4.13.2"
    const val androidExt = "1.1.5"
    const val androidEspresso = "3.5.1"
}

object AndroidCommon {
    const val core = "androidx.core:core-ktx:${Versions.kotlin}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object KotlinCommon {
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val kotlinSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}"
}

object Compose {
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.compose_material}"
    const val composeMaterialWindows3 =
        "androidx.compose.material3:material3-window-size-class:${Versions.compose_material}"
    const val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val testJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val testManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
}

object KoinInjection {
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
    const val koinJUnit = "io.insert-koin:koin-test-junit4:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
}

object Network {
    const val client = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val okhttp = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
    const val serializate = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
    const val kotlinxJson = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
    const val clientLogging = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
}

object LocalDB {
    const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
    const val android_driver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
}

object TestAndroidLibs {
    const val ext = "androidx.test.ext:junit:${Versions.androidExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.androidEspresso}"
}

fun DependencyHandler.androidCommonApi() {
    api(AndroidCommon.core)
    api(AndroidCommon.lifecycle)
    api(AndroidCommon.coroutinesAndroid)
    api(AndroidCommon.timber)
}

fun DependencyHandler.kotlinCommonApi() {
    api(KotlinCommon.coroutinesCore)
    api(KotlinCommon.kotlinSerialization)
}

fun DependencyHandler.composeApi() {
    api(Compose.activityCompose)
    api(Compose.ui)
    api(Compose.composeMaterial3)
    api(Compose.composeMaterialWindows3)
    api(Compose.preview)
}

fun DependencyHandler.compose() {
    androidTestImplementation(Compose.testJunit)
    debugImplementation(Compose.uiTooling)
    debugImplementation(Compose.testManifest)
}

fun DependencyHandler.koin() {
    implementation(KoinInjection.koinAndroid)
    implementation(KoinInjection.koinCompose)
    testImplementation(KoinInjection.koinTest)
    testImplementation(KoinInjection.koinJUnit)
}

fun DependencyHandler.koinCore() {
    implementation(KoinInjection.koinCore)
    testImplementation(KoinInjection.koinTest)
    testImplementation(KoinInjection.koinJUnit)
}

fun DependencyHandler.ktor() {
    implementation(Network.client)
    implementation(Network.okhttp)
    implementation(Network.serializate)
    implementation(Network.clientLogging)
    implementation(Network.kotlinxJson)
    implementation(Network.contentNegotiation)
}

fun DependencyHandler.sqlDb() {
    implementation(LocalDB.android_driver)
    implementation(LocalDB.runtime)
}

fun DependencyHandler.testing() {
    testImplementation(TestLibs.junit)
    androidTestImplementation(TestAndroidLibs.ext)
    androidTestImplementation(TestAndroidLibs.espresso)
}

private fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

private fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}

private fun DependencyHandler.api(depName: String) {
    add("api", depName)
}

private fun DependencyHandler.androidTestImplementation(depName: String) {
    add("androidTestImplementation", depName)
}

private fun DependencyHandler.kaptAndroidTest(depName: String) {
    add("kaptAndroidTest", depName)
}

private fun DependencyHandler.testImplementation(depName: String) {
    add("testImplementation", depName)
}

private fun DependencyHandler.kaptTest(depName: String) {
    add("kaptTest", depName)
}

private fun DependencyHandler.debugImplementation(depName: String) {
    add("debugImplementation", depName)
}