# Basic
<img src="docs/images/basic.png" alt="basic" height="240" align="right"/> 

![GitHub License](https://img.shields.io/github/license/lexilabs-app/basic)
![GitHub Release Date](https://img.shields.io/github/release-date/lexilabs-app/basic)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.10-7f52ff.svg?style=flat&logo=kotlin)](https://kotlinlang.org)

A Kotlin Multiplatform library to rapidly add basic features like logging and audio to any project in a small and fast way.

## Current KMP Support

| Operating System | Basic-Images | Basic-Logging | Basic-Sound |
|------------------|--------------|---------------|-------------|
| Android          | - [x]        | - [x]         | - [x]       |
| iOS              | - [x]        | - [x]         | - [x]       |
| macOS            | - [x]        | - [x]         | - [x]       |
| watchOS          |              | - [x]         | - [x]       |
| tvOS             |              | - [x]         | - [x]       |
| nodeJS           |              | - [x]         | - [x]       |
| jsBrowser        |              | - [x]         | - [x]       |
| wasmJsBrowser    |              | - [x]         |             |
| JVM              |              | - [x]         |             |
| Linux            |              | - [x]         |             |
| Windows          |              | - [x]         |             |

## Documentation
* [Basic-Images](basic-images/README.md) [![Maven Central](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-images?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-images)
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