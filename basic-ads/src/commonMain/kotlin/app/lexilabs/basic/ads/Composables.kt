package app.lexilabs.basic.ads

import androidx.compose.runtime.Composable

@DependsOnGoogleMobileAds
@Composable public expect fun BannerAd(adId: String, adSize: AdSize)

// TODO: Create an activity when building an interstitial ad
@DependsOnGoogleMobileAds
@Composable public expect fun InterstitialAd(context: Any?, adUnitId: String, onDismissed: () -> Unit = {})

@DependsOnGoogleMobileAds
@Composable public expect fun RewardedAd(adId: String, adSize: AdSize)

@DependsOnGoogleMobileAds
@Composable public expect fun RewardedInterstitialAd(adId: String, adSize: AdSize)

@DependsOnGoogleMobileAds
@Composable public expect fun AppOpenAd(adId: String, adSize: AdSize)