@file:Suppress("ConvertSecondaryConstructorToPrimary")

package app.lexilabs.basic.sound

import app.lexilabs.basic.logging.Log
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import platform.AVFAudio.AVAudioPlayer
import platform.Foundation.NSData
import platform.Foundation.NSURL
import platform.Foundation.dataWithContentsOfFile
import platform.Foundation.dataWithContentsOfURL

/**
 * Play audio from a url ([String]).
 *
 * Example:
 * ```
 * val audioUrl = "https://dare.wisc.edu/wp-content/uploads/sites/1051/2008/11/MS072.mp3"
 * val audio = Audio(audioUrl, true) // AutoPlay is marked "true"
 * ```
 */

@OptIn(ExperimentalForeignApi::class)
@ExperimentalBasicSound
public actual class Audio actual constructor(): AudioBuilder {

    private val tag = "Basic-Sound Audio"

    private val _audioState = MutableStateFlow<AudioState>(AudioState.NONE)
    public actual override val audioState: StateFlow<AudioState> = _audioState.asStateFlow()

    private var player: AVAudioPlayer? = null

    public actual var resource: String = ""
    public actual var autoPlay: Boolean = false

    public actual constructor(resource: String, autoPlay: Boolean) : this() {
        this.resource = resource
        this.autoPlay = autoPlay
        load()
    }

    public actual constructor(context: Any?, composeResource: String, autoPlay: Boolean) : this() {
        this.resource = composeResource
        this.autoPlay = autoPlay
        load()
    }

    public actual override fun load(context: Any?) {
        try {
            _audioState.value = AudioState.LOADING
            val data: NSData
            if (resource.substring(0, 4) == "http") {
                val url: NSURL = NSURL.URLWithString(resource)
                    ?: throw IllegalStateException("load:The URL provided was invalid")
                data = NSData.dataWithContentsOfURL(url)
                    ?: throw IllegalStateException("load:NS failed to load URL as data")
            } else {
                data = NSData.dataWithContentsOfFile(resource)
                    ?: throw IllegalStateException("load:The path provided was invalid")
            }
            player = AVAudioPlayer(data, error = null)
            _audioState.value = AudioState.READY
            if (autoPlay) {
                play()
            }
        } catch (e: Exception) {
            Log.e(tag, "load:failure: $e")
            _audioState.value = AudioState.ERROR("load:failure: $e")
        }
    }

    public actual override fun play() {
        try {
            if (audioState.value == AudioState.NONE) {
                throw IllegalStateException("play:AudioState.NONE: mediaPlayer not initialized")
            }
            player?.play()
            _audioState.value = AudioState.PLAYING
        } catch (e: Exception) {
            Log.e(tag, "play:failure:$e")
            _audioState.value = AudioState.ERROR("play: $e")
        }
    }

    public actual override fun pause() {
        try {
            player?.pause()
            _audioState.value = AudioState.PAUSED
        } catch (e: Exception) {
            Log.e(tag, "pause:failure: $e")
            _audioState.value = AudioState.ERROR("pause: $e")
        }
    }

    public actual override fun stop() {
        try {
            player?.pause()
//            player?.seekToTime(CMTimeMake(value = 0, timescale = 1))
            player?.currentTime = 0.0
            _audioState.value = AudioState.READY
        } catch (e: Exception) {
            Log.e(tag, "stop:failure: $e")
            _audioState.value = AudioState.ERROR("stop: $e")
        }
    }

    public actual override fun release() {
        try {
            player = null
            _audioState.value = AudioState.NONE
        } catch (e: Exception) {
            Log.e(tag, "release:failure: $e")
            _audioState.value = AudioState.ERROR("release: $e")
        }
    }
}
