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
public actual fun BannerAd(adUnitId: String, adSize: AdSize) {
    UIKitView(
        factory = {
            val viewController = UIApplication.sharedApplication.keyWindow?.rootViewController
            checkNotNull(viewController) { "Root ViewController is null" }

            val bannerView = GADBannerView(adSize.toCGRectCValue()).apply {
                adUnitID = adUnitId
                this.rootViewController = viewController
                loadRequest(AdLoader().requestAd())
            }
            bannerView
        },
        modifier = Modifier.size(width = adSize.width.dp, height = adSize.height.dp)
    )
}

@Composable
public actual fun InterstitialAd(
    activity: Any?,
    adUnitId: String,
    onDismissed: () -> Unit,
    onShown: () -> Unit,
    onImpression: () -> Unit,
    onClick: () -> Unit,
    onFailure: () -> Unit,
) {
    val adLoader = AdLoader()
    adLoader.loadInterstitialAd(
        activity,
        adUnitId,
        onLoaded = {
            adLoader.showInterstitialAd(
                activity = activity,
                onDismissed = { onDismissed() },
                onShown = { onShown() },
                onImpression = { onImpression() },
                onClick = { onClick() },
                onFailure = { onFailure() }
            )
        }
    )
}

@Composable
public actual fun RewardedAd(
    activity: Any?,
    adUnitId: String,
    onDismissed: () -> Unit,
    onRewardEarned: () -> Unit,
    onShown: () -> Unit,
    onImpression: () -> Unit,
    onClick: () -> Unit,
    onFailure: () -> Unit,
) {
    val adLoader = AdLoader()
    adLoader.loadRewardedAd(
        activity = activity,
        adUnitId = adUnitId,
        onLoaded = {
            adLoader.showRewardedAd(
                activity = activity,
                onDismissed = { onDismissed() },
                onRewardEarned = { onRewardEarned() },
                onShown = { onShown() },
                onImpression = { onImpression() },
                onClick = { onClick() },
                onFailure = { onFailure() }
            )
        }
    )
}

@Composable
public actual fun RewardedInterstitialAd(
    activity: Any?,
    adUnitId: String,
    onDismissed: () -> Unit,
    onRewardEarned: () -> Unit,
    onShown: () -> Unit,
    onImpression: () -> Unit,
    onClick: () -> Unit,
    onFailure: () -> Unit,
) {
    val adLoader = AdLoader()
    adLoader.loadRewardedInterstitialAd(
        activity = activity,
        adUnitId = adUnitId,
        onLoaded = {
            adLoader.showRewardedInterstitialAd(
                activity = activity,
                onDismissed = { onDismissed() },
                onRewardEarned = { onRewardEarned() },
                onShown = { onShown() },
                onImpression = { onImpression() },
                onClick = { onClick() },
                onFailure = { onFailure() }
            )
        }
    )
}
