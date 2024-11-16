package app.lexilabs.basic.sound

/**
 * Play audio from a local file path or a Compose Resource ([String]).
 *
 * Example:
 * ```
 * val audioByte: AudioByte = AudioByte() // Create an AudioByte instance
 * val clickSound: Any = audioByte.load(Res.getUri("files/click.mp3")) // Load the audio file into memory
 * audioByte.play(clickSound) // Call it whenever you like as needed
 * ```
 */
public expect class AudioByte(): AudioByteBuilder {
    /**
     * Used to load an [Audio] file into memory.
     *
     * @param context provide the Platform Context (this is mostly to accommodate Android)
     * @param localPath provide the path and filename for the audio file as a [String]
     *
     * Example:
     * ```
     * val audioByte: AudioByte = AudioByte() // Create an AudioByte instance
     * val clickSound: Any = audioByte.load(Res.getUri("files/click.mp3")) // Load the audio file into memory
     * ```
     */
    override fun load(context: Any, localPath: String): Any
    /**
     * Play a loaded sound immediately, without regard for how many times it's already played
     *
     * @param item provide the result of the [load] function called previously
     *
     * Example:
     * ```
     * val audioByte: AudioByte = AudioByte() // Create an AudioByte instance
     * val clickSound: Any = audioByte.load(Res.getUri("files/click.mp3")) // Load the audio file into memory
     * audioByte.play(clickSound) // Call it whenever you like as needed
     * ```
     */
    override fun play(item: Any)
    /**
     * Used when done to clear the [AudioByte] object from memory.
     **
     * Example:
     * ```
     * val audioByte: AudioByte = AudioByte() // Create an AudioByte instance
     * val clickSound: Any = audioByte.load(Res.getUri("files/click.mp3")) // Load the audio file into memory
     * audioByte.release() // audio can no longer be called
     * ```
     */
    override fun release()
}