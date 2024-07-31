# Basic-Sound

A Kotlin Multiplatform library to rapidly integrate audio across all your Kotlin Multiplatform apps.
Our current prototype, Version 0.1.1, supports Android and iOS.

## Supported Filetypes
| Format   |    Android   | iOS |                                                                       File / Container Types                                                                      |
|----------|:------------:|:---:|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| AAC LC   |      YES     | YES | 3GPP (.3gp) MPEG-4 (.mp4, .m4a) ADTS raw AAC (.aac, decode in Android 3.1+, encode in Android 4.0+, ADIF not supported) MPEG-TS (.ts, not seekable, Android 3.0+) |
| AMR-NB   |      YES     |  NO | 3GPP (.3gp) AMR (.amr)                                                                                                                                            |
| FLAC     | Android 3.1+ |  NO | FLAC (.flac) MPEG-4 (.mp4, .m4a, Android 10+)                                                                                                                     |
| MIDI     |      YES     |  NO | Type 0 and 1 (.mid, .xmf, .mxmf) RTTTL/RTX (.rtttl, .rtx) OTA (.ota) iMelody (.imy)                                                                               |
| MP3      |      YES     | YES | MP3 (.mp3) MPEG-4 (.mp4, .m4a, Android 10+) Matroska (.mkv, Android 10+)                                                                                          |
| Opus     | Android 5.0+ |  NO | Ogg (.ogg) Matroska (.mkv)                                                                                                                                        |
| PCM/WAVE |      YES     |  NO | WAVE (.wav)                                                                                                                                                       |
| Vorbis   |      YES     |  NO | Ogg (.ogg) Matroska (.mkv, Android 4.0+) MPEG-4 (.mp4, .m4a, Android 10+)                                                                                         |

## Installation
You'll need to add your maven dependency list
```toml
# in your 'libs.versions.toml' file
[versions]
lexilabs-basic-sound = "0.1.1"

[libraries]
lexilabs-basic-sound = { module = "app.lexilabs.basic:basic-sound", version.ref = "lexilabs-basic-sound" }
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

## At a glance:

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
