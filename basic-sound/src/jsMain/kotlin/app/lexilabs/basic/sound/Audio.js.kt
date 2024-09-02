package app.lexilabs.basic.sound

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.w3c.dom.Audio

@OptIn(ExperimentalBasicSound::class)
public actual class Audio public actual constructor() : AudioBuilder {

    public actual var resource: String = ""
    public actual var autoPlay: Boolean = false

    private val _audioState = MutableStateFlow<AudioState>(AudioState.NONE)
    public actual override val audioState: StateFlow<AudioState> = _audioState.asStateFlow()

    private var player: Audio? = null

    public actual constructor(resource: String, autoPlay: Boolean) : this() {
        this.resource = resource
        this.autoPlay = autoPlay
        load()
    }

    actual override fun load() {
        _audioState.value = AudioState.LOADING
        try {
            player = Audio(resource)
            player?.oncanplaythrough?.let { _ ->
                {
                    _audioState.value = AudioState.READY
                    if (autoPlay) {
                        play()
                    }
                }
            }
        } catch (e: Exception) {
            _audioState.value = AudioState.ERROR("load:failure:$e")
        }
    }

    actual override fun play() {
        player?.let {
            if (audioState.value !is AudioState.PLAYING) {
                when (audioState.value) {
                    is AudioState.ERROR -> {
                        throw Exception("play:AudioState.ERROR: ${(audioState.value as AudioState.ERROR).message}")
                    }
                    is AudioState.LOADING -> { /** DO NOTHING **/}
                    is AudioState.NONE -> {
                        _audioState.value =
                            AudioState.ERROR("play:AudioState.NONE: mediaPlayer not initialized")
                        throw IllegalStateException("play:AudioState.NONE: mediaPlayer not initialized")
                    }
                    is AudioState.PAUSED -> {
                        it.play()
                        _audioState.value = AudioState.PLAYING
                    }
                    is AudioState.PLAYING -> { /** DO NOTHING **/ }
                    is AudioState.READY -> {
                        it.play()
                        _audioState.value = AudioState.PLAYING
                    }
                }
            }
        }
    }

    actual override fun pause() {
        player?.let {
            if (audioState.value is AudioState.PLAYING) {
                it.pause()
                _audioState.value = AudioState.PAUSED
            }
        }
    }

    actual override fun stop() {
        player?.let {
            if (audioState.value is AudioState.PLAYING) {
                it.pause()
                it.currentTime = 0.0
                _audioState.value = AudioState.READY
            }
        }
    }

    actual override fun release() {
        _audioState.value = AudioState.NONE
        player?.let {
            it.pause()
            it.src = ""
            it.load()
        }
        player = null
    }
}