# Basic-Sound
<img src="../docs/images/basic.png" alt="basic" height="240" align="right"/> 

[![Basic-Sound](https://img.shields.io/maven-central/v/app.lexilabs.basic/basic-sound?color=blue)](https://central.sonatype.com/artifact/app.lexilabs.basic/basic-sound)

A Kotlin Multiplatform library to rapidly integrate audio across all your Kotlin Multiplatform apps.
Currently, this library only ingests URLs; objects and files on the local path are not yet supported.

![badge-android](http://img.shields.io/badge/android-full_support-65c663.svg?style=flat)
![badge-ios](http://img.shields.io/badge/ios-full_support-65c663.svg?style=flat)
![badge-mac](http://img.shields.io/badge/macos-full_support-65c663.svg?style=flat)
![badge-watchos](http://img.shields.io/badge/watchos-full_support-65c663.svg?style=flat)
![badge-tvos](http://img.shields.io/badge/tvos-full_support-65c663.svg?style=flat)
![badge-nodejs](https://img.shields.io/badge/jsNode-full_support-65c663.svg?style=flat)
![badge-jsBrowser](https://img.shields.io/badge/jsBrowser-full_support-65c663.svg?style=flat)
![badge-wasmJsBrowser](https://img.shields.io/badge/wasmJsBrowser-full_support-65c663.svg?style=flat)
![badge-jvm](http://img.shields.io/badge/jvm-no_support-red.svg?style=flat)
![badge-linux](http://img.shields.io/badge/linux-no_support-red.svg?style=flat)
![badge-windows](http://img.shields.io/badge/windows-no_support-red.svg?style=flat)

## Supported Filetypes
| Format    |      Android       |        iOS         | javascript / wasm  | File / Container Types                                                                                                                                            |
|:----------|:------------------:|:------------------:|:------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| AAC LC    | :white_check_mark: | :white_check_mark: |        :x:         | 3GPP (.3gp) MPEG-4 (.mp4, .m4a) ADTS raw AAC (.aac, decode in Android 3.1+, encode in Android 4.0+, ADIF not supported) MPEG-TS (.ts, not seekable, Android 3.0+) |
| AMR-NB    | :white_check_mark: |        :x:         |        :x:         | 3GPP (.3gp) AMR (.amr)                                                                                                                                            |
| FLAC      | :white_check_mark: |        :x:         |        :x:         | FLAC (.flac) MPEG-4 (.mp4, .m4a, Android 10+)                                                                                                                     |
| MIDI      | :white_check_mark: |        :x:         |        :x:         | Type 0 and 1 (.mid, .xmf, .mxmf) RTTTL/RTX (.rtttl, .rtx) OTA (.ota) iMelody (.imy)                                                                               |
| MP3       | :white_check_mark: | :white_check_mark: | :white_check_mark: | MP3 (.mp3) MPEG-4 (.mp4, .m4a, Android 10+) Matroska (.mkv, Android 10+)                                                                                          |
| Opus      | :white_check_mark: |        :x:         |     :question:     | Ogg (.ogg) Matroska (.mkv)                                                                                                                                        |
| PCM/WAVE  | :white_check_mark: |        :x:         | :white_check_mark: | WAVE (.wav)                                                                                                                                                       |
| Vorbis    | :white_check_mark: |        :x:         |     :question:     | Ogg (.ogg) Matroska (.mkv, Android 4.0+) MPEG-4 (.mp4, .m4a, Android 10+)                                                                                         |

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
You can initialize an `Audio` object with a URL
```kotlin
val resource = "https://dare.wisc.edu/wp-content/uploads/sites/1051/2008/11/MS072.mp3"
val audio = Audio(resource, true) // AutoPlay is marked "true"
```

You can play the audio separately from initializing the `Audio` object.
```kotlin
val resource = "https://dare.wisc.edu/wp-content/uploads/sites/1051/2008/11/MS072.mp3"
val audio = Audio(resource) // loads the audio file
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

There are lots of options to load larger files asynchronously:
```kotlin
// Create empty Audio instance
val audio = Audio()
audio.resource = "https://dare.wisc.edu/wp-content/uploads/sites/1051/2008/11/MS072.mp3"
// Begin collecting the state of audio
val audioState by audioPlayer.audioState.collectAsState()
// Begin loading the audio async
lifecycleScope.launch {
    withContext(Dispatchers.IO) {
        audio.load()
    }
}

DoSomethingElse() // do other stuff in the meantime

Button(
    onClick = {
        when (audioState) {
            is AudioState.NONE -> audioPlayer.load()
            is AudioState.READY -> audioPlayer.play()
            is AudioState.ERROR -> println((audioState as AudioState.ERROR).message)
            is AudioState.PAUSED -> audioPlayer.play()
            is AudioState.PLAYING -> audioPlayer.pause()
            else -> { /** DO NOTHING **/ }
        }
    }
) {
    when (audioState) {
        is AudioState.ERROR -> Text("Error")
        is AudioState.LOADING -> Text("Loading")
        is AudioState.NONE -> Text("None")
        is AudioState.READY -> Text("Ready")
        is AudioState.PAUSED -> Text("Paused")
        is AudioState.PLAYING -> Text("Playing")
    }
}
```
