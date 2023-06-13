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
    const val lifecycleLiveData = "1.3.2"
    const val activityCompose = "1.7.0"
    const val coroutines = "1.6.4"
    const val compose = "1.4.1"
    const val composeFundation = "1.4.3"
    const val coil = "2.4.0"
    const val compose_material = "1.2.0-alpha02"
    const val compose_navigation = "2.5.3"
    const val koin = "3.4.0"
    const val retrofit = "2.9.0"
    const val okHttp = "4.11.0"
    const val gson = "2.10.1"
    const val room = "2.5.1"
    const val timber = "5.0.1"
    const val hilt = "2.45"
    const val splash = "1.0.0"
    const val browser = "1.5.0"
    const val composePaging = "1.0.0-alpha20"
    const val androidPaging = "3.1.1"
    const val hiltVersion = "2.44"
    const val constraint = "1.0.1"

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
    const val navigation = "androidx.navigation:navigation-compose:${Versions.compose_navigation}"
    const val testJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val testManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val fundation = "androidx.compose.foundation:foundation:${Versions.composeFundation}"
    const val lifeCycle = "androidx.compose.runtime:runtime-livedata:${Versions.lifecycleLiveData}"
    const val constraint = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraint}"
}

object Paging {
    const val pagingCompose = "androidx.paging:paging-compose:${Versions.composePaging}"
    const val paging3Android = "androidx.paging:paging-runtime:${Versions.androidPaging}"
}

object KoinInjection {
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
    const val koinJUnit = "io.insert-koin:koin-test-junit4:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
}

object Hilt {
    const val hiltCore = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltVersion}"
    const val hiltCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
}

object Network {
    const val client = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHtttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
}

object LocalDB {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKaptProcessor = "androidx.room:room-compiler:${Versions.room}"
    const val roomCoroutines = "androidx.room:room-ktx:${Versions.room}"
    const val roomPaging = "androidx.room:room-paging:${Versions.room}"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
}

object TestAndroidLibs {
    const val ext = "androidx.test.ext:junit:${Versions.androidExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.androidEspresso}"
}

object Splash {
    const val splashscreen = "androidx.core:core-splashscreen:${Versions.splash}"
}

object Authentication {
    const val chromeTab = "androidx.browser:browser:${Versions.browser}"
}

fun DependencyHandler.splash() {
    implementation(Splash.splashscreen)
}

fun DependencyHandler.authentication() {
    implementation(Authentication.chromeTab)
}

fun DependencyHandler.androidCommonApi() {
    api(AndroidCommon.core)
    api(AndroidCommon.lifecycle)
    api(AndroidCommon.coroutinesAndroid)
    api(AndroidCommon.timber)
}

fun DependencyHandler.timberAlone() {
    implementation(AndroidCommon.timber)
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
    api(Compose.navigation)
    api(Compose.coil)
    api(Hilt.hiltCompose)
    api(Compose.lifeCycle)
    api(Compose.constraint)
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

fun DependencyHandler.hilt() {
    implementation(Hilt.hiltCore)
    kapt(Hilt.hiltCompiler)
}

fun DependencyHandler.retrofit() {
    implementation(Network.client)
    implementation(Network.okhttp)
    implementation(Network.gson)
    implementation(Network.retrofitGson)
    implementation(Network.okHtttpLogger)
}

fun DependencyHandler.sqlDb() {
    implementation(LocalDB.roomRuntime)
    implementation(LocalDB.roomCoroutines)
    implementation(LocalDB.roomPaging)
    kapt(LocalDB.roomKaptProcessor)
}

fun DependencyHandler.paging() {
    implementation(Paging.pagingCompose)
    implementation(Paging.paging3Android)
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