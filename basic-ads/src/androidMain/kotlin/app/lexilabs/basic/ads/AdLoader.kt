package app.lexilabs.basic.ads

import android.content.Context
import androidx.annotation.RequiresPermission
import app.lexilabs.basic.logging.Log

public actual typealias AdRequest = com.google.android.gms.ads.AdRequest
public typealias InterstitialAd = com.google.android.gms.ads.interstitial.InterstitialAd

public actual object AdLoader {

    public const val TAG: String = "AdLoader"

    @RequiresPermission("android.permission.INTERNET")
    public actual fun requestAd(): AdRequest =
        com.google.android.gms.ads.AdRequest.Builder().build()

    @RequiresPermission("android.permission.INTERNET")
    public actual fun loadInterstitialAd(
        context: Any?, adUnitId: String
    ): InterstitialAd? {
        var holder: com.google.android.gms.ads.interstitial.InterstitialAd? = null
        com.google.android.gms.ads.interstitial.InterstitialAd
            .load(
                context as Context,
                adUnitId,
                requestAd(),
                object : com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: com.google.android.gms.ads.LoadAdError) {
                Log.d(TAG, "loadInterstitialAd:failure:$adError")
            }

            override fun onAdLoaded(interstitialAd: com.google.android.gms.ads.interstitial.InterstitialAd) {
                Log.d(TAG, "loadInterstitialAd:success")
                holder = interstitialAd
            }
        })
        holder?.let {
            holder = setFullScreenCallbacks(it)
        }
        return holder
    }

    private fun setFullScreenCallbacks(
        ad: com.google.android.gms.ads.interstitial.InterstitialAd
    ): InterstitialAd? {

        var holder: com.google.android.gms.ads.interstitial.InterstitialAd? = ad

        holder?.fullScreenContentCallback =
            object: com.google.android.gms.ads.FullScreenContentCallback() {

                override fun onAdClicked() {
                    // Called when a click is recorded for an ad.
                    Log.d(TAG, "Ad was clicked.")
                }

                override fun onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    Log.d(TAG, "Ad dismissed fullscreen content.")
                    holder = null
                }

                override fun onAdFailedToShowFullScreenContent(p0: com.google.android.gms.ads.AdError) {
                    // Called when ad fails to show.
                    Log.e(TAG, "Ad failed to show fullscreen content.")
                    holder = null
                }

                override fun onAdImpression() {
                    // Called when an impression is recorded for an ad.
                    Log.d(TAG, "Ad recorded an impression.")
                }

                override fun onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                    Log.d(TAG, "Ad showed fullscreen content.")
                }
            }
        return holder
    }
}