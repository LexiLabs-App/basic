package app.lexilabs.basic.ads

import androidx.annotation.RequiresPermission
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@RequiresPermission("android.permission.INTERNET")
@Composable
public actual fun BannerAd(adId: String, adSize: AdSize) {
    AndroidView(
        factory = { context ->
            val adView = AdView(context)
            adView.apply {
                setAdSize(adSize.toAndroid())
                adUnitId = adId
                loadAd(AdLoader().requestAd())
            }
        }
    )
}

@RequiresPermission("android.permission.INTERNET")
@Composable
public actual fun InterstitialAd(activity: Any?, adUnitId: String, onDismissed: () -> Unit) {
    val adLoader = AdLoader()
    adLoader.loadInterstitialAd(
        activity,
        adUnitId,
        onLoaded = {
            adLoader.showInterstitialAd(activity, onDismissed)
        }
    )
}

@RequiresPermission("android.permission.INTERNET")
@Composable
public actual fun RewardedAd(activity: Any?, adId: String, onDismissed: () -> Unit, onRewardEarned: () -> Unit) {
    val adLoader = AdLoader()
    adLoader.loadRewardedAd(
        activity = activity,
        adUnitId = adId,
        onLoaded = {
            adLoader.showRewardedAd(
                activity = activity,
                onDismissed = onDismissed,
                onRewardEarned = onRewardEarned
            )
        }
    )
}

@RequiresPermission("android.permission.INTERNET")
@Composable
public actual fun RewardedInterstitialAd(activity: Any?, adId: String, onDismissed: () -> Unit, onRewardEarned: () -> Unit) {
    val adLoader = AdLoader()
    adLoader.loadRewardedInterstitialAd(
        activity = activity,
        adUnitId = adId,
        onLoaded = {
            adLoader.showRewardedInterstitialAd(
                activity = activity,
                onDismissed = onDismissed,
                onRewardEarned = onRewardEarned
            )
        }
    )
}