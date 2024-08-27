package app.lexilabs.basic.images

import androidx.compose.ui.graphics.ImageBitmap

public expect object ImageLoader {
    public suspend fun load(url: BasicUrl): ImageBitmap?
    public suspend fun load(path: BasicPath): ImageBitmap?
}