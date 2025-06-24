package com.reactnativehyperswitchscancard

import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class HyperswitchScancardModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return "HyperswitchScancard"
  }

  @ReactMethod
  fun launchScanCard(scanCardRequest: String, callBack: Callback) {
    ScanCardLauncher.setScanCardCallback(callBack)
    currentActivity?.let { ScanCardLauncher.startHSScanCardActivity(it) }
  }
}
