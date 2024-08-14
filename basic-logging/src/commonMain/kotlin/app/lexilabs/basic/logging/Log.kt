package app.lexilabs.basic.logging

/**
 *
 * An easy-to-use method of logging.
 *
 * Use [Log.v], [Log.d], [Log.i], [Log.w], [Log.e], and [Log.wtf] methods to write to logcat.
 *
 * @param[tag] typically the name of the parent class.
 * @param[message] whatever string of information you'd like to pass.
 *
 * ```kotlin
 * class RandomClass() {
 *
 *   val tag = "RandomClass"
 *
 *   fun test() {
 *     Log.v(tag, "it works!")
 *   }
 * }
 * ```
 *
 */
public expect object Log : Logger {
    /**
     * Send logcat the least important message possible.
     *
     * @param[tag] typically the name of the parent class.
     * @param[message] whatever string of information you'd like to pass.
     *
     * ```kotlin
     * class RandomClass() {
     *
     *   val tag = "RandomClass"
     *
     *   fun test() {
     *     Log.v(tag, "it works!")
     *   }
     * }
     * ```
     */
    override fun v(tag: String, message: String)

    /**
     * Send logcat a routine message that you want the developer to see.
     *
     * @param[tag] typically the name of the parent class.
     * @param[message] whatever string of information you'd like to pass.
     *
     * ```kotlin
     * class RandomClass() {
     *
     *   val tag = "RandomClass"
     *
     *   fun test() {
     *     Log.i(tag, "it works!")
     *   }
     * }
     * ```
     */
    override fun i(tag: String, message: String)

    /**
     * Send logcat a warning message that indicates depreciated features or risky app behaviour
     *
     * @param[tag] typically the name of the parent class.
     * @param[message] whatever string of information you'd like to pass.
     *
     * ```kotlin
     * class RandomClass() {
     *
     *   val tag = "RandomClass"
     *
     *   fun test() {
     *     Log.w(tag, "it works!")
     *   }
     * }
     * ```
     */
    override fun w(tag: String, message: String)

    /**
     * Send logcat a message that indicates where a fault is occurring
     *
     * @param[tag] typically the name of the parent class.
     * @param[message] whatever string of information you'd like to pass.
     *
     * ```kotlin
     * class RandomClass() {
     *
     *   val tag = "RandomClass"
     *
     *   fun test() {
     *     Log.d(tag, "it works!")
     *   }
     * }
     * ```
     */
    override fun d(tag: String, message: String)

    /**
     * Send logcat an error message that aids the developer in troubleshooting
     *
     * @param[tag] typically the name of the parent class.
     * @param[message] whatever string of information you'd like to pass.
     *
     * ```kotlin
     * class RandomClass() {
     *
     *   val tag = "RandomClass"
     *
     *   fun test() {
     *     Log.e(tag, "it did not work!")
     *   }
     * }
     * ```
     */
    override fun e(tag: String, message: String)

    /**
     * [wtf] (A.K.A. What the F***) Something so inexplicably impossible has occurred that you want to immediately draw as much attention to the error as possible because you don't want to get fired.
     *
     * @param[tag] typically the name of the parent class.
     * @param[message] whatever string of information you'd like to pass.
     *
     * ```kotlin
     * class RandomClass() {
     *
     *   val tag = "RandomClass"
     *
     *   fun thisWillNeverHappen() {
     *     Log.wtf(tag, "BEFORE DEBUG: call that dude from LinkedIn back and accept the job.")
     *   }
     * }
     * ```
     */
    override fun wtf(tag: String, message: String)
}