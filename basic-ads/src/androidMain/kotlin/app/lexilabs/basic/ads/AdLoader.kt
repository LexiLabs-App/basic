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
    private var rewardedInterstitialAd: com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd? = null
    private var rewardedInterstitialAdUnitId: String = ""
    private var rewardedAd: com.google.android.gms.ads.rewarded.RewardedAd? = null
    private var rewardedAdUnitId: String = ""

    @RequiresPermission("android.permission.INTERNET")
    public actual fun requestAd(): AdRequest =
        com.google.android.gms.ads.AdRequest.Builder().build()

    @RequiresPermission("android.permission.INTERNET")
    public actual fun loadInterstitialAd(
        activity: Any?,
        adUnitId: String,
        onLoaded: () -> Unit
    ) {
        Log.d(tag, "loadInterstitialAd: Loading")
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
//        if (interstitialAd != null) {
        interstitialAd?.let {
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
        } ?: Log.d(tag, "The interstitial ad wasn't ready yet.")
    }

    @RequiresPermission("android.permission.INTERNET")
    public actual fun loadRewardedInterstitialAd(
        activity: Any?,
        adUnitId: String,
        onLoaded: () -> Unit
    ) {
        rewardedInterstitialAdUnitId = adUnitId
        com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
            .load(
                activity as Activity,
                adUnitId,
                requestAd(),
                object : com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: com.google.android.gms.ads.LoadAdError) {
                        super.onAdFailedToLoad(adError)
                        Log.d(tag, "loadRewardedInterstitialAd:failure:$adError")
                    }

                    override fun onAdLoaded(ad: com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd) {
                        super.onAdLoaded(ad)
                        Log.d(tag, "loadRewardedInterstitialAd:success")
                        rewardedInterstitialAd = ad
                        onLoaded()
                    }
                })
    }

    @SuppressLint("MissingPermission")
    @RequiresPermission("android.permission.INTERNET")
    public actual fun showRewardedInterstitialAd(activity: Any?, onDismissed: () -> Unit, onRewardEarned: () -> Unit){
        rewardedInterstitialAd?.let {
            rewardedInterstitialAd?.fullScreenContentCallback = object: com.google.android.gms.ads.FullScreenContentCallback() {
                override fun onAdClicked() {
                    super.onAdClicked()
                    // Called when a click is recorded for an ad.
                    Log.d(tag, "Ad was clicked.")
                    loadRewardedInterstitialAd(activity, rewardedInterstitialAdUnitId)
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    // Called when ad is dismissed.
                    Log.d(tag, "Ad dismissed fullscreen content.")
                    rewardedInterstitialAd = null
                    loadRewardedInterstitialAd(activity, rewardedInterstitialAdUnitId)
                }

                override fun onAdFailedToShowFullScreenContent(p0: com.google.android.gms.ads.AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                    // Called when ad fails to show.
                    Log.e(tag, "Ad failed to show fullscreen content.")
                    rewardedInterstitialAd = null
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
            (rewardedInterstitialAd as com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd).show(
                activity as Activity
            ) {
                onRewardEarned()
            }
        } ?: Log.d(tag, "The rewarded interstitial ad wasn't ready yet.")
    }

    @RequiresPermission("android.permission.INTERNET")
    public actual fun loadRewardedAd(
        activity: Any?,
        adUnitId: String,
        onLoaded: () -> Unit
    ) {
        rewardedAdUnitId = adUnitId
        com.google.android.gms.ads.rewarded.RewardedAd
            .load(
                activity as Activity,
                adUnitId,
                requestAd(),
                object : com.google.android.gms.ads.rewarded.RewardedAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: com.google.android.gms.ads.LoadAdError) {
                        super.onAdFailedToLoad(adError)
                        Log.d(tag, "loadRewardedInterstitialAd:failure:$adError")
                    }

                    override fun onAdLoaded(ad: com.google.android.gms.ads.rewarded.RewardedAd) {
                        super.onAdLoaded(ad)
                        Log.d(tag, "loadRewardedInterstitialAd:success")
                        rewardedAd = ad
                        onLoaded()
                    }
                })
    }

    @SuppressLint("MissingPermission")
    @RequiresPermission("android.permission.INTERNET")
    public actual fun showRewardedAd(activity: Any?, onDismissed: () -> Unit, onRewardEarned: () -> Unit){
        rewardedAd?.let {
            rewardedAd?.fullScreenContentCallback = object: com.google.android.gms.ads.FullScreenContentCallback() {
                override fun onAdClicked() {
                    super.onAdClicked()
                    // Called when a click is recorded for an ad.
                    Log.d(tag, "Ad was clicked.")
                    loadRewardedAd(activity, rewardedAdUnitId)
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    // Called when ad is dismissed.
                    Log.d(tag, "Ad dismissed fullscreen content.")
                    rewardedAd = null
                    loadRewardedAd(activity, rewardedAdUnitId)
                }

                override fun onAdFailedToShowFullScreenContent(p0: com.google.android.gms.ads.AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                    // Called when ad fails to show.
                    Log.e(tag, "Ad failed to show fullscreen content.")
                    rewardedAd = null
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
            (rewardedAd as com.google.android.gms.ads.rewarded.RewardedAd).show(
                activity as Activity
            ) {
                onRewardEarned()
            }
        } ?: Log.d(tag, "The rewarded interstitial ad wasn't ready yet.")
    }
}