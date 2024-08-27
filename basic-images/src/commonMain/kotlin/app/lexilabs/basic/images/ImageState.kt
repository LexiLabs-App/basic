package app.lexilabs.basic.images

public sealed class ImageState {
    public data object NONE: ImageState()
    public data object LOADING: ImageState()
    public data object SHOWING: ImageState()
    public data class ERROR(val message: String) : ImageState()
}