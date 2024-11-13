package app.lexilabs.basic.ads

import androidx.compose.runtime.Composable

/**
 * Loads and displays a Banner Ad using a [Composable].
 * @param adUnitId Your AdMob AdUnitId [String]
 * @param adSize Your AdMob [AdSize]
 * @see AdUnitId.autoSelect
 */
@DependsOnGoogleMobileAds
@Composable public expect fun BannerAd(
    adUnitId: String = "ca-app-pub-3940256099942544/9214589741",
    adSize: AdSize = AdSize.FULL_BANNER
)

/**
 * Loads and displays an Interstitial Ad using a [Composable].
 * @param adUnitId Your AdMob AdUnitId [String]
 * @param adSize Your AdMob [AdSize]
 * @param onDismissed Lambda that executes when the user closes the ad
 * @see AdUnitId.autoSelect
 */
@DependsOnGoogleMobileAds
@Composable public expect fun InterstitialAd(
    activity: Any? = null,
    adUnitId: String = "ca-app-pub-3940256099942544/1033173712",
    onDismissed: () -> Unit = {}
)

/**
 * Loads and displays a Rewarded Ad using a [Composable].
 * @param adUnitId Your AdMob AdUnitId [String]
 * @param adSize Your AdMob [AdSize]
 * @param onDismissed Lambda that executes when the user closes the ad
 * @param onRewardEarned Lambda that executes when the user has earned an ad-related reward
 * @see AdUnitId.autoSelect
 */
@DependsOnGoogleMobileAds
@Composable public expect fun RewardedAd(
    activity: Any? = null,
    adUnitId: String = "ca-app-pub-3940256099942544/5224354917",
    onDismissed: () -> Unit = {},
    onRewardEarned: () -> Unit = {}
)

/**
 * Loads and displays a Rewarded Interstitial Ad using a [Composable].
 * @param adUnitId Your AdMob AdUnitId [String]
 * @param adSize Your AdMob [AdSize]
 * @param onDismissed Lambda that executes when the user closes the ad
 * @param onRewardEarned Lambda that executes when the user has earned an ad-related reward
 * @see AdUnitId.autoSelect
 */
@DependsOnGoogleMobileAds
@Composable public expect fun RewardedInterstitialAd(
    activity: Any? = null,
    adUnitId: String = "ca-app-pub-3940256099942544/5354046379",
    onDismissed: () -> Unit = {},
    onRewardEarned: () -> Unit = {}
)