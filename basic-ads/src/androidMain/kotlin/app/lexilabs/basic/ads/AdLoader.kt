package app.lexilabs.basic.ads

import android.annotation.SuppressLint
import android.app.Activity
import androidx.annotation.RequiresPermission
import app.lexilabs.basic.logging.Log

public actual typealias AdRequest = com.google.android.gms.ads.AdRequest

public actual class AdLoader {

    private val tag: String = "AdLoader"
    private var interstitialAd: com.google.android.gms.ads.interstitial.InterstitialAd? = null
    private var interstitialAdUnitId: String = ""

    @RequiresPermission("android.permission.INTERNET")
    public actual fun requestAd(): AdRequest =
        com.google.android.gms.ads.AdRequest.Builder().build()

    @RequiresPermission("android.permission.INTERNET")
    public actual fun loadInterstitialAd(
        activity: Any?,
        adUnitId: String,
        onLoaded: () -> Unit
    ) {
        interstitialAdUnitId = adUnitId
        com.google.android.gms.ads.interstitial.InterstitialAd
            .load(
                activity as Activity,
                adUnitId,
                requestAd(),
                object : com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: com.google.android.gms.ads.LoadAdError) {
                super.onAdFailedToLoad(adError)
                Log.d(tag, "loadInterstitialAd:failure:$adError")
            }

            override fun onAdLoaded(ad: com.google.android.gms.ads.interstitial.InterstitialAd) {
                super.onAdLoaded(ad)
                Log.d(tag, "loadInterstitialAd:success")
                interstitialAd = ad
                onLoaded()
            }
        })
    }

    @SuppressLint("MissingPermission")
    @RequiresPermission("android.permission.INTERNET")
    public actual fun showInterstitialAd(activity: Any?, onDismissed: () -> Unit){
        if (interstitialAd != null) {
            interstitialAd?.fullScreenContentCallback = object: com.google.android.gms.ads.FullScreenContentCallback() {
                override fun onAdClicked() {
                    super.onAdClicked()
                    // Called when a click is recorded for an ad.
                    Log.d(tag, "Ad was clicked.")
                    loadInterstitialAd(activity, interstitialAdUnitId)
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    // Called when ad is dismissed.
                    Log.d(tag, "Ad dismissed fullscreen content.")
                    interstitialAd = null
                    loadInterstitialAd(activity, interstitialAdUnitId)
                }

                override fun onAdFailedToShowFullScreenContent(p0: com.google.android.gms.ads.AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                    // Called when ad fails to show.
                    Log.e(tag, "Ad failed to show fullscreen content.")
                    interstitialAd = null
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    // Called when an impression is recorded for an ad.
                    Log.d(tag, "Ad recorded an impression.")
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                    // Called when ad is shown.
                    Log.d(tag, "Ad showed fullscreen content.")
                }
            }
            // CONTINUE
            (interstitialAd as com.google.android.gms.ads.interstitial.InterstitialAd).show(activity as Activity)
        } else {
            Log.d(tag, "The interstitial ad wasn't ready yet.")
        }
    }
}