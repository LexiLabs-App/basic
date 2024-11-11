package app.lexilabs.basic.ads

public object AdUnitId {
    public actual fun autoSelect(androidAdUnitId: String?, iosAdUnitId: String?, jsAdUnitId: String?): String? {
        return iosAdUnitId
    }
}