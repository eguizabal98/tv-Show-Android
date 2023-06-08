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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        jniLibs {
            excludes += setOf("META-INF/{AL2.0,LGPL2.1}")
        }
        resources {
            excludes += setOf("META-INF/{AL2.0,LGPL2.1}")
        }
    }
    namespace = "com.eem.tvshowandroid"
}

dependencies {

    implementation(project(":KotlinCommon"))
    implementation(project(":AndroidCommon"))
    implementation(project(":domain"))
    // Modules implementation only to Di Injection
    implementation(project(":data"))
    // Ui Modules
    implementation(project(":authentication"))
    implementation(project(":home"))

    splash()
    hilt()
    paging()

    testing()
}

kapt {
    correctErrorTypes = true
}
