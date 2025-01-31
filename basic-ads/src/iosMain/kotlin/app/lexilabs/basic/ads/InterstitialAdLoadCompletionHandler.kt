package app.lexilabs.basic.ads

import cocoapods.Google_Mobile_Ads_SDK.GADInterstitialAd
import cocoapods.Google_Mobile_Ads_SDK.GADInterstitialAdLoadCompletionHandler
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError

@OptIn(ExperimentalForeignApi::class)
public class InterstitialAdLoadCompletionHandler(
    private var onLoad: (GADInterstitialAd) -> Unit,
    private var onError: (NSError) -> Unit
): GADInterstitialAdLoadCompletionHandler {
    override fun invoke(p1: GADInterstitialAd?, p2: NSError?) {
        p1?.let { onLoad(it) } ?: p2?.let { onError(it) }
    }
}