package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat


class notification : AppCompatActivity() {

    val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notification)

        createNotificationChannel()

        var button_basic_notification = findViewById<Button>(R.id.basic_notification) as Button
        button_basic_notification.setOnClickListener{
            val mBuild = NotificationCompat.Builder(applicationContext,"YOUR_CHANNEL_ID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Programação 3")
                .setContentText("jfdkalsçfjdklçasj")
                .setAutoCancel(true)
            mNotificationManager.notify(0,mBuild.build())
        }


    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("YOUR_CHANNEL_ID","YOUR_CHANNEL_NAME",
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.enableLights(true)
            channel.lightColor = Color.BLUE
            channel.description = "YOUR_NOTIFICATION_CHANNEL_DESCRIPTION"


            mNotificationManager.createNotificationChannel(channel)
        }
    }
}

/*
notificações:

- Viajantes próximo (basica)
- Promoções perto (extendida)
- Pedido de solicitação de amizade (ação)
- Mensagens (notificação Reply)

 */