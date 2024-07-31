package app.lexilabs.basic.sound

import kotlinx.coroutines.flow.StateFlow

@ExperimentalBasicSound
public interface AudioBuilder {
    public val audioState: StateFlow<AudioState>
    public fun load()
    public fun play()
    public fun pause()
    public fun stop()
    public fun release()
}