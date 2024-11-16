package app.lexilabs.basic.sound

import app.lexilabs.basic.logging.Log
import org.w3c.dom.Audio

public actual class AudioByte actual constructor() : AudioByteBuilder {
    private var audioPlayer: Audio = Audio()

    public actual override fun load(context: Any, localPath: String): Any {
        Log.i("AudioByte", localPath)
        audioPlayer = Audio(localPath)
        audioPlayer.load()
        return audioPlayer
    }

    public actual override fun play(item: Any) {
        Log.i("AudioByte", "playing")
        (item as Audio).play()
    }

    public actual override fun release() {
        Log.i("AudioByte", "release")
        audioPlayer.pause()
        audioPlayer.src = ""
        audioPlayer.load()
    }
}