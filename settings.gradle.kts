pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TvShowAndroid"
include("app")
include("KotlinCommon")
include("AndroidCommon")
include(":domain")
include(":data")
include(":authentication")
include(":home")
