package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat

class CustomBroadcastReceiver2 : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val msg = getMessageText(intent!!)?:""

        val repliedNotification = NotificationCompat.Builder(context!!,"YOUR_CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentText(msg)
            .setAutoCancel(true)

        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "YOUR_CHANEL_ID", "YOUR_CHANNEL_NAME",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.enableLights(true)
            channel.lightColor = Color.BLUE
            channel.description = "YOUR_NOTIFICATION_CHANLL_DESCRIPTIO"
            mNotificationManager.createNotificationChannel(channel)
        }
        mNotificationManager.notify(1, repliedNotification.build())
        Toast.makeText(context,"custom broadcast2: " + msg, Toast.LENGTH_SHORT).show()
    }



    private fun getMessageText(intent: Intent):CharSequence?{
        return RemoteInput.getResultsFromIntent(intent)?.getCharSequence("data")
    }

}