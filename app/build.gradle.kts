plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdkVersion(Apps.compileSdk)

    defaultConfig {
        applicationId = "com.eem.tvshowandroid"
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
        versionCode = Apps.versionCode
        versionName = Apps.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        exclude("META-INF/{AL2.0,LGPL2.1}")
    }
    namespace = "com.eem.tvshowandroid"
}

dependencies {

    implementation(project(":KotlinCommon"))
    implementation(project(":AndroidCommon"))

    hilt()

    testing()
}

kapt {
    correctErrorTypes = true
}