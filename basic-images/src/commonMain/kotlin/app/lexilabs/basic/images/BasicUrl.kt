package app.lexilabs.basic.images

/**
 * Used to convert the [String] of an absolute file path to an [BasicUrl] object.
 * This was a critical feature to prevent URL Strings from being confused with Path Strings.
 *
 * Example:
 * ```kotlin
 * val url = BasicUrl("https://picsum.photos/200")
 * println(url.toString())
 * ```
 */
@ExperimentalBasicImages
public class BasicUrl(urlString: String) {
    private val url: String = urlString

    /**
     * Returns the URL [String] originally passed to the [BasicUrl] object.
     */
    override fun toString(): String {
        return url
    }
}