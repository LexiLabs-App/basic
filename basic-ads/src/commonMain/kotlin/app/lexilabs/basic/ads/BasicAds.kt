package app.lexilabs.basic.ads

import androidx.annotation.MainThread

@DependsOnGoogleMobileAds
public expect object BasicAds {
    public val errorDomain: String
    public var configuration: RequestConfiguration
    public val version: String
    public val initialized: Boolean
    @MainThread
    public fun initialize(context: Any?)
//    public fun getInitializationStatus(): InitializationStatus?
    public fun disableMediationAdapterInitialization(context: Any?)
    public fun openDebugMenu(context: Any?, adUnitId: String)
    public fun setAppMuted(muted: Boolean)
    public fun setAppVolume(volume: Float)
}

