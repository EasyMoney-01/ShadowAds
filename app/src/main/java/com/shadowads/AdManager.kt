package com.shadowads

import android.content.Context
import android.util.Log
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds
import com.unity3d.ads.UnityAds.UnityAdsShowCompletionState

class AdManager(private val context: Context) : IUnityAdsInitializationListener, IUnityAdsShowListener {
    private val TAG = "AdManager"
    private val GAME_ID = "6066543"
    private val PLACEMENT_ID = "Rewarded_Android"
    private var isInitialized = false
    private var adListener: AdListener? = null

    init {
        initializeUnityAds()
    }

    private fun initializeUnityAds() {
        Log.d(TAG, "🎬 Initializing Unity Ads with Game ID: $GAME_ID")
        
        UnityAds.initialize(
            context,
            GAME_ID,
            this,
            BuildConfig.DEBUG
        )
    }

    override fun onInitializationComplete() {
        Log.d(TAG, "✅ Unity Ads Initialized Successfully")
        isInitialized = true
    }

    override fun onInitializationFailed(error: UnityAds.UnityAdsInitializationError?, message: String?) {
        Log.e(TAG, "❌ Unity Ads Initialization Failed: $message")
        isInitialized = false
    }

    fun showRewardedAd(listener: AdListener) {
        this.adListener = listener
        
        if (!isInitialized) {
            Log.e(TAG, "⚠️ Unity Ads not initialized yet")
            listener.onAdFailed("Unity Ads not initialized")
            return
        }

        Log.d(TAG, "📺 Showing Rewarded Ad with Placement: $PLACEMENT_ID")
        
        UnityAds.show(
            context as? android.app.Activity,
            PLACEMENT_ID,
            this
        )
    }

    override fun onUnityAdsShowFailure(
        placementId: String,
        error: UnityAds.UnityAdsShowError,
        message: String
    ) {
        Log.e(TAG, "❌ Ad Show Failed - Placement: $placementId, Error: $error, Message: $message")
        adListener?.onAdFailed("$error: $message")
    }

    override fun onUnityAdsShowStart(placementId: String) {
        Log.d(TAG, "🎬 Ad Started - Placement: $placementId")
        adListener?.onAdStarted()
    }

    override fun onUnityAdsShowClick(placementId: String) {
        Log.d(TAG, "👆 Ad Clicked - Placement: $placementId")
        adListener?.onAdClicked()
    }

    override fun onUnityAdsShowComplete(
        placementId: String,
        state: UnityAdsShowCompletionState
    ) {
        Log.d(TAG, "✅ Ad Completed - Placement: $placementId, State: $state")
        
        if (state == UnityAdsShowCompletionState.COMPLETED) {
            Log.d(TAG, "🎉 Rewarded Ad Completed Successfully")
            adListener?.onAdCompleted()
        } else {
            Log.d(TAG, "⚠️ Ad Skipped")
            adListener?.onAdFailed("Ad was skipped")
        }
    }

    fun destroy() {
        Log.d(TAG, "🛑 Destroying AdManager")
        adListener = null
    }
}
