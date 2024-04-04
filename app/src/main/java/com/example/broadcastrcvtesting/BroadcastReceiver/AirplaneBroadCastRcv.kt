package com.example.broadcastrcvtesting.BroadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneBroadCastRcv: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p0 != null) {
            Toast.makeText(p0.applicationContext, "Airplane Mode", Toast.LENGTH_SHORT).show()
        }
    }
}