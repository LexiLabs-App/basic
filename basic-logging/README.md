# Basic-Logging

A Kotlin Multiplatform library to rapidly add logging to any project.

[Basic-Logging](#basic-logging) [![Maven Central](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-logging?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-logging)

![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat)
![badge-ios](http://img.shields.io/badge/platform-ios-CDCDCD.svg?style=flat)
![badge-mac](http://img.shields.io/badge/platform-macos-111111.svg?style=flat)
![badge-watchos](http://img.shields.io/badge/platform-watchos-C0C0C0.svg?style=flat)
![badge-tvos](http://img.shields.io/badge/platform-tvos-808080.svg?style=flat)
![badge-jvm](http://img.shields.io/badge/platform-jvm-DB413D.svg?style=flat)
![badge-linux](http://img.shields.io/badge/platform-linux-2D3F6C.svg?style=flat)
![badge-windows](http://img.shields.io/badge/platform-windows-4D76CD.svg?style=flat)
![badge-nodejs](https://img.shields.io/badge/platform-jsNode-F8DB5D.svg?style=flat)
![badge-jsBrowser](https://img.shields.io/badge/platform-jsBrowser-F8DB5D.svg?style=flat)
![badge-wasmJsBrowser](https://img.shields.io/badge/platform-wasmJsBrowser-F8DB5D.svg?style=flat)

### How it works
Basic-Logging uses platform-specific logging libraries to create console logs.
These libraries include Java's Log, Apple's NSLog, and direct console writing for Javascript, WASM, MinGW, Linux, and JVM.

## Installation
Add your dependencies from Maven
```toml
# in your 'libs.versions.toml' file
[versions]
lexilabs-basic = "0.1.3"

[libraries]
lexilabs-basic-logging = { module = "app.lexilabs.basic:basic-logging", version.ref = "lexilabs-basic"}
```
then include the library in your gradle build
```kotlin
// in your 'shared/build.gradle.kts' file
sourceSets {
    commonMain.dependencies {
        implementation(libs.lexilabs.basic.logging)
    }
}
```

## At a glance:

```kotlin
import app.lexilabs.basic.logging.Log

val tag = "CurrentModule"
Log.v(tag, "This is a verbose message")
Log.i(tag, "This is an info message")
Log.d(tag, "This is a debug message")
Log.w(tag, "This is a warning message")
Log.e(tag, "This is an error message") 
Log.wtf(tag, "Catastrophe message")
```