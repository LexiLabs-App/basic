package app.lexilabs.basic.sound

import app.lexilabs.basic.logging.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import kotlin.io.path.Path

@ExperimentalBasicSound
public actual class Audio actual constructor(): AudioBuilder {

    private val tag = "Basic-Sound Audio"

    private val _audioState = MutableStateFlow<AudioState>(AudioState.NONE)
    public actual override val audioState: StateFlow<AudioState> = _audioState.asStateFlow()

    private var clip: Clip = AudioSystem.getClip()
    private var cursor: Long = 0L

    public actual var resource: String = ""
    public actual var autoPlay: Boolean = false

    public actual constructor(resource: String, autoPlay: Boolean): this() {
        this.resource = resource
        this.autoPlay = autoPlay
    }

    public actual constructor(context: Any?, composeResource: String, autoPlay: Boolean): this() {
        this.resource = composeResource
        this.autoPlay = autoPlay
    }

    public actual override fun load(context: Any?) {
        try {
            _audioState.value = AudioState.LOADING

            val stream = if (resource.substring(0, 4) == "http") {
                AudioSystem.getAudioInputStream(Path(resource).toUri().toURL())
                    ?: throw IllegalStateException("load:The URL provided was invalid or failed to load")
            } else {
                AudioSystem.getAudioInputStream(File(resource).absoluteFile)
                    ?: throw IllegalStateException("load:The path provided was invalid")
            }
            clip.open(stream)
            when (clip.isOpen) {
                true -> {
                    _audioState.value = AudioState.READY
                    if (autoPlay) {
                        play()
                    }
                }

                false -> _audioState.value = AudioState.LOADING
            }
        } catch (e: Exception) {
            Log.e(tag, "load:failure: $e")
            _audioState.value = AudioState.ERROR("load:failure: $e")
        }
    }

    public actual override fun play() {
        when (audioState.value) {
            is AudioState.LOADING,
            is AudioState.PLAYING -> { /** do nothing **/ }
            is AudioState.NONE -> {
                throw Exception ("AudioState.NONE: load() not run yet")
            }
            is AudioState.ERROR -> {
                throw Exception("AudioState.ERROR: ${(audioState.value as AudioState.ERROR).message}")
            }
            is AudioState.PAUSED -> {
                clip.microsecondPosition = cursor
                clip.start()
                _audioState.value = AudioState.PLAYING
            }
            is AudioState.READY -> {
                clip.start()
                _audioState.value = AudioState.PLAYING
            }
        }
    }

    public actual override fun pause() {
        try {
            if (clip.isRunning) {
                cursor = clip.microsecondPosition
                clip.stop()
                _audioState.value = AudioState.PAUSED
            }
        } catch (e: Exception) {
            Log.e(tag, "pause:failure: $e")
            _audioState.value = AudioState.ERROR("pause:failure: $e")
        }
    }

    public actual override fun stop() {
        try {
            if (clip.isRunning) {
                clip.stop()
                cursor = 0L
                _audioState.value = AudioState.READY
            }
        } catch (e: Exception) {
            Log.e(tag, "stop:failure: $e")
            _audioState.value = AudioState.ERROR("stop:failure: $e")
        }
    }

    public actual override fun release() {
        try {
            _audioState.value = AudioState.NONE
            clip.flush()
        } catch (e: Exception) {
            Log.e(tag, "release:failure: $e")
            _audioState.value = AudioState.ERROR("release:failure: $e")
        }
    }
}