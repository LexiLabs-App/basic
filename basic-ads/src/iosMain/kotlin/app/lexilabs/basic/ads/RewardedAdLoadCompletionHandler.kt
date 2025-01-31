package app.lexilabs.basic.ads

import cocoapods.Google_Mobile_Ads_SDK.GADRewardedAd
import cocoapods.Google_Mobile_Ads_SDK.GADRewardedAdLoadCompletionHandler
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError

@OptIn(ExperimentalForeignApi::class)
public class RewardedAdLoadCompletionHandler(
    private var onLoad: (GADRewardedAd) -> Unit,
    private var onError: (NSError) -> Unit
): GADRewardedAdLoadCompletionHandler {
    override fun invoke(p1: GADRewardedAd?, p2: NSError?) {
        p1?.let { onLoad(it) } ?: p2?.let { onError(it) }
    }
}