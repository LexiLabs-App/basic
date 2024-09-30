rootProject.name = "Basic"
include(":basic-ads")
include(":basic-images")
include(":basic-sound")
include(":basic-logging")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

