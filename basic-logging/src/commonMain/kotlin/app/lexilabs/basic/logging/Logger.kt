package app.lexilabs.basic.logging

import app.lexilabs.basic.logging.Log.wtf

/**
 * A common interface to enable multiplatform logging.
 *
 * Requires override of [v], [d], [i], [w], [e], and [wtf] functions.
 *
 */
public interface Logger {

    /**
     * When overridden, this should extend a logging function
     *
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
    public fun v(tag: String, message: String)

    /**
     * When overridden, this should extend a logging function
     *
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
    public fun d(tag: String, message: String)

    /**
     * When overridden, this should extend a logging function
     *
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
    public fun i(tag: String, message: String)

    /**
     * When overridden, this should extend a logging function
     *
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
    public fun w(tag: String, message: String)

    /**
     * When overridden, this should extend a logging function
     *
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
    public fun e(tag: String, message: String)

    /**
     * When overridden, this should extend a logging function
     *
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
    public fun wtf(tag: String, message: String)
}