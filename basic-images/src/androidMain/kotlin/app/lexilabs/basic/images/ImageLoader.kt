package app.lexilabs.basic.images

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

public actual object ImageLoader {

    public actual suspend fun load(url: BasicUrl): ImageBitmap? {
        var bitmap: ImageBitmap? = null
        return withContext(Dispatchers.IO){
            ImageClient(url.toString())?.let { bitmapByteArray ->
                bitmap = BitmapFactory.decodeByteArray(bitmapByteArray, 0, bitmapByteArray.size).asImageBitmap()
            } ?: {
                bitmap = null
            }
            return@withContext bitmap
        }
    }

    public actual suspend fun load(path: BasicPath): ImageBitmap? {
        var bitmap: ImageBitmap?
        return withContext(Dispatchers.IO){
            val bitmapByteArray = File(path.toString()).readBytes()
            bitmap = BitmapFactory.decodeByteArray(bitmapByteArray, 0, bitmapByteArray.size).asImageBitmap()
            return@withContext bitmap
        }
    }
}