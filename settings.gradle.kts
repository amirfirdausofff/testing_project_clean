pluginManagement {
    repositories {
        google()  // For Android projects
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        // The following repositories will be used for dependency resolution
        google()
        maven("https://maven.google.com")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://jitpack.io")
        mavenCentral()
    }
}

rootProject.name = "Testing Project"
include(":app")
 