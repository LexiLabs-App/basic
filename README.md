# The Basic Suite by LexiLabs

A Kotlin Multiplatform library to rapidly add basic features like logging and audio to any project in a small and fast way. Currently supports Apple devices, Android devices, and browsers using Javascript or WASM.

**Current Version:** `0.1.2`
* [Basic-Logging](#basic-logging)
* [Basic-Sound](#basic-sound)

## Basic-Logging
A Kotlin Multiplatform library to rapidly add logging to any project.
Our current prototype, version 0.1.2 supports Android, Apple, Javascript, and WASM.

### Installation
Add your dependencies from Maven
```toml
# in your 'libs.versions.toml' file
[versions]
lexilabs-basic = "0.1.2"

[libraries]
lexilabs-basic-logging = { module = "app.lexilabs.basic:basic-logging", version.ref = "lexilabs-basic-logging" }
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

### At a glance:

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

### How it works
Basic-Logging depends on Java's Log4J, Apple's NSLog, and direct console writing for Javascript and WASM.

## Basic-Sound

A Kotlin Multiplatform library to rapidly integrate audio across all your Kotlin Multiplatform apps.
Our current prototype, Version 0.1.2, supports Android, Apple, and Javascript.

### Supported Filetypes
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

### Installation
You'll need to add your maven dependency list
```toml
# in your 'libs.versions.toml' file
[versions]
lexilabs-basic = "0.1.2"

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

### At a glance:

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
