plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.squareup.sqldelight")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.eem.localdata"
    compileSdk = Apps.compileSdk

    defaultConfig {
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":AndroidCommon"))
    implementation(project(":KotlinCommon"))
    implementation(project(":data"))

    koinCore()
    sqlDb()

    testing()
}
