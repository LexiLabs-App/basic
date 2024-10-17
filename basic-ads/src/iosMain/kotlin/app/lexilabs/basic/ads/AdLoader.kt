package app.lexilabs.basic.ads

import cocoapods.Google_Mobile_Ads_SDK.GADInterstitialAd
import cocoapods.Google_Mobile_Ads_SDK.GADRequest
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
public actual typealias AdRequest = GADRequest
@OptIn(ExperimentalForeignApi::class)
public actual typealias InterstitialAd = GADInterstitialAd

@OptIn(ExperimentalForeignApi::class)
public actual object AdLoader {

    public actual fun requestAd(): AdRequest = GADRequest()

    public actual fun loadInterstitialAd(context: Any?, adUnitId: String): InterstitialAd? { TODO() }
}