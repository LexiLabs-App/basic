# Basic
<img src="docs/images/basic.png" alt="basic" height="240" align="right"/> 

A Kotlin Multiplatform library to rapidly add basic features like logging and audio to any project in a small and fast way.

[![Build](https://github.com/xxfast/KStore/actions/workflows/build.yml/badge.svg)](https://github.com/xxfast/KStore/actions/workflows/build.yml)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.10-7f52ff.svg?style=flat&logo=kotlin)](https://kotlinlang.org)

![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat)
![badge-ios](http://img.shields.io/badge/platform-ios-CDCDCD.svg?style=flat)
![badge-mac](http://img.shields.io/badge/platform-macos-111111.svg?style=flat)
![badge-watchos](http://img.shields.io/badge/platform-watchos-C0C0C0.svg?style=flat)
![badge-tvos](http://img.shields.io/badge/platform-tvos-808080.svg?style=flat)
![badge-nodejs](https://img.shields.io/badge/platform-jsNode-F8DB5D.svg?style=flat)
![badge-jsBrowser](https://img.shields.io/badge/platform-jsBrowser-F8DB5D.svg?style=flat)
![badge-wasmJsBrowser](https://img.shields.io/badge/platform-wasmJsBrowser-F8DB5D.svg?style=flat)

## Documentation
* [Basic-Logging](basic-logging/README.md) [![Maven Central](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-logging?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-logging)
* [Basic-Sound](basic-sound/README.md) [![Maven Central](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-sound?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-sound)

## Quick Start
Add your dependencies from Maven
```toml
# in your 'gradle/libs.versions.toml' file
[versions]
lexilabs-basic = "0.1.3"

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