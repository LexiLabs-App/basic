package app.lexilabs.basic.logging

public actual object Log : Logger {
    actual override fun v(tag: String, message: String) {
        console.log("VERBOSE: [$tag] $message")
    }

    actual override fun d(tag: String, message: String) {
        console.log("DEBUG: [$tag] $message")
    }

    actual override fun i(tag: String, message: String) {
        console.log("INFO: [$tag] $message")
    }

    actual override fun w(tag: String, message: String) {
        console.log("WARN: [$tag] $message")
    }

    actual override fun e(tag: String, message: String) {
        console.log("ERROR: [$tag] $message")
    }
    actual override fun wtf(tag: String, message: String) {
        console.log("WHAT-THE-F***: [$tag] $message")
    }
}