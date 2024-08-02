package com.example.teste2.notification_feature

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"solicitação de amizade aceita", Toast.LENGTH_SHORT).show()
    }
}