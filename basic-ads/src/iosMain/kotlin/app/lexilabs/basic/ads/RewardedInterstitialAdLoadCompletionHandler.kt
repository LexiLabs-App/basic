package app.lexilabs.basic.ads

import cocoapods.Google_Mobile_Ads_SDK.GADRewardedInterstitialAd
import cocoapods.Google_Mobile_Ads_SDK.GADRewardedInterstitialAdLoadCompletionHandler
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError

@OptIn(ExperimentalForeignApi::class)
public class RewardedInterstitialAdLoadCompletionHandler(
    private var onLoad: (GADRewardedInterstitialAd) -> Unit,
    private var onError: (NSError) -> Unit
): GADRewardedInterstitialAdLoadCompletionHandler {
    override fun invoke(p1: GADRewardedInterstitialAd?, p2: NSError?) {
        p1?.let { onLoad(it) } ?: p2?.let { onError(it) }
    }
}