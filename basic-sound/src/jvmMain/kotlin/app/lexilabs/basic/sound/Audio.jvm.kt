package app.lexilabs.basic.sound

import kotlinx.coroutines.flow.StateFlow

@ExperimentalBasicSound
public actual class Audio actual constructor(url: String, autoPlay: Boolean) {
    public actual val audioState: StateFlow<AudioState>
        get() = TODO("Not yet implemented")

    public actual fun play() {
    }

    public actual fun pause() {
    }

    public actual fun stop() {
    }

    public actual fun release() {
    }

    public actual val url: String
        get() = TODO("Not yet implemented")
    public actual val autoPlay: Boolean
        get() = TODO("Not yet implemented")
}