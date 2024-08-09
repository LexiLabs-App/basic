# Basic-Sound
<img src="../docs/images/basic.png" alt="basic" style="text-align:right;height:240px;"/> 

[![Basic-Sound](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-sound?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-sound)

A Kotlin Multiplatform library to rapidly integrate audio across all your Kotlin Multiplatform apps.
Currently, this library only ingests URLs; objects and files on the local path are not yet supported.

![badge-android](http://img.shields.io/badge/android-fully_supported-green.svg?style=flat)
![badge-ios](http://img.shields.io/badge/ios-fully_supported-green.svg?style=flat)
![badge-mac](http://img.shields.io/badge/macos-fully_supported-green.svg?style=flat)
![badge-watchos](http://img.shields.io/badge/watchos-fully_supported-green.svg?style=flat)
![badge-tvos](http://img.shields.io/badge/tvos-fully_supported-green.svg?style=flat)
![badge-nodejs](https://img.shields.io/badge/jsNode-fully_supported-green.svg?style=flat)
![badge-jsBrowser](https://img.shields.io/badge/jsBrowser-fully_supported-green.svg?style=flat)
![badge-wasmJsBrowser](https://img.shields.io/badge/wasmJsBrowser-not_supported-red.svg?style=flat)
![badge-jvm](http://img.shields.io/badge/jvm-not_supported-red.svg?style=flat)
![badge-linux](http://img.shields.io/badge/linux-not_supported-red.svg?style=flat)
![badge-windows](http://img.shields.io/badge/windows-not_supported-red.svg?style=flat)

## Supported Filetypes
| Format   | Android | iOS | javascript |                                                                      File / Container Types                                                                       |
|----------|:-------:|:---:|:----------:|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| AAC LC   |   YES   | YES |     NO     | 3GPP (.3gp) MPEG-4 (.mp4, .m4a) ADTS raw AAC (.aac, decode in Android 3.1+, encode in Android 4.0+, ADIF not supported) MPEG-TS (.ts, not seekable, Android 3.0+) |
| AMR-NB   |   YES   | NO  |     NO     |                                                                      3GPP (.3gp) AMR (.amr)                                                                       |
| FLAC     |   YES   | NO  |     NO     |                                                           FLAC (.flac) MPEG-4 (.mp4, .m4a, Android 10+)                                                           |
| MIDI     |   YES   | NO  |     NO     |                                        Type 0 and 1 (.mid, .xmf, .mxmf) RTTTL/RTX (.rtttl, .rtx) OTA (.ota) iMelody (.imy)                                        |
| MP3      |   YES   | YES |    YES     |                                             MP3 (.mp3) MPEG-4 (.mp4, .m4a, Android 10+) Matroska (.mkv, Android 10+)                                              |
| Opus     |   YES   | NO  |   MAYBE    |                                                                    Ogg (.ogg) Matroska (.mkv)                                                                     |
| PCM/WAVE |   YES   | NO  |    YES     |                                                                            WAVE (.wav)                                                                            |
| Vorbis   |   YES   | NO  |   MAYBE    |                                             Ogg (.ogg) Matroska (.mkv, Android 4.0+) MPEG-4 (.mp4, .m4a, Android 10+)                                             |

## Installation
You'll need to add your maven dependency list
```toml
# in your 'libs.versions.toml' file
[versions]
lexilabs-basic = "+" # gets the latest version

[libraries]
lexilabs-basic-sound = { module = "app.lexilabs.basic:basic-sound", version.ref = "lexilabs-basic" }
```
then include the library in your gradle build
```kotlin
// in your 'shared/build.gradle.kts' file
sourceSets {
    commonMain.dependencies {
        implementation(libs.lexilabs.basic.sound)
    }
}
```

## Usage

```kotlin
val audioUrl = "https://dare.wisc.edu/wp-content/uploads/sites/1051/2008/11/MS072.mp3"
val audio = Audio(audioUrl, true) // AutoPlay is marked "true"
```

You can also load the audio asynchronously:
```kotlin
val audioUrl = "https://dare.wisc.edu/wp-content/uploads/sites/1051/2008/11/MS072.mp3"
val audio = Audio(audioUrl) // loads the audio file
DoSomethingElse()
audio.play() // plays the audio immediately upon execution
```

You can also pause and stop the audio:
```kotlin
/** PAUSING **/
audio.pause() // remembers where it paused
audio.play() // and resumes once executed again
/** STOPPING **/
audio.stop() // resets to the beginning of the file
audio.play() // and replays it again upon execution
```

You should release your audio when done to preserve memory:
```kotlin
audio.release() // converts the audio instance to null
```
