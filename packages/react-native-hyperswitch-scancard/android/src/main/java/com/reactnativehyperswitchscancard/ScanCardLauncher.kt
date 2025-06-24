package com.reactnativehyperswitchscancard

import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.Callback

class ScanCardLauncher {

  companion object {
    lateinit var callback: Callback

    fun setScanCardCallback(callback: Callback) {
      this.callback = callback
    }

    fun startHSScanCardActivity(currentActivity: Activity) {
      val scanCardIntent = Intent(currentActivity, HSScanCardActivity::class.java)
      currentActivity.startActivity(scanCardIntent)
    }
  }

}
