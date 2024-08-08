package app.lexilabs.basic.logging

public actual object Log : Logger {
    actual override fun v(tag: String, message: String) {
        println("VERBOSE: [$tag] $message")
    }

    actual override fun d(tag: String, message: String) {
        println("DEBUG: [$tag] $message")
    }

    actual override fun i(tag: String, message: String) {
        println("INFO: [$tag] $message")
    }

    actual override fun w(tag: String, message: String) {
        println("WARN: [$tag] $message")
    }

    actual override fun e(tag: String, message: String) {
        println("ERROR: [$tag] $message")
    }
    actual override fun wtf(tag: String, message: String) {
        println("WHAT-THE-F***: [$tag] $message")
    }
}