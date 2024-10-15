package app.lexilabs.basic.ads

import androidx.annotation.RequiresPermission
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.viewinterop.AndroidView

@RequiresPermission("android.permission.INTERNET")

@Composable
public actual fun BannerAd(adId: String, adSize: AdSize) {
//    DisposableEffect(Unit) {
//        onDispose {
//            TODO()
//        }
//    }
    AndroidView(
        factory = { context ->
            val adView = AdView(context)
            adView.apply {
                setAdSize(adSize.toAndroid())
                adUnitId = adId
                loadAd(requestAd())
            }
        }
    )
}

@RequiresPermission("android.permission.INTERNET")
@Composable
public actual fun InterstitialAd(adId: String, adSize: AdSize) { TODO() }

@RequiresPermission("android.permission.INTERNET")
@Composable
public actual fun RewardedAd(adId: String, adSize: AdSize) { TODO() }

@RequiresPermission("android.permission.INTERNET")
@Composable
public actual fun RewardedInterstitialAd(adId: String, adSize: AdSize) { TODO() }

@RequiresPermission("android.permission.INTERNET")
@Composable
public actual fun AppOpenAd(adId: String, adSize: AdSize) { TODO() }

private fun requestAd() = com.google.android.gms.ads.AdRequest.Builder().build()