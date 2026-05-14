package com.shadowads

interface AdListener {
    fun onAdStarted()
    fun onAdCompleted()
    fun onAdClicked()
    fun onAdFailed(error: String)
}
