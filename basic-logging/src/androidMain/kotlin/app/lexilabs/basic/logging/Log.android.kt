package app.lexilabs.basic.logging

public actual object Log : Logger {
    actual override fun v(tag: String, message: String) {
        android.util.Log.v(tag, message)
    }

    actual override fun d(tag: String, message: String) {
        android.util.Log.d(tag, message)
    }

    actual override fun i(tag: String, message: String) {
        android.util.Log.i(tag, message)
    }

    actual override fun w(tag: String, message: String) {
        android.util.Log.w(tag, message)
    }

    actual override fun e(tag: String, message: String) {
        android.util.Log.e(tag, message)
    }

    actual override fun wtf(tag: String, message: String) {
        android.util.Log.wtf(tag, message)
    }
}