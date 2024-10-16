package app.lexilabs.basic.ads

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.UIKitView
import app.lexilabs.basic.logging.Log
import cocoapods.Google_Mobile_Ads_SDK.GADBannerView
import cocoapods.Google_Mobile_Ads_SDK.GADRequest
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readValue
import platform.UIKit.UIApplication
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
@Composable
public actual fun BannerAd(adId: String, adSize: AdSize) {
    Log.d("BannerAd", "starting")
//    DisposableEffect(Unit) {
//        onDispose { TODO() }
//    }
    UIKitView(
        factory = {
            val bannerView = GADBannerView(adSize.toIos().readValue()).apply {
                adUnitID = adId
                rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
                if (rootViewController == null) {
                    Log.d("BannerAd","rootController=null")
                }
                loadRequest(GADRequest())
            }
            Log.d("BannerAd","finished")
            bannerView
        },
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
