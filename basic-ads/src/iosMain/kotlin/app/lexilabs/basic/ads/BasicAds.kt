package app.lexilabs.basic.ads

import cocoapods.Google_Mobile_Ads_SDK.GADErrorDomain
import cocoapods.Google_Mobile_Ads_SDK.GADInitializationStatus
import cocoapods.Google_Mobile_Ads_SDK.GADMobileAds
import cocoapods.Google_Mobile_Ads_SDK.GADPublisherPrivacyPersonalizationState
import cocoapods.Google_Mobile_Ads_SDK.GADRequestConfiguration
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSNumber

//@OptIn(ExperimentalForeignApi::class)
//public actual typealias InitializationStatus = GADInitializationStatus

public actual object BasicAds {

    @OptIn(ExperimentalForeignApi::class)
    public actual val errorDomain: String
        get() = GADErrorDomain

    @OptIn(ExperimentalForeignApi::class)
    public actual var configuration: RequestConfiguration
        get() = GADMobileAds.sharedInstance().requestConfiguration().toCommon()
        set(config) { config.setConfigurationForIos() }

    public actual val version: String
        get() = TODO("Not yet implemented")
    public actual val initialized: Boolean
        get() = TODO("Not yet implemented")

    public actual fun initialize(context: Any?) {
    }

//    @OptIn(ExperimentalForeignApi::class)
//    public actual fun getInitializationStatus(): InitializationStatus? =
//        GADMobileAds.sharedInstance().initializationStatus

    @OptIn(ExperimentalForeignApi::class)
    public actual fun disableMediationAdapterInitialization(context: Any?) {
        GADMobileAds.sharedInstance().disableMediationInitialization()
    }

    public actual fun openDebugMenu(context: Any?, adUnitId: String) {
        // DO NOTHING. This doesn't exist in iOS
    }

    @OptIn(ExperimentalForeignApi::class)
    public actual fun setAppMuted(muted: Boolean) {
        GADMobileAds.sharedInstance().setApplicationMuted(muted)
    }

    @OptIn(ExperimentalForeignApi::class)
    public actual fun setAppVolume(volume: Float) {
        GADMobileAds.sharedInstance().setApplicationVolume(volume)
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun RequestConfiguration.setConfigurationForIos() {
    GADMobileAds.sharedInstance().requestConfiguration.let {
        it.setMaxAdContentRating(this.maxAdContentRating)
        it.setPublisherPrivacyPersonalizationState(this.publisherPrivacyPersonalizationState.toIos())
        it.setTagForUnderAgeOfConsent(NSNumber(this.tagForUnderAgeOfConsent))
        it.setTagForChildDirectedTreatment(NSNumber(this.tagForChildDirectedTreatment))
        it.setTestDeviceIdentifiers(this.testDeviceIds)
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun GADRequestConfiguration.toCommon(): RequestConfiguration =
    RequestConfiguration(
        maxAdContentRating = this.maxAdContentRating,
        publisherPrivacyPersonalizationState = this.publisherPrivacyPersonalizationState.toCommon(),
        tagForChildDirectedTreatment = this.tagForChildDirectedTreatment?.intValue ?: 0,
        tagForUnderAgeOfConsent = this.tagForUnderAgeOfConsent?.intValue ?: 0,
        testDeviceIds = this.testDeviceIdentifiers?.map { it.toString() }
    )

private fun RequestConfiguration.PublisherPrivacyPersonalizationState.toIos(): GADPublisherPrivacyPersonalizationState =
    this.ordinal.toLong()

private fun GADPublisherPrivacyPersonalizationState.toCommon(): RequestConfiguration.PublisherPrivacyPersonalizationState =
    RequestConfiguration.PublisherPrivacyPersonalizationState.fromInt(this.toInt())
