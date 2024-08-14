package app.lexilabs.basic.sound

import kotlinx.coroutines.flow.StateFlow

/**
 * A common interface to enable multiplatform audio.
 *
 * Requires override of [audioState], [load], [play], [pause], [stop], and [release] functions.
 *
 */
@ExperimentalBasicSound
public interface AudioBuilder {
    public val audioState: StateFlow<AudioState>
    public fun load()
    public fun play()
    public fun pause()
    public fun stop()
    public fun release()
}