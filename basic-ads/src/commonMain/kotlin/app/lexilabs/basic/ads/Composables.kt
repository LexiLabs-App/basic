package app.lexilabs.basic.ads

import androidx.compose.runtime.Composable

@DependsOnGoogleMobileAds
@Composable public expect fun BannerAd(
    adId: String = "ca-app-pub-3940256099942544/9214589741",
    adSize: AdSize = AdSize.FULL_BANNER
)

@DependsOnGoogleMobileAds
@Composable public expect fun InterstitialAd(
    activity: Any? = null,
    adUnitId: String = "ca-app-pub-3940256099942544/1033173712",
    onDismissed: () -> Unit = {}
)

@DependsOnGoogleMobileAds
@Composable public expect fun RewardedAd(
    activity: Any? = null,
    adId: String = "ca-app-pub-3940256099942544/5224354917",
    onDismissed: () -> Unit = {},
    onRewardEarned: () -> Unit = {}
)

@DependsOnGoogleMobileAds
@Composable public expect fun RewardedInterstitialAd(
    activity: Any? = null,
    adId: String = "ca-app-pub-3940256099942544/5354046379",
    onDismissed: () -> Unit = {},
    onRewardEarned: () -> Unit = {}
)