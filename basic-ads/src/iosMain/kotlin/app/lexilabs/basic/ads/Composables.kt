package app.lexilabs.basic.ads

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import app.lexilabs.basic.logging.Log
import cocoapods.Google_Mobile_Ads_SDK.GADBannerView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication

@OptIn(ExperimentalForeignApi::class)
@Composable
public actual fun BannerAd(adId: String, adSize: AdSize) {
    Log.d("BannerAd", "composable:starting")
//    DisposableEffect(Unit) {
//        onDispose { TODO() }
//    }
    UIKitView(
        factory = {
            val viewController = UIApplication.sharedApplication.keyWindow?.rootViewController
            checkNotNull(viewController) { "Root ViewController is null" }

            val bannerView = GADBannerView(adSize.toCGRectCValue()).apply {
                adUnitID = adId
                this.rootViewController = viewController
                loadRequest(AdLoader.requestAd())
            }
            bannerView
        },
        modifier = Modifier.size(width = adSize.width.dp, height = adSize.height.dp)
    )
}

@OptIn(ExperimentalForeignApi::class)
@Composable
public actual fun InterstitialAd(loadedAd: InterstitialAd) { TODO() }

@Composable
public actual fun RewardedAd(adId: String, adSize: AdSize) { TODO() }

@Composable
public actual fun RewardedInterstitialAd(adId: String, adSize: AdSize) { TODO() }

@Composable
public actual fun AppOpenAd(adId: String, adSize: AdSize) { TODO() }