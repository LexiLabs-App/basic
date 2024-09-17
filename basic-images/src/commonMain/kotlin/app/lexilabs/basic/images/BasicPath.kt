package app.lexilabs.basic.images

/**
 * Used to convert the [String] of an absolute file path to an [BasicPath] object.
 * This was a critical feature to prevent URL Strings from being confused with Path Strings.
 *
 * Example:
 * ```kotlin
 * val path = BasicPath("appLocalDirectory/cacheDirectory/images/exampleImage.jpeg")
 * println(path.toString())
 * ```
 */
@ExperimentalBasicImages
public class BasicPath(pathString: String) {
    private val path: String = pathString

    /**
     * Returns the Path [String] originally passed to the [BasicPath] object.
     */
    override fun toString(): String {
        return path
    }
}