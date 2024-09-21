package app.lexilabs.basic.images

import androidx.compose.ui.graphics.ImageBitmap

/**
 * Contains [load] functions for [BasicImage] that accepts both [BasicUrl] and [BasicPath] objects.
 */
@ExperimentalBasicImages
public expect object ImageLoader {
    /**
     * Downloads a PNG, JPEG, or WEBP file from an internet URL using a [BasicUrl] object, then provides the [ImageBitmap] file, if available.
     *
     * Example:
     * ```kotlin
     * val url = BasicUrl("https://picsum.photos/200")
     * val bitmap = ImageLoader.load(url)
     * ```
     */
    public suspend fun load(url: BasicUrl): ImageBitmap?

    /**
     * Opens a PNG, JPEG, or WEBP file from a local path using a [BasicPath] object, then provides the [ImageBitmap] file, if available.
     *
     * Example:
     * ```kotlin
     * val path = BasicPath("appLocalDirectory/cacheDirectory/images/exampleImage.jpeg")
     * val bitmap = ImageLoader.load(path)
     * ```
     */
    public suspend fun load(path: BasicPath): ImageBitmap?
}