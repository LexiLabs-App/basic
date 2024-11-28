# Basic-Images
<img src="../docs/images/basic.png" alt="basic" height="240" align="right"/> 

[Basic-Images](https://basic.lexilabs.app/basic-images) [![Maven Central](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-images?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-images)

A Kotlin Multiplatform library to rapidly add images to any project.

![badge-android](http://img.shields.io/badge/android-full_support-65c663.svg?style=flat)
![badge-ios](http://img.shields.io/badge/ios-full_support-65c663.svg?style=flat)
![badge-mac](http://img.shields.io/badge/macos-full_support-65c663.svg?style=flat)
![badge-watchos](http://img.shields.io/badge/watchos-no_support-red.svg?style=flat)
![badge-tvos](http://img.shields.io/badge/tvos-no_support-red.svg?style=flat)
![badge-nodejs](https://img.shields.io/badge/jsNode-no_support-red.svg?style=flat)
![badge-jsBrowser](https://img.shields.io/badge/jsBrowser-no_support-red.svg?style=flat)
![badge-wasmJsBrowser](https://img.shields.io/badge/wasmJsBrowser-no_support-red.svg?style=flat)
![badge-jvm](http://img.shields.io/badge/jvm-full_support-65c663.svg?style=flat)
![badge-linux](http://img.shields.io/badge/linux-no_support-red.svg?style=flat)
![badge-windows](http://img.shields.io/badge/windows-no_support-red.svg?style=flat)

### How it works
Basic-Images uses platform-specific bitmap libraries to display JPEG, PNG, and WEBP files as a Composable.

## Installation
Add your dependencies from Maven
```toml
# in your 'libs.versions.toml' file
[versions]
kotlin = "2.1.0" # gets the latest version, but MUST BE 2.1.0 OR HIGHER
compose = "+" # gets the latest version
lexilabs-basic = "+" #gets the latest version

[libraries]
lexilabs-basic-images = { module = "app.lexilabs.basic:basic-images", version.ref = "lexilabs-basic"}

[plugins] # make sure you're using the JetBrains plugin to import your composables
jetbrainsCompose = { id = "org.jetbrains.compose", version.ref = "compose" }
compose-compiler = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
```
then include the library in your gradle build
```kotlin
// in your 'shared/build.gradle.kts' file
plugins {
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

sourceSets {
    commonMain.dependencies {
        implementation(libs.lexilabs.basic.images)
    }
}
```

## Usage
```kotlin
// Other dependencies were omitted to focus on the "basics"
import app.lexilabs.basic.images.BasicImage
import app.lexilabs.basic.images.BasicUrl
import app.lexilabs.basic.images.BasicPath

@Composable
fun TestComposable() {
    Column {
        // Loads an image via URL location
        BasicImage(url = BasicUrl("https://picsum.photos/200"))
        // Loads an image via Path location
        BasicImage(path = BasicPath("appLocalDirectory/cacheDirectory/images/exampleImage.jpeg"))
    }
}
```
In case you need it, here's some [additional documentation](https://basic.lexilabs.app/basic-images)