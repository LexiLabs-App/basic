package app.lexilabs.basic.sound

import kotlinx.coroutines.flow.StateFlow

actual class Audio actual constructor(url: String, autoPlay: Boolean) {
    actual val audioState: StateFlow<AudioState>
        get() = TODO("Not yet implemented")

    actual fun play() {
    }

    actual fun pause() {
    }

    actual fun stop() {
    }

    actual fun release() {
    }

    actual val url: String
        get() = TODO("Not yet implemented")
    actual val autoPlay: Boolean
        get() = TODO("Not yet implemented")
}