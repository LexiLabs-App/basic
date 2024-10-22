package app.lexilabs.basic.ads

import android.content.Context
import androidx.annotation.RequiresPermission

public actual typealias AdError = com.google.android.gms.ads.AdError
//public actual typealias InitializationStatus = com.google.android.gms.ads.initialization.InitializationStatus
public typealias AdView = com.google.android.gms.ads.AdView

public actual object BasicAds {

    public actual val errorDomain: String
        get() = com.google.android.gms.ads.MobileAds.ERROR_DOMAIN

    public actual var configuration: RequestConfiguration
        get() = com.google.android.gms.ads.MobileAds.getRequestConfiguration().toCommon()
        set(config) = com.google.android.gms.ads.MobileAds.setRequestConfiguration(config.toAndroid())

    public actual val version: String = com.google.android.gms.ads.MobileAds.getVersion().toString()

    // TODO: Verify that this works
    public actual val initialized: Boolean
        get() = com.google.android.gms.ads.MobileAds.getInitializationStatus()?.adapterStatusMap?.isNotEmpty() ?: false

    @RequiresPermission("android.permission.INTERNET")
    public actual fun initialize(context: Any?) {
        com.google.android.gms.ads.MobileAds.initialize(context as Context)
    }

    public actual fun disableMediationAdapterInitialization(context: Any?) {
        com.google.android.gms.ads.MobileAds.disableMediationAdapterInitialization(context as Context)
    }

    public actual fun openDebugMenu(context: Any?, adUnitId: String) {
        com.google.android.gms.ads.MobileAds.openDebugMenu(context as Context, adUnitId)
    }

    public actual fun setAppMuted(muted: Boolean) {
        com.google.android.gms.ads.MobileAds.setAppMuted(muted)
    }

    public actual fun setAppVolume(volume: Float) {
        com.google.android.gms.ads.MobileAds.setAppVolume(volume)
    }

//    public actual fun getInitializationStatus(): InitializationStatus? =
//        com.google.android.gms.ads.MobileAds.getInitializationStatus()
}
