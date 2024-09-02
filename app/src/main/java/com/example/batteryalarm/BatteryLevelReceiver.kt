package com.example.batteryalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BatteryLevelReceiver(private val onBatteryLow: () -> Unit ,private val onBatteryOK: () -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BATTERY_LOW) {
            Log.d("Battery", "batteryLevelObserver Inside if")
            onBatteryLow()
        }else if (intent?.action == Intent.ACTION_BATTERY_OKAY){
            Log.d("Battery", "batteryLevelObserver Inside else")
            onBatteryOK()
        }
    }
}