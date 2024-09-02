package com.example.batteryalarm

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.Tag
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.batteryalarm.ui.theme.BatteryAlarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BatteryAlarmTheme {

                    BatteryScreen()

            }
        }
    }
}


@Composable
fun BatteryScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    var batteryIcon by remember { mutableIntStateOf(R.drawable.battery_full) }



    batteryLevelObserver(context,
        onBatteryLow = {
            if(batteryIcon != R.drawable.battery_low)batteryIcon = R.drawable.battery_low },
        onBatteryOK =  {
            if(batteryIcon != R.drawable.battery_full)batteryIcon = R.drawable.battery_full })

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = batteryIcon),
            contentDescription = "Battery Icon", modifier.fillMaxSize()

        )

    }




}




fun batteryLevelObserver(context: Context, onBatteryLow: () -> Unit, onBatteryOK: () -> Unit) {
    val batteryLevelReceiver = BatteryLevelReceiver(onBatteryLow,onBatteryOK)
    val intentFilterLow = IntentFilter(Intent.ACTION_BATTERY_LOW)
    Log.d("Battery", "batteryLevelObserver")
    context.registerReceiver(batteryLevelReceiver, intentFilterLow)
    val intentFilterOk = IntentFilter(Intent.ACTION_BATTERY_OKAY)
    context.registerReceiver(batteryLevelReceiver, intentFilterOk)
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    BatteryAlarmTheme {
        BatteryScreen()

    }
}