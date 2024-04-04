package com.example.broadcastrcvtesting.BroadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ChargingBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("BroadcastRcvTesting", "onReceive: ")
        if (p0 != null) {
            Toast.makeText(p0.applicationContext, "Charging", Toast.LENGTH_SHORT).show()
        }
    }
}