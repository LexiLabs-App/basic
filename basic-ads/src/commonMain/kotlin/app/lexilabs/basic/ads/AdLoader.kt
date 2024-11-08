package app.lexilabs.basic.ads

public expect class AdRequest

public expect class AdLoader() {
    public fun requestAd(): AdRequest
    public fun loadInterstitialAd(activity: Any?, adUnitId: String, onLoaded: () -> Unit = {})
    public fun showInterstitialAd(activity: Any?, onDismissed: () -> Unit)
}