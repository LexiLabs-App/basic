# Basic
<img src="basic.png" alt="basic" align="right"/> 

![GitHub License](https://img.shields.io/github/license/lexilabs-app/basic)

A Kotlin Multiplatform library to rapidly add basic features like pictures, logging, and audio to any project in a small and fast way.

> [!CAUTION]
> As of April 10th 2025, this repository has been archived and all Basic libraries have been migrated to their own separate repositories.

## Repositories
* [Basic-Ads](https://github.com/LexiLabs-App/basic-ads) [![MavenCentral](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-ads?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-ads)
* [Basic-Images](https://github.com/LexiLabs-App/basic-images) [![Maven Central](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-images?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-images)
* [Basic-Logging](https://github.com/LexiLabs-App/basic-logging) [![Maven Central](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-logging?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-logging)
* [Basic-Sound](https://github.com/LexiLabs-App/basic-sound) [![Maven Central](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-sound?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-sound)

## Quick Start
Add your dependencies from Maven
```toml
# in your 'gradle/libs.versions.toml' file
[versions]
kotlin = "+" # gets the latest Kotlin version
basic = "+" # gets the latest Basic version

[libraries]
basic-ads = { group = "app.lexilabs.basic", name = "basic-ads", version.ref = "basic" }
basic-images = { group = "app.lexilabs.basic", name = "basic-images", version.ref = "basic" }
basic-logging = { group = "app.lexilabs.basic", name = "basic-logging", version.ref = "basic" }
basic-sound = { group = "app.lexilabs.basic", name = "basic-sound", version.ref = "basic" }

[bundles]
# remove items from bundle as desired
basic = [ "basic-ads", "basic-images", "basic-logging", "basic-sound" ]
```
then include the library in your gradle build
```kotlin
// in your 'shared/build.gradle.kts' or 'composeApp/build.gradle.kts' file
sourceSets {
    commonMain.dependencies {
        implementation(libs.bundles.basic)
    }
}
```