package app.lexilabs.basic.sound

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.util.Log

public actual class AudioByte actual constructor() : AudioByteBuilder {
    private val soundPool: SoundPool
    private var audioIdHolder: Int = 0

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(4)
            .setAudioAttributes(audioAttributes)
            .build()
    }

    public actual override fun load(context: Any, localPath: String): Any {
        Log.i("AudioByte", localPath)
        val path = localPath.removePrefix("file:///android_asset/")
        val fd = (context as Context).assets.openFd(path)
        audioIdHolder = soundPool.load(fd, 1)
        return audioIdHolder
    }

    public actual override fun play(item: Any) {
        soundPool.play(item as Int, 1f, 1f, 1, 0, 1f)
    }

    public actual override fun release() {
        soundPool.release()
    }
}