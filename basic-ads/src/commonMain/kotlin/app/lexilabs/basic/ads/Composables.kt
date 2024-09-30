package app.lexilabs.basic.ads

import androidx.compose.runtime.Composable

@Composable public expect fun BannerAd(adId: String, adSize: AdSize)
@Composable public expect fun InterstitialAd(adId: String, adSize: AdSize)
@Composable public expect fun RewardedAd(adId: String, adSize: AdSize)
@Composable public expect fun RewardedInterstitialAd(adId: String, adSize: AdSize)
@Composable public expect fun AppOpenAd(adId: String, adSize: AdSize)