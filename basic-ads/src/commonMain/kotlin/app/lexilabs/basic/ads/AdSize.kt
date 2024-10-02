package app.lexilabs.basic.ads

@DependsOnGoogleMobileAds
public expect class AdSize public constructor(width: Int, height: Int) {

    public val width: Int
    public val height: Int

    public companion object {
        public val FULL_WIDTH: Int
        public val AUTO_HEIGHT: Int
        public val BANNER: AdSize
        public val FULL_BANNER: AdSize
        public val LARGE_BANNER: AdSize
        public val LEADERBOARD: AdSize
        public val MEDIUM_RECTANGLE: AdSize
        public val WIDE_SKYSCRAPER: AdSize
        public val FLUID: AdSize
        public val INVALID: AdSize
        public val SEARCH: AdSize
    }
}