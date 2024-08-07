package app.lexilabs.basic.logging

public expect object Log : Logger {
    override fun v(tag: String, message: String)
    override fun i(tag: String, message: String)
    override fun w(tag: String, message: String)
    override fun d(tag: String, message: String)
    override fun e(tag: String, message: String)
    override fun wtf(tag: String, message: String)
}