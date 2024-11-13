package app.lexilabs.basic.ads

public actual object AdUnitId {
    public actual fun autoSelect(androidAdUnitId: String?, iosAdUnitId: String?, jsAdUnitId: String?): String {
        return iosAdUnitId ?: ""
    }
}