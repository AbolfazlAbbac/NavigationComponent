pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io") }

    }
}

rootProject.name = "NavigationComponent"
include(":app")
include(":coroutines")
include(":dagger-hilt")
include(":dagger-hilt_practice")
include(":viewmodel")
include(":livedata")
include(":datastore")
include(":project")
