package app.lexilabs.basic.sound

/**
 * @property[NONE] indicates merely an initial state where no audio has loaded
 * @property[LOADING] an audio file is being loaded
 * @property[READY] sound is ready to be played
 * @property[PLAYING] player is currently playing audio, even if not audible
 * @property[PAUSED] player has stopped but retained its progress / timecode
 * @property[ERROR] Something unexpected occurred. Error will be printed to logs
 */
@ExperimentalBasicSound
public sealed class AudioState {

    /**
     * @property[NONE] Indicates merely an initial state where no audio has loaded
     */
    public data object NONE: AudioState()

    /**
     * @property[LOADING] an audio file is being loaded
     */
    public data object LOADING : AudioState()

    /**
     * @property[READY] sound is ready to be played
     */
    public data object READY : AudioState()

    /**
     * @property[PLAYING] player is currently playing audio, even if not audible
     */
    public data object PLAYING : AudioState()

    /**
     * @property[PAUSED] player has stopped but retained its progress / timecode
     */
    public data object PAUSED : AudioState()

    /**
     * @property[ERROR] Something unexpected occurred. Error will be printed to logs
     */
    public data class ERROR(val message: String) : AudioState()
}