package app.lexilabs.basic.images

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.File
import javax.imageio.ImageIO

/**
 * Contains [load] functions for [BasicImage] that accepts both [BasicUrl] and [BasicPath] objects.
 */
@OptIn(ExperimentalBasicImages::class)
public actual object ImageLoader {

    /**
     * Downloads a PNG, JPEG, or WEBP file from an internet URL using a [BasicUrl] object, then provides the [ImageBitmap] file, if available.
     *
     * Example:
     * ```kotlin
     * val url = BasicUrl("https://picsum.photos/200")
     * val bitmap = ImageLoader.load(url)
     * ```
     */
    public actual suspend fun load(url: BasicUrl): ImageBitmap? {
        var bitmap: ImageBitmap? = null
        return withContext(Dispatchers.IO) {
            ImageClient(url.toString())?.let { bitmapByteArray ->
                bitmap = bitmapByteArray.toImageBitmap()
            } ?: {
                bitmap = null
            }
            return@withContext bitmap
        }
    }

    /**
     * Opens a PNG, JPEG, or WEBP file from a local path using a [BasicPath] object, then provides the [ImageBitmap] file, if available.
     *
     * Example:
     * ```kotlin
     * val path = BasicPath("appLocalDirectory/cacheDirectory/images/exampleImage.jpeg")
     * val bitmap = ImageLoader.load(path)
     * ```
     */
    public actual suspend fun load(path: BasicPath): ImageBitmap? {
        return withContext(Dispatchers.IO) {
            val bitmapByteArray = File(path.toString()).readBytes()
            return@withContext bitmapByteArray.toImageBitmap()
        }
    }

    private fun ByteArray.toImageBitmap(): ImageBitmap {
        // Convert ByteArray to BufferedImage
        val inputStream = ByteArrayInputStream(this)
        val bufferedImage: BufferedImage = ImageIO.read(inputStream)
        // Convert BufferedImage to ImageBitmap
        return bufferedImage.toComposeImageBitmap()
    }
}