package app.lexilabs.basic.sound

@ExperimentalBasicSound
/**
 * @property[NONE] indicates merely an initial state where no audio has loaded
 * @property[LOADING] an audio file is being loaded
 * @property[READY] sound is ready to be played
 * @property[PLAYING] player is currently playing audio, even if not audible
 * @property[PAUSED] player has stopped but retained its progress / timecode
 * @property[ERROR] Something unexpected occurred. Error will be printed to logs
 */
public sealed class AudioState {
    public data object NONE: AudioState()
    public data object LOADING : AudioState()
    public data object READY : AudioState()
    public data object PLAYING : AudioState()
    public data object PAUSED : AudioState()
    public data class ERROR(val message: String) : AudioState()
}