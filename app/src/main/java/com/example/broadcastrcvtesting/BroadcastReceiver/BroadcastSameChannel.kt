package com.example.broadcastrcvtesting.BroadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BroadcastSameChannel: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            Toast.makeText(context,"Received at ${intent.getStringExtra("key")}",Toast.LENGTH_SHORT).show()
        }

    }
}