package app.lexilabs.basic.ads

import cocoapods.Google_Mobile_Ads_SDK.GADRequest
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
public actual typealias AdRequest = GADRequest

@OptIn(ExperimentalForeignApi::class)
public actual class AdLoader {

    public actual fun requestAd(): AdRequest = GADRequest()

    public actual fun loadInterstitialAd(activity: Any?, adUnitId: String, onLoaded: () -> Unit) { TODO() }

    public actual fun showInterstitialAd(activity: Any?, onDismissed: () -> Unit){ TODO() }

    public actual fun loadRewardedInterstitialAd(activity: Any?, adUnitId: String, onLoaded: () -> Unit) { TODO() }

    public actual fun showRewardedInterstitialAd(activity: Any?, onDismissed: () -> Unit, onRewardEarned: () -> Unit) { TODO() }
}