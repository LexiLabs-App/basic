@file:Suppress("ConvertSecondaryConstructorToPrimary")

package app.lexilabs.basic.sound

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import platform.AVFAudio.AVAudioPlayer
import platform.Foundation.NSData
import platform.Foundation.NSURL
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

    private val _audioState = MutableStateFlow<AudioState>(AudioState.NONE)
    public actual override val audioState: StateFlow<AudioState> = _audioState.asStateFlow()

    private var player: AVAudioPlayer? = null

    public actual var url: String = ""
    public actual var autoPlay: Boolean = false

    public actual constructor(url: String, autoPlay: Boolean) : this() {
        this.url = url
        this.autoPlay = autoPlay
        load()
    }

    public actual override fun load() {
        _audioState.value = AudioState.LOADING
//        val nsUrl = NSURL.URLWithString(url)
//        nsUrl?.let { verifiedUrl ->
//            val playerItem = AVPlayerItem(uRL = verifiedUrl)
//            player = AVPlayer(playerItem = playerItem)
            val url: NSURL = NSURL.URLWithString(url) ?: throw IllegalStateException("load:The URL provided was invalid")
            val data: NSData = NSData.dataWithContentsOfURL(url) ?: throw IllegalStateException("load:NS failed to load URL as data")
            player = AVAudioPlayer(data, error = null)
            _audioState.value = AudioState.READY
            if (autoPlay) {
                play()
            }
//        }
    }

    public actual override fun play() {
        if (audioState.value == AudioState.NONE) {
            throw IllegalStateException("play:AudioState.NONE: mediaPlayer not initialized")
        }
        try {
            player?.play()
            _audioState.value = AudioState.PLAYING
        } catch (e: Exception) {
            _audioState.value = AudioState.ERROR("play: $e")
            throw Exception("play:$e")
        }
    }

    public actual override fun pause() {
        try {
            player?.pause()
            _audioState.value = AudioState.PAUSED
        } catch (e: Exception) {
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
            _audioState.value = AudioState.ERROR("stop: $e")
        }
    }

    public actual override fun release() {
        try {
            player = null
            _audioState.value = AudioState.NONE
        } catch (e: Exception) {
            _audioState.value = AudioState.ERROR("release: $e")
        }
    }
}
