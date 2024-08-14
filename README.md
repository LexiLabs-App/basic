# Basic
<img src="docs/images/basic.png" alt="basic" height="240" align="right"/> 

![GitHub License](https://img.shields.io/github/license/lexilabs-app/basic)
![GitHub Release Date](https://img.shields.io/github/release-date/lexilabs-app/basic)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.10-7f52ff.svg?style=flat&logo=kotlin)](https://kotlinlang.org)

A Kotlin Multiplatform library to rapidly add basic features like logging and audio to any project in a small and fast way.

![badge-android](http://img.shields.io/badge/android-fully_supported-65c663.svg?style=flat)
![badge-ios](http://img.shields.io/badge/ios-fully_supported-65c663.svg?style=flat)
![badge-mac](http://img.shields.io/badge/macos-fully_supported-65c663.svg?style=flat)
![badge-watchos](http://img.shields.io/badge/watchos-fully_supported-65c663.svg?style=flat)
![badge-tvos](http://img.shields.io/badge/tvos-fully_supported-65c663.svg?style=flat)
![badge-nodejs](https://img.shields.io/badge/jsNode-fully_supported-65c663.svg?style=flat)
![badge-jsBrowser](https://img.shields.io/badge/jsBrowser-fully_supported-65c663.svg?style=flat)
![badge-wasmJsBrowser](https://img.shields.io/badge/wasmJsBrowser-partially_supported-yellow.svg?style=flat)
![badge-jvm](http://img.shields.io/badge/jvm-partially_supported-yellow.svg?style=flat)
![badge-linux](http://img.shields.io/badge/linux-partially_supported-yellow.svg?style=flat)
![badge-windows](http://img.shields.io/badge/windows-partially_supported-yellow.svg?style=flat)

## Documentation
* [Basic-Logging](basic-logging/README.md) [![Maven Central](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-logging?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-logging)
* [Basic-Sound](basic-sound/README.md) [![Maven Central](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-sound?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-sound)

## Quick Start
Add your dependencies from Maven
```toml
# in your 'gradle/libs.versions.toml' file
[versions]
lexilabs-basic = "+" # gets the latest version

[libraries]
lexilabs-basic-logging = { module = "app.lexilabs.basic:basic-logging", version.ref = "lexilabs-basic"}
lexilabs-basic-sound = { module = "app.lexilabs.basic:basic-sound", version.ref = "lexilabs-basic" }
```
then include the library in your gradle build
```kotlin
// in your 'shared/build.gradle.kts' or 'composeApp/build.gradle.kts' file
sourceSets {
    commonMain.dependencies {
        implementation(libs.lexilabs.basic.logging)
        implementation(libs.lexilabs.basic.sound)
    }
}
```