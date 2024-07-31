@file:Suppress("ConvertSecondaryConstructorToPrimary")

package app.lexilabs.basic.sound

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
public actual class Audio: AudioBuilder {

    private val tag = "Audio"


    private val _audioState = MutableStateFlow<AudioState>(AudioState.NONE)
    public actual override val audioState: StateFlow<AudioState> = _audioState.asStateFlow()

    private var mediaPlayer: MediaPlayer? = null

    public actual var url: String = ""
    public actual var autoPlay: Boolean = false

    public actual constructor(url: String, autoPlay: Boolean) {
        this.url = url
        this.autoPlay = autoPlay
        load()
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
    public actual override fun load() {
        _audioState.value = AudioState.LOADING
        mediaPlayer = MediaPlayer().apply {
            setDataSource(url)
            prepareAsync()
            setOnPreparedListener {
                // Ready to play
                _audioState.value = AudioState.READY
                if (autoPlay) {
                    play()
                }
            }
            setOnErrorListener { _, what, extra ->
                // Handle error
                _audioState.value = AudioState.ERROR("init: MediaPlayer Error: what=$what, extra=$extra")
                true // Indicating that the error was handled
            }
            setOnCompletionListener {
                _audioState.value = AudioState.READY
            }
        }
    }

    public actual override fun play() {
        mediaPlayer?.let {
            if (!it.isPlaying) {
                when (audioState.value) {
                    is AudioState.PLAYING -> {
                        /** do nothing **/
                        Log.d(tag, "play:AudioState.PLAYING: do you want it louder or something, lady?" )
                    }

                    is AudioState.LOADING -> {
                        /** do nothing **/
                        Log.d(tag, "play:AudioState.LOADING: wait for player to load" )
                    }

                    is AudioState.NONE -> {
                        Log.e(tag, "play:AudioState.NONE: mediaPlayer not initialized" )
                        _audioState.value =
                            AudioState.ERROR("play:AudioState.NONE: mediaPlayer not initialized")
                        throw IllegalStateException("play:AudioState.NONE: mediaPlayer not initialized")
                    }

                    is AudioState.ERROR -> {
                        Log.e(tag, "play:AudioState.ERROR: ${(audioState.value as AudioState.ERROR).message}" )
                        throw Exception("play:AudioState.ERROR: ${(audioState.value as AudioState.ERROR).message}")
                    }

                    is AudioState.PAUSED -> {
                        Log.i(tag,"play:AudioState.PAUSED: resuming" )
                        it.start()
                        _audioState.value = AudioState.PLAYING
                    }

                    is AudioState.READY -> {
                        Log.i(tag, "play:AudioState.READY: playing" )
                        it.start()
                        _audioState.value = AudioState.PLAYING
                    }
                }
            }
        }
    }

    public actual override fun pause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                _audioState.value = AudioState.PAUSED
            }
        }
    }

    public actual override fun stop() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
                it.prepareAsync()
                _audioState.value = AudioState.READY
            }
        }
    }

    public actual override fun release() {
        _audioState.value = AudioState.NONE
        mediaPlayer?.release()
        mediaPlayer = null
    }
}