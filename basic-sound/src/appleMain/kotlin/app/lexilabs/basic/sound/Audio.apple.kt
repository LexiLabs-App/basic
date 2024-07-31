@file:Suppress("ConvertSecondaryConstructorToPrimary")

package app.lexilabs.basic.sound

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.seekToTime
import platform.CoreMedia.CMTimeMake
import platform.Foundation.NSURL

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

    private val _audioState = MutableStateFlow<AudioState>(AudioState.NONE)
    public actual override val audioState: StateFlow<AudioState> = _audioState.asStateFlow()

    private var player: AVPlayer? = null

    public actual var url: String = ""
    public actual var autoPlay: Boolean = false

    public actual constructor(url: String, autoPlay: Boolean) {
        this.url = url
        this.autoPlay = autoPlay
        load()
    }

    public actual override fun load() {
        _audioState.value = AudioState.LOADING
        val nsUrl = NSURL.URLWithString(url)
        nsUrl?.let { verifiedUrl ->
            val playerItem = AVPlayerItem(uRL = verifiedUrl)
            player = AVPlayer(playerItem = playerItem)
            _audioState.value = AudioState.READY
            if (autoPlay) {
                play()
            }
        }
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

    @OptIn(ExperimentalForeignApi::class)
    public actual override fun stop() {
        try {
            player?.pause()
            player?.seekToTime(CMTimeMake(value = 0, timescale = 1))
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
