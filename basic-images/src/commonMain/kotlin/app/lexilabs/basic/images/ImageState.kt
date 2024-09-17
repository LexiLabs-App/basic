package app.lexilabs.basic.images

/**
 * Indicates the current state of an [BasicImage] as it proceeds through the [ImageLoader.load]
 * cycle
 *
 * @property ImageState.NONE The [BasicImage] has not been initiated
 * @property ImageState.LOADING The bitmap is being downloaded by URL or opened at the Path
 * @property ImageState.SHOWING The [BasicImage] is displaying the bitmap
 * @property ImageState.ERROR An error has occurred
 */
@ExperimentalBasicImages
public sealed class ImageState {

    /**
     * [ImageState.NONE] indicates [BasicImage] has not been initiated
     */
    public data object NONE: ImageState()

    /**
     * [ImageState.LOADING] indicates the bitmap is being downloaded by URL or opened at the Path
     */
    public data object LOADING: ImageState()

    /**
     * [ImageState.SHOWING] indicates the [BasicImage] is displaying the bitmap
     *
     */
    public data object SHOWING: ImageState()

    /**
     * [ImageState.ERROR] indicates an error has occurred.
     * @param message Provides error information for logging as a [String]
     */
    public data class ERROR(val message: String) : ImageState()
}