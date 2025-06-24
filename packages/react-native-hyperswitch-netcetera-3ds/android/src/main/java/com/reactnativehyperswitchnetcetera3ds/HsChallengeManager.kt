package com.reactnativehyperswitchnetcetera3ds


import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Callback
import com.netcetera.threeds.sdk.api.transaction.challenge.ChallengeStatusReceiver
import com.netcetera.threeds.sdk.api.transaction.challenge.events.CompletionEvent
import com.netcetera.threeds.sdk.api.transaction.challenge.events.ProtocolErrorEvent
import com.netcetera.threeds.sdk.api.transaction.challenge.events.RuntimeErrorEvent

class HsChallengeManager : ChallengeStatusReceiver {

  lateinit var postChallengeCallback: Callback;

  val map = Arguments.createMap()

  fun setPostHsChallengeCallback(callback: Callback) {
    this.postChallengeCallback = callback
  }

  override fun completed(completionEvent: CompletionEvent) {
    // Handle successful or unsuccessful completion of challenge flow
    map.putString("status", "success")
    map.putString("message", "challenge completed successfully")
    postChallengeCallback.invoke(map)
  }

  override fun cancelled() {
    // Handle challenge canceled by the user
    map.putString("status", "error")
    map.putString("message", "challenge cancelled by user")
    postChallengeCallback.invoke(map)
  }

  override fun timedout() {
    // Handle challenge timeout
    map.putString("status", "error")
    map.putString("message", "challenge timeout")
    postChallengeCallback.invoke(map)
  }

  override fun protocolError(protocolErrorEvent: ProtocolErrorEvent) {
    // Handle protocol error that has been send by the ACS
//    Log.i("Challenge Protocol", protocolErrorEvent.errorMessage.toString())
    map.putString("status", "error")
    map.putString("message", protocolErrorEvent.errorMessage.toString())
    postChallengeCallback.invoke(map)
  }

  override fun runtimeError(runtimeErrorEvent: RuntimeErrorEvent) {
    // Handle error that has occurred in the SDK at runtime
//    Log.i("Challenge Runtime", runtimeErrorEvent.errorMessage.toString())
    map.putString("status", "error")
    map.putString("message", runtimeErrorEvent.errorMessage.toString())
    postChallengeCallback.invoke(map)
  }
}
