package app.lexilabs.basic.logging

public interface Logger {
    public fun v(tag: String, message: String)
    public fun d(tag: String, message: String)
    public fun i(tag: String, message: String)
    public fun w(tag: String, message: String)
    public fun e(tag: String, message: String)
    public fun wtf(tag: String, message: String)
}