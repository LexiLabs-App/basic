package app.lexilabs.basic.ads

import app.lexilabs.basic.logging.Log
import cocoapods.Google_Mobile_Ads_SDK.GADFullScreenContentDelegateProtocol
import cocoapods.Google_Mobile_Ads_SDK.GADFullScreenPresentingAdProtocol
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
public class AdFullScreenContent(
    private val onShown: () -> Unit = {},
    private val onDismissed: () -> Unit = {},
    private val onClick: () -> Unit = {},
    private val onImpression: () -> Unit = {},
): NSObject(), GADFullScreenContentDelegateProtocol {

    private val tag = "AdFullScreenContent"

    override fun ad(
        ad: GADFullScreenPresentingAdProtocol,
        didFailToPresentFullScreenContentWithError: NSError
    ) {
        // super.ad(ad, didFailToPresentFullScreenContentWithError)
        Log.d(tag, "ad called")
        onShown()
    }

    override fun adDidDismissFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
        // super.adDidDismissFullScreenContent(ad)
        Log.d(tag, "dismissed")
//        interstitialAd = null
        onDismissed()
    }

    override fun adDidRecordClick(ad: GADFullScreenPresentingAdProtocol) {
        // super.adDidRecordClick(ad)
        Log.d(tag, "recorded click")
        onClick()
    }

    override fun adDidRecordImpression(ad: GADFullScreenPresentingAdProtocol) {
        // super.adDidRecordImpression(ad)
        Log.d(tag, "recorded impression")
        onImpression()
    }

    override fun adWillDismissFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
        // super.adWillDismissFullScreenContent(ad)
        Log.d(tag, "will dismiss")
    }

    override fun adWillPresentFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
        // super.adWillPresentFullScreenContent(ad)
        Log.d(tag, "will present content")
    }
}