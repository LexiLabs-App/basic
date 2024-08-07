package app.lexilabs.basic.logging

import platform.Foundation.NSLog

public actual object Log : Logger {
    actual override fun v(tag: String, message: String) {
        NSLog("VERBOSE: [$tag] $message")
    }

    actual override fun d(tag: String, message: String) {
        NSLog("DEBUG: [$tag] $message")
    }

    actual override fun i(tag: String, message: String) {
        NSLog("INFO: [$tag] $message")
    }

    actual override fun w(tag: String, message: String) {
        NSLog("WARN: [$tag] $message")
    }

    actual override fun e(tag: String, message: String) {
        NSLog("ERROR: [$tag] $message")
    }
    actual override fun wtf(tag: String, message: String) {
        NSLog("WHAT-THE-F***: [$tag] $message")
    }
}