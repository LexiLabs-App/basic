package app.lexilabs.basic.sound

@ExperimentalBasicSound
public sealed class AudioState {
    public data object NONE: AudioState()
    public data object LOADING : AudioState()
    public data object READY : AudioState()
    public data object PLAYING : AudioState()
    public data object PAUSED : AudioState()
    public data class ERROR(val message: String) : AudioState()
}