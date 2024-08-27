package app.lexilabs.basic.images

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.allocArrayOf
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.jetbrains.skia.ColorAlphaType
import org.jetbrains.skia.ColorSpace
import org.jetbrains.skia.ColorType
import org.jetbrains.skia.Image
import org.jetbrains.skia.ImageInfo
import platform.AppKit.NSImage
import platform.CoreFoundation.CFDataGetBytePtr
import platform.CoreFoundation.CFDataGetLength
import platform.CoreFoundation.CFRelease
import platform.CoreGraphics.CGDataProviderCopyData
import platform.CoreGraphics.CGImageAlphaInfo
import platform.CoreGraphics.CGImageGetAlphaInfo
import platform.CoreGraphics.CGImageGetBytesPerRow
import platform.CoreGraphics.CGImageGetDataProvider
import platform.CoreGraphics.CGImageGetHeight
import platform.CoreGraphics.CGImageGetWidth
import platform.CoreGraphics.CGImageRelease
import platform.Foundation.NSData
import platform.Foundation.NSFileManager
import platform.Foundation.create

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
public actual object ImageLoader {

    public actual suspend fun load(url: BasicUrl): ImageBitmap? {
        var bitmap: ImageBitmap? = null
        return withContext(Dispatchers.IO){
            ImageClient(url.toString())?.let { bitmapByteArray ->
                bitmap = bitmapByteArray.toImageBitmap()
            } ?: {
                bitmap = null
            }
            return@withContext bitmap
        }
    }

    public actual suspend fun load(path: BasicPath): ImageBitmap? {
        return withContext(Dispatchers.IO){
            getFileAsImageBitmap(path.toString())
        }
    }

    private fun getFileAsImageBitmap(filePath: String): ImageBitmap? {
        val fileManager = NSFileManager.defaultManager
        val nsData = fileManager.contentsAtPath(filePath)
        return nsData?.toNSImage()?.toImageBitmap()
    }

    private fun ByteArray.toImageBitmap(): ImageBitmap? {
        return this@toImageBitmap.toNSData().toNSImage().toSkiaImage()?.toComposeImageBitmap()
    }

    private fun ByteArray.toNSData(): NSData {
        memScoped {
            return NSData.create(bytes = allocArrayOf(this@toNSData),
                length = this@toNSData.size.toULong())
        }
    }

    private fun NSData.toNSImage(): NSImage {
        return NSImage(this@toNSImage)
    }

    private fun NSImage.toImageBitmap(): ImageBitmap {
        val skiaImage = this.toSkiaImage() ?: return ImageBitmap(1, 1)
        return skiaImage.toComposeImageBitmap()
    }

    private fun NSImage.toSkiaImage(): Image? {
        val imageRef = this.CGImageForProposedRect(null, null, null) ?: return null

        val width = CGImageGetWidth(imageRef).toInt()
        val height = CGImageGetHeight(imageRef).toInt()

        val bytesPerRow = CGImageGetBytesPerRow(imageRef)
        val data = CGDataProviderCopyData(CGImageGetDataProvider(imageRef))
        val bytePointer = CFDataGetBytePtr(data)
        val length = CFDataGetLength(data)

        val alphaType = when (CGImageGetAlphaInfo(imageRef)) {
            CGImageAlphaInfo.kCGImageAlphaPremultipliedFirst,
            CGImageAlphaInfo.kCGImageAlphaPremultipliedLast -> ColorAlphaType.PREMUL
            CGImageAlphaInfo.kCGImageAlphaFirst,
            CGImageAlphaInfo.kCGImageAlphaLast -> ColorAlphaType.UNPREMUL
            CGImageAlphaInfo.kCGImageAlphaNone,
            CGImageAlphaInfo.kCGImageAlphaNoneSkipFirst,
            CGImageAlphaInfo.kCGImageAlphaNoneSkipLast -> ColorAlphaType.OPAQUE
            else -> ColorAlphaType.UNKNOWN
        }

        val byteArray = ByteArray(length.toInt()) { index ->
            bytePointer!![index].toByte()
        }

        CFRelease(data)
        CGImageRelease(imageRef)

        val skiaColorSpace = ColorSpace.sRGB
        val colorType = ColorType.RGBA_8888

        // Convert RGBA to BGRA
        for (i in byteArray.indices step 4) {
            val r = byteArray[i]
            val g = byteArray[i + 1]
            val b = byteArray[i + 2]
            val a = byteArray[i + 3]

            byteArray[i] = b
            byteArray[i + 2] = r
        }

        return Image.makeRaster(
            imageInfo = ImageInfo(
                width = width,
                height = height,
                colorType = colorType,
                alphaType = alphaType,
                colorSpace = skiaColorSpace
            ),
            bytes = byteArray,
            rowBytes = bytesPerRow.toInt(),
        )
    }
}