package app.lexilabs.basic.logging

import platform.Foundation.NSLog

public actual object Log : Logger {
    actual override fun v(tag: String, message: String) {
        NSLog("[$tag] VERBOSE: $message")
    }

    actual override fun d(tag: String, message: String) {
        NSLog("[$tag] DEBUG: $message")
    }

    actual override fun i(tag: String, message: String) {
        NSLog("[$tag] INFO: $message")
    }

    actual override fun w(tag: String, message: String) {
        NSLog("[$tag] WARN: $message")
    }

    actual override fun e(tag: String, message: String) {
        NSLog("[$tag] ERROR: $message")
    }
    actual override fun wtf(tag: String, message: String) {
        NSLog("[$tag] WHAT-THE-F***: $message")
    }
}