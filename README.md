# Basic
<img src="docs/images/basic.png" alt="basic" height="240" align="right"/> 

![GitHub License](https://img.shields.io/github/license/lexilabs-app/basic)
![GitHub Release Date](https://img.shields.io/github/release-date/lexilabs-app/basic)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-7f52ff.svg?style=flat&logo=kotlin)](https://kotlinlang.org)

A Kotlin Multiplatform library to rapidly add basic features like pictures, logging, and audio to any project in a small and fast way.

| Platforms     |          Images          |      Logging       |          Sound           |        Ads         |
|:--------------|:------------------------:|:------------------:|:------------------------:|:------------------:|
| Android       |    :white_check_mark:    | :white_check_mark: |    :white_check_mark:    | :white_check_mark: |
| iOS           |    :white_check_mark:    | :white_check_mark: |    :white_check_mark:    | :white_check_mark: |
| macOS         |    :white_check_mark:    | :white_check_mark: |    :white_check_mark:    |  :no_entry_sign:   |
| watchOS       |     :no_entry_sign:      | :white_check_mark: |    :white_check_mark:    |  :no_entry_sign:   |
| tvOS          | :hourglass_flowing_sand: | :white_check_mark: |    :white_check_mark:    |  :no_entry_sign:   |
| nodeJS        | :hourglass_flowing_sand: | :white_check_mark: |    :white_check_mark:    |  :no_entry_sign:   |
| jsBrowser     | :hourglass_flowing_sand: | :white_check_mark: |    :white_check_mark:    |  :no_entry_sign:   |
| wasmJsBrowser | :hourglass_flowing_sand: | :white_check_mark: |    :white_check_mark:    |  :no_entry_sign:   |
| JVM           |    :white_check_mark:    | :white_check_mark: | :hourglass_flowing_sand: |  :no_entry_sign:   |
| Linux         | :hourglass_flowing_sand: | :white_check_mark: | :hourglass_flowing_sand: |  :no_entry_sign:   |
| Windows       | :hourglass_flowing_sand: | :white_check_mark: | :hourglass_flowing_sand: |  :no_entry_sign:   |

## Documentation
* [Basic-Images](basic-images/README.md) [![Maven Central](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-images?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-images)
* [Basic-Logging](basic-logging/README.md) [![Maven Central](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-logging?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-logging)
* [Basic-Sound](basic-sound/README.md) [![Maven Central](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-sound?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-sound)
* [Basic-Ads](basic-ads/README.md)[![MavenCentral](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-ads?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-ads)

## Quick Start
Add your dependencies from Maven
```toml
# in your 'gradle/libs.versions.toml' file
[versions]
lexilabs-basic = "+" # gets the latest version

[libraries]
lexilabs-basic-images = { module = "app.lexilabs.basic:basic-images", version.ref = "lexilabs-basic" }
lexilabs-basic-logging = { module = "app.lexilabs.basic:basic-logging", version.ref = "lexilabs-basic" }
lexilabs-basic-sound = { module = "app.lexilabs.basic:basic-sound", version.ref = "lexilabs-basic" }
lexilabs-basic-ads = { module = "app.lexilabs.basic:basic-ads", version.ref = "lexilabs-basic" }
```
then include the library in your gradle build
```kotlin
// in your 'shared/build.gradle.kts' or 'composeApp/build.gradle.kts' file
sourceSets {
    commonMain.dependencies {
        implementation(libs.lexilabs.basic.images)
        implementation(libs.lexilabs.basic.logging)
        implementation(libs.lexilabs.basic.sound)
        implementation(libs.lexilabs.basic.ads)
    }
}
```