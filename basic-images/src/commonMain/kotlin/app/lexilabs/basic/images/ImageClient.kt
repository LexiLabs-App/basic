package app.lexilabs.basic.images

import app.lexilabs.basic.logging.Log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsChannel
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.readRemaining
import kotlinx.io.readByteArray

/**
 * Gets and caches an image file by url[String] after [invoke] of [ImageClient] instance.
 *
 * Example:
 * ```
 * val image = ImageClient(urlString) // instantiate and request response from URL
 * ```
 */
@ExperimentalBasicImages
public object ImageClient {

    private const val TAG = "ImageClient"

    private val client = HttpClient {
        install(HttpTimeout) {
            requestTimeoutMillis = 1000
        }
        install(HttpCache) {
//            val cacheFile = Files.createDirectories(Paths.get("build/cache")).toFile()
//            privateStorage(FileStorage(cacheFile)) // You have to create a custom class using interface [CacheStorage]
        }
    }

    /**
     * [invoke] requests a [HttpResponse] from a url using the [ImageClient].
     *
     * Example:
     * ```
     * val image = ImageClient(urlString) // instantiate and request response from URL
     * ```
     */
    public suspend operator fun invoke(urlString: String): ByteArray? {
        return try {
            val response: HttpResponse = client.get(urlString)
            when (response.status.value) {
                200 -> {
                    /** OK **/
                    Log.d(TAG, "Response [${response.status.value}]: OK - $urlString")
                    response.bodyAsChannel().readRemaining().readByteArray()
                }

                404 -> {
                    /** Not Found **/
                    Log.e(TAG, "Response [${response.status.value}]: Not Found - $urlString")
                    null
                }

                429 -> {
                    /** Rate Limit **/
                    Log.e(TAG, "Response [${response.status.value}]: Rate Limit - $urlString")
                    null
                }

                500 -> {
                    /** Server Error **/
                    Log.e(TAG, "Response [${response.status.value}]: Server Error - $urlString")
                    null
                }

                else -> {
                    /** Unknown Error **/
                    Log.e(TAG, "Response [${response.status.value}]: ${response.status.description} - $urlString")
                    null
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            null
        }
    }
}