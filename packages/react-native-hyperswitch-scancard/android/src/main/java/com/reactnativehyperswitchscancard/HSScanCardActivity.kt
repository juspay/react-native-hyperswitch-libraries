package com.reactnativehyperswitchscancard
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.bridge.Arguments
import io.hyperswitch.android.hscardscan.cardscan.CardScanSheet
import io.hyperswitch.android.hscardscan.cardscan.CardScanSheetResult

class HSScanCardActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val cardScanSheet = CardScanSheet.create(this, ::onScanFinished)
    cardScanSheet.present()
  }

  override fun onBackPressed() {
    super.onBackPressed()
    finish()
  }

  private fun onScanFinished(result: CardScanSheetResult) {
    val map = Arguments.createMap()
    when (result) {
      is CardScanSheetResult.Completed -> {
        map.putString("status", "Succeeded")
        val dataMap = Arguments.createMap()
        dataMap.putString("pan", result.scannedCard.pan)
        map.putMap("data", dataMap)
        ScanCardLauncher.callback.invoke(map)
        finish()
      }

      is CardScanSheetResult.Canceled -> {
        map.putString("status", "Cancelled")
        finish()
      }

      is CardScanSheetResult.Failed -> {
        map.putString("status", "Failed")
        finish()
      }
    }
  }
}
