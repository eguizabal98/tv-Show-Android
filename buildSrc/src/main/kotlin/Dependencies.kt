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
    const val lifecycle = "2.6.1"
    const val activityCompose = "1.7.0"
    const val coroutines = "1.6.4"
    const val compose = "1.4.0"
    const val hilt = "2.45"
    const val ktor = "2.2.4"
    const val sqlDelight = "1.5.4"

    const val junit = "4.13.2"
    const val androidExt = "1.1.5"
    const val androidEspresso = "3.5.1"
}

object AndroidCommon {
    const val core = "androidx.core:core-ktx:${Versions.kotlin}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object KotlinCommon {
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
}

object Compose {
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val testJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val testManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
}

object DependencyInjection {
    const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val android_test = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
}

object Network {
    const val client = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val okhttp = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
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
}

fun DependencyHandler.kotlinCommonApi() {
    api(KotlinCommon.coroutinesCore)
}

fun DependencyHandler.composeApi() {
    api(Compose.activityCompose)
    api(Compose.ui)
    api(Compose.material)
    api(Compose.preview)
}

fun DependencyHandler.compose() {
    androidTestImplementation(Compose.testJunit)
    debugImplementation(Compose.uiTooling)
    debugImplementation(Compose.testManifest)
}

fun DependencyHandler.hilt() {
    implementation(DependencyInjection.android)
    kapt(DependencyInjection.compiler)
    androidTestImplementation(DependencyInjection.android_test)
    kaptAndroidTest(DependencyInjection.compiler)
    testImplementation(DependencyInjection.android_test)
    kaptTest(DependencyInjection.compiler)
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