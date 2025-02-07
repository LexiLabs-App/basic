package app.lexilabs.basic.sound

import app.lexilabs.basic.logging.Log
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UnsafeNumber
import platform.AVFAudio.AVAudioEngine
import platform.AVFAudio.AVAudioFile
import platform.AVFAudio.AVAudioMixerNode
import platform.AVFAudio.AVAudioNodeBus
import platform.AVFAudio.AVAudioPlayerNode
import platform.Foundation.NSURL

@OptIn(ExperimentalForeignApi::class)
public actual class AudioByte actual constructor() : AudioByteBuilder {
    private val engine: AVAudioEngine = AVAudioEngine()
    private val mixer: AVAudioMixerNode = engine.mainMixerNode
    private val tag = "AudioByte"

    init {
        engine.startAndReturnError(null)
    }

    public actual override fun load(context: Any, localPath: String): Any {
        val pathUrl = NSURL(string = localPath)
        return AVAudioFile(pathUrl, null)
    }

    @OptIn(UnsafeNumber::class)
    actual override fun play(item: Any) {
        val playerNode = AVAudioPlayerNode()
        engine.attachNode(playerNode)
        engine.connect(playerNode, mixer, mixer.outputFormatForBus(AVAudioNodeBus.MIN_VALUE))
        playerNode.scheduleFile(
            file = item as AVAudioFile,
            atTime = null,
            null
//            completionHandler = {
//                this.engine.detachNode(playerNode)
//            }
        )
        playerNode.play()
        Log.d(tag, "play: playing")
    }

    actual override fun release() {
        engine.stop()
    }
}