package app.lexilabs.basic.ads

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import cocoapods.Google_Mobile_Ads_SDK.GADBannerView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication

@OptIn(ExperimentalForeignApi::class)
@Composable
public actual fun BannerAd(adId: String, adSize: AdSize) {
    UIKitView(
        factory = {
            val viewController = UIApplication.sharedApplication.keyWindow?.rootViewController
            checkNotNull(viewController) { "Root ViewController is null" }

            val bannerView = GADBannerView(adSize.toCGRectCValue()).apply {
                adUnitID = adId
                this.rootViewController = viewController
                loadRequest(AdLoader().requestAd())
            }
            bannerView
        },
        modifier = Modifier.size(width = adSize.width.dp, height = adSize.height.dp)
    )
}

@Composable
public actual fun InterstitialAd(activity: Any?, adUnitId: String, onDismissed: () -> Unit) {
    val adLoader = AdLoader()
    adLoader.loadInterstitialAd(
        activity,
        adUnitId,
        onLoaded = {
            adLoader.showInterstitialAd(activity) { onDismissed() }
        }
    )
}

@Composable
public actual fun RewardedAd(activity: Any?, adId: String, onDismissed: () -> Unit, onRewardEarned: () -> Unit) {
    val adLoader = AdLoader()
    adLoader.loadRewardedAd(
        activity = activity,
        adUnitId = adId,
        onLoaded = {
            adLoader.showRewardedAd(
                activity = activity,
                onDismissed = { onDismissed() },
                onRewardEarned = { onRewardEarned() }
            )
        }
    )
}

@Composable
public actual fun RewardedInterstitialAd(activity: Any?, adId: String, onDismissed: () -> Unit, onRewardEarned: () -> Unit) {
    val adLoader = AdLoader()
    adLoader.loadRewardedInterstitialAd(
        activity = activity,
        adUnitId = adId,
        onLoaded = {
            adLoader.showRewardedInterstitialAd(
                activity = activity,
                onDismissed = { onDismissed() },
                onRewardEarned = { onRewardEarned() }
            )
        }
    )
}
