package app.lexilabs.basic.ads

/**
 * An object used to hold functions related to AdMob AdUnitIds
 * @see AdUnitId.autoSelect
 */
public expect object AdUnitId {
    /**
     * Provides a way of selecting an AdMob AdUnitId by platform during runtime.
     * This function works for any ad type.
     * @param androidAdUnitId provide an AdUnitId [String] for Android implementation
     * @param iosAdUnitId provide an AdUnitId [String] for iOS implementation
     */
    public fun autoSelect(androidAdUnitId: String? = null, iosAdUnitId: String? = null): String
}