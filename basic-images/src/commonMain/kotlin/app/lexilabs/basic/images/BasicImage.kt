package app.lexilabs.basic.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality
import androidx.compose.ui.layout.ContentScale
import app.lexilabs.basic.images.ImageLoader.load

/**
 * A composable that lays out and asynchronously draws a [ImageBitmap] from a given [BasicPath]
 * using a [LaunchedEffect].
 *
 * Example:
 * ```kotlin
 * @Composable
 * fun Test() {
 *     // Loads an image via URL location
 *     BasicImage(path = BasicPath("/images/exampleImage.jpeg"))
 * }
 * ```
 *
 * @param path The [BasicPath] to obtain the PNG, JPEG, or WEBP file
 * @param contentDescription text used by accessibility services to describe what this image
 * represents.
 * @param modifier Modifier used to adjust the layout algorithm or draw decoration content (ex.
 * background)
 * @param alignment Optional alignment parameter used to place the [ImageBitmap] in the given
 * bounds defined by the width and height
 * @param contentScale Optional scale parameter used to determine the aspect ratio scaling to be used
 * if the bounds are a different size from the intrinsic size of the [ImageBitmap]
 * @param alpha Optional opacity to be applied to the [ImageBitmap] when it is rendered onscreen
 * @param colorFilter Optional ColorFilter to apply for the [ImageBitmap] when it is rendered
 * onscreen
 * @param filterQuality Sampling algorithm applied to the [ImageBitmap] when it is scaled and drawn
 * into the destination. The default is [FilterQuality.Low] which scales using a bilinear
 * sampling algorithm
 * @param placeholderEnabled Boolean value to determine if the image should be preceded by a
 * circular progress indicator while loading.
 */
@ExperimentalBasicImages
@Composable
public fun BasicImage(
    path: BasicPath,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DefaultFilterQuality,
    placeholderEnabled: Boolean = true
) {
    var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var state by remember { mutableStateOf<ImageState>(ImageState.NONE) }

    LaunchedEffect(path) {
        state = ImageState.LOADING
        bitmap = load(path)
        state = if (bitmap == null) {
            ImageState.ERROR("Bitmap failed during load")
        } else {
            ImageState.SHOWING
        }
    }

    bitmap?.let {
        Image(
            bitmap = it,
            contentDescription = contentDescription,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
            filterQuality = filterQuality,
            modifier = modifier
        )
    } ?: if (placeholderEnabled) { BasicImagePlaceHolder(modifier)} else {}
}

/**
 * A composable that lays out and asynchronously draws a [ImageBitmap] from a given [BasicUrl]
 * using a [LaunchedEffect].
 *
 * Example:
 * ```kotlin
 * @Composable
 * fun Test() {
 *     // Loads an image via URL location
 *     BasicImage(url = BasicUrl("https://picsum.photos/200"))
 * }
 * ```
 *
 * @param url The [BasicUrl] to obtain the PNG, JPEG, or WEBP file
 * @param contentDescription text used by accessibility services to describe what this image
 * represents.
 * @param modifier Modifier used to adjust the layout algorithm or draw decoration content (ex.
 * background)
 * @param alignment Optional alignment parameter used to place the [ImageBitmap] in the given
 * bounds defined by the width and height
 * @param contentScale Optional scale parameter used to determine the aspect ratio scaling to be used
 * if the bounds are a different size from the intrinsic size of the [ImageBitmap]
 * @param alpha Optional opacity to be applied to the [ImageBitmap] when it is rendered onscreen
 * @param colorFilter Optional ColorFilter to apply for the [ImageBitmap] when it is rendered
 * onscreen
 * @param filterQuality Sampling algorithm applied to the [ImageBitmap] when it is scaled and drawn
 * into the destination. The default is [FilterQuality.Low] which scales using a bilinear
 * sampling algorithm
 * @param placeholderEnabled Boolean value to determine if the image should be preceded by a
 * circular progress indicator while loading.
 */
@ExperimentalBasicImages
@Composable
public fun BasicImage(
    url: BasicUrl,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DefaultFilterQuality,
    placeholderEnabled: Boolean = true
) {
    var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var state by remember { mutableStateOf<ImageState>(ImageState.NONE) }

    LaunchedEffect(url) {
        state = ImageState.LOADING
        bitmap = load(url)
        state = if (bitmap == null) {
            ImageState.ERROR("Bitmap failed during load")
        } else {
            ImageState.SHOWING
        }
    }

    bitmap?.let {
        Image(
            bitmap = it,
            contentDescription = contentDescription,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
            filterQuality = filterQuality,
            modifier = modifier
        )
    } ?: if (placeholderEnabled) { BasicImagePlaceHolder(modifier)} else {}
}

/**
 * A composable that displays while waiting on a [BasicImage] to load.
 */
@ExperimentalBasicImages
@Composable
public fun BasicImagePlaceHolder(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        CircularProgressIndicator()
    }
}