package app.lexilabs.basic.ads

public expect object AdUnitId {
    public fun autoSelect(androidAdUnitId: String? = null, iosAdUnitId: String? = null, jsAdUnitId: String? = null): String?
}