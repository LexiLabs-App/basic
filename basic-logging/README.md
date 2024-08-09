# Basic-Logging
<img src="../docs/images/basic.png" alt="basic" style="float:right; height:240px;"/> 

[Basic-Logging](#basic-logging) [![Maven Central](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-logging?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-logging)

A Kotlin Multiplatform library to rapidly add logging to any project.

![badge-android](http://img.shields.io/badge/android-fully_supported-green.svg?style=flat)
![badge-ios](http://img.shields.io/badge/ios-fully_supported-green.svg?style=flat)
![badge-mac](http://img.shields.io/badge/macos-fully_supported-green.svg?style=flat)
![badge-watchos](http://img.shields.io/badge/watchos-fully_supported-green.svg?style=flat)
![badge-tvos](http://img.shields.io/badge/tvos-fully_supported-green.svg?style=flat)
![badge-nodejs](https://img.shields.io/badge/jsNode-fully_supported-green.svg?style=flat)
![badge-jsBrowser](https://img.shields.io/badge/jsBrowser-fully_supported-green.svg?style=flat)
![badge-wasmJsBrowser](https://img.shields.io/badge/wasmJsBrowser-fully_supported-green.svg?style=flat)
![badge-jvm](http://img.shields.io/badge/jvm-fully_supported-green.svg?style=flat)
![badge-linux](http://img.shields.io/badge/linux-fully_supported-green.svg?style=flat)
![badge-windows](http://img.shields.io/badge/windows-fully_supported-green.svg?style=flat)

### How it works
Basic-Logging uses platform-specific logging libraries to create console logs.
These libraries include Java's Log, Apple's NSLog, and direct console writing for Javascript, WASM, MinGW, Linux, and JVM.

## Installation
Add your dependencies from Maven
```toml
# in your 'libs.versions.toml' file
[versions]
lexilabs-basic = "+" #gets the latest version

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

## Usage

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