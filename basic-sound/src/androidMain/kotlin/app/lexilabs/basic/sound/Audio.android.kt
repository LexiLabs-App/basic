@file:Suppress("ConvertSecondaryConstructorToPrimary")

package app.lexilabs.basic.sound

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Play audio from a url ([String]).
 *
 * Example:
 * ```
 * val audioUrl = "https://dare.wisc.edu/wp-content/uploads/sites/1051/2008/11/MS072.mp3"
 * val audio = Audio(audioUrl, true) // AutoPlay is marked "true"
 * ```
 */

@ExperimentalBasicSound
public actual class Audio actual constructor(): AudioBuilder {

    private val tag = "Audio"

    private val _audioState = MutableStateFlow<AudioState>(AudioState.NONE)
    public actual override val audioState: StateFlow<AudioState> = _audioState.asStateFlow()

    private var mediaPlayer: MediaPlayer? = null

    public actual var resource: String = ""
    public actual var autoPlay: Boolean = false

    public actual constructor(resource: String, autoPlay: Boolean): this() {
        this.resource = resource
        this.autoPlay = autoPlay
        load()
    }

    public actual constructor(context: Any?, composeResource: String, autoPlay: Boolean): this() {
        this.resource = composeResource
        this.autoPlay = autoPlay
        context?.let {
            load(it)
        } ?: throw NullPointerException("Context is required for Composable Resource to be used with `Audio()`")
    }

    /**
     * Used to load an [Audio] file when [AudioState.NONE].
     *
     * Sets the [audioState] to [AudioState.READY]
     *
     * Example:
     * ```
     * val audio = Audio(audioUrl) // AutoPlay defaults to "false"
     * audio.play() // plays the sound immediately
     * ```
     */
    public actual override fun load(context: Any?) {
        try {
            _audioState.value = AudioState.LOADING
            mediaPlayer = MediaPlayer().apply {
                // if there is a context provided
                (context as Context?)?.let {
                    setDataSource(
                        // Convert file to File Descriptor before ingesting
                        it.assets.openFd(
                            // Try to remove prefix created by Compose Resources first
                            resource.removePrefix("file:///android_asset/")
                        )
                    )
                } ?: setDataSource(resource)
                // reset context after

                prepareAsync()
                setOnPreparedListener {
                    // Ready to play
                    _audioState.value = AudioState.READY
                    if (autoPlay) {
                        play()
                    }
                }
                setOnErrorListener { _, _, _ ->
                    throw Exception("init: MediaPlayer Error: $resource")
                }
                setOnCompletionListener {
                    _audioState.value = AudioState.READY
                }
            }
        } catch (e: Exception) {
            Log.e(tag, "load:failure: $e")
            _audioState.value = AudioState.ERROR("load:failure: $e")
        }
    }

    public actual override fun play() {
        try {
            mediaPlayer?.let {
                if (!it.isPlaying) {
                    when (audioState.value) {
                        is AudioState.LOADING,
                        is AudioState.PLAYING -> { /** do nothing **/ }
                        is AudioState.NONE -> {
                            throw Exception ("AudioState.NONE: mediaPlayer not initialized")
//                        throw IllegalStateException("play:AudioState.NONE: mediaPlayer not initialized")
                        }
                        is AudioState.ERROR -> {
                            throw Exception("AudioState.ERROR: ${(audioState.value as AudioState.ERROR).message}")
                        }
                        is AudioState.PAUSED,
                        is AudioState.READY -> {
                            it.start()
                            _audioState.value = AudioState.PLAYING
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(tag, "play:failure: $e")
            _audioState.value = AudioState.ERROR("play:failure: $e")
        }
    }

    public actual override fun pause() {
        try {
            mediaPlayer?.let {
                if (it.isPlaying) {
                    it.pause()
                    _audioState.value = AudioState.PAUSED
                }
            }
        } catch (e: Exception) {
            Log.e(tag, "pause:failure: $e")
            _audioState.value = AudioState.ERROR("pause:failure: $e")
        }
    }

    public actual override fun stop() {
        try {
            mediaPlayer?.let {
                if (it.isPlaying) {
                    it.stop()
                    it.prepareAsync()
                    _audioState.value = AudioState.READY
                }
            }
        } catch (e: Exception) {
            Log.e(tag, "stop:failure: $e")
            _audioState.value = AudioState.ERROR("stop:failure: $e")
        }
    }

    public actual override fun release() {
        try {
            _audioState.value = AudioState.NONE
            mediaPlayer?.release()
            mediaPlayer = null
        } catch (e: Exception) {
            Log.e(tag, "release:failure: $e")
            _audioState.value = AudioState.ERROR("release:failure: $e")
        }
    }
}