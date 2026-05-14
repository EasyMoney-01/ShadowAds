package com.shadowads

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AdListener {
    private val TAG = "MainActivity"
    private lateinit var adManager: AdManager
    private lateinit var statusText: TextView
    private lateinit var descriptionText: TextView
    private lateinit var adsCountText: TextView
    private lateinit var sessionTimeText: TextView
    private lateinit var playAdButton: Button
    private lateinit var stopButton: Button

    private var adsCount = 0
    private var sessionStartTime = 0L
    private var isAutoPlayEnabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "🚀 MainActivity Created")

        // Initialize views
        statusText = findViewById(R.id.statusText)
        descriptionText = findViewById(R.id.descriptionText)
        adsCountText = findViewById(R.id.adsCountText)
        sessionTimeText = findViewById(R.id.sessionTimeText)
        playAdButton = findViewById(R.id.playAdButton)
        stopButton = findViewById(R.id.stopButton)

        // Initialize AdManager
        adManager = AdManager(this)
        sessionStartTime = SystemClock.uptimeMillis()

        // Update session time every second
        GlobalScope.launch {
            while (isAutoPlayEnabled) {
                delay(1000)
                updateSessionTime()
            }
        }

        // Set button listeners
        playAdButton.setOnClickListener {
            Log.d(TAG, "▶ Play Ad button clicked")
            showAd()
        }

        stopButton.setOnClickListener {
            Log.d(TAG, "■ Stop Auto-Play button clicked")
            toggleAutoPlay()
        }

        // Start auto-play
        showAd()
    }

    private fun showAd() {
        if (!isAutoPlayEnabled) {
            Log.d(TAG, "⚠️ Auto-play is disabled")
            return
        }

        Log.d(TAG, "📺 Showing ad...")
        statusText.text = "Ad Playing..."
        descriptionText.text = "Please watch the advertisement"
        playAdButton.isEnabled = false

        adManager.showRewardedAd(this)
    }

    override fun onAdStarted() {
        Log.d(TAG, "🎬 Ad Started")
        statusText.text = "Ad Playing..."
        descriptionText.text = "Please watch the full advertisement"
    }

    override fun onAdCompleted() {
        Log.d(TAG, "✅ Ad Completed")
        adsCount++
        adsCountText.text = adsCount.toString()
        
        statusText.text = "Ad Completed!"
        descriptionText.text = "Next ad in 2 seconds..."

        // Auto-play next ad after 2 seconds
        GlobalScope.launch {
            delay(2000)
            if (isAutoPlayEnabled) {
                runOnUiThread {
                    showAd()
                }
            }
        }
    }

    override fun onAdClicked() {
        Log.d(TAG, "👆 Ad Clicked")
        statusText.text = "Ad Clicked"
    }

    override fun onAdFailed(error: String) {
        Log.e(TAG, "❌ Ad Failed: $error")
        statusText.text = "Ad Error"
        descriptionText.text = "Retrying in 3 seconds..."
        playAdButton.isEnabled = true

        // Retry after 3 seconds
        GlobalScope.launch {
            delay(3000)
            if (isAutoPlayEnabled) {
                runOnUiThread {
                    showAd()
                }
            }
        }
    }

    private fun toggleAutoPlay() {
        isAutoPlayEnabled = !isAutoPlayEnabled
        
        if (isAutoPlayEnabled) {
            Log.d(TAG, "✅ Auto-Play Enabled")
            statusText.text = "Auto-Play Enabled"
            descriptionText.text = "Ads will play automatically"
            stopButton.text = "■ Stop Auto-Play"
            showAd()
        } else {
            Log.d(TAG, "⛔ Auto-Play Disabled")
            statusText.text = "Auto-Play Disabled"
            descriptionText.text = "Tap 'Play Ad Now' to play ads manually"
            stopButton.text = "▶ Start Auto-Play"
            playAdButton.isEnabled = true
        }
    }

    private fun updateSessionTime() {
        val elapsedTime = (SystemClock.uptimeMillis() - sessionStartTime) / 1000
        runOnUiThread {
            sessionTimeText.text = "${elapsedTime}s"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "🛑 MainActivity Destroyed")
        adManager.destroy()
    }
}
