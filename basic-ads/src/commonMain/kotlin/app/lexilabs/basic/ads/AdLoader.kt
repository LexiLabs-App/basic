package app.lexilabs.basic.ads

public expect class AdRequest
public expect class InterstitialAd

public expect object AdLoader {
    public fun requestAd(): AdRequest
    public fun loadInterstitialAd(context: Any?, adUnitId: String): InterstitialAd?
}