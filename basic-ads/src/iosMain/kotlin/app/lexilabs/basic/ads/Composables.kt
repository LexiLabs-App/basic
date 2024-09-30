package app.lexilabs.basic.ads

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import cocoapods.Google_Mobile_Ads_SDK.GADBannerView
import cocoapods.Google_Mobile_Ads_SDK.GADRequest
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readValue

@OptIn(ExperimentalForeignApi::class)
@Composable
public actual fun BannerAd(adId: String, adSize: AdSize) {
    DisposableEffect(Unit) {
        onDispose { TODO() }
    }
    UIKitView(
        factory = {
            val adView = GADBannerView()
            adView.apply {
                setAdSize(adSize.toIos().readValue())
                setAdUnitID(adId)
                loadRequest(requestAd())
            }
        },
        modifier = Modifier
    )
}

@Composable
public actual fun InterstitialAd(adId: String, adSize: AdSize) { TODO() }

@Composable
public actual fun RewardedAd(adId: String, adSize: AdSize) { TODO() }

@Composable
public actual fun RewardedInterstitialAd(adId: String, adSize: AdSize) { TODO() }

@Composable
public actual fun AppOpenAd(adId: String, adSize: AdSize) { TODO() }

@OptIn(ExperimentalForeignApi::class)
private fun requestAd() = GADRequest.request()