package app.lexilabs.basic.ads

import androidx.annotation.RequiresPermission
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@RequiresPermission("android.permission.INTERNET")
@Composable
public actual fun BannerAd(adUnitId: String, adSize: AdSize) {
    AndroidView(
        factory = { context ->
            val adView = AdView(context)
            adView.apply {
                this.setAdSize(adSize.toAndroid())
                this.adUnitId = adUnitId
                this.loadAd(AdLoader().requestAd())
            }
        }
    )
}

@RequiresPermission("android.permission.INTERNET")
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

@RequiresPermission("android.permission.INTERNET")
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

@RequiresPermission("android.permission.INTERNET")
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