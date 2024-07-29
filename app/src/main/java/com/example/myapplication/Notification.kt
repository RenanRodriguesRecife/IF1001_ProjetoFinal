package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


//- Viajantes próximo (basica)
//- Promoções perto (extendida)
//- Pedido de solicitação de amizade (ação)
//- Mensagens (notificação Reply)

const val CHANNEL_ID1 = "channelId1" //Viajante próximo
const val CHANNEL_ID2 = "channelId2" //mensagens
const val CHANNEL_ID3 = "channelId3" //promoção perto
const val CHANNEL_ID4 = "channelId4" //solicitação de amizate

class Notification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notification)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        createNotificationChannel(CHANNEL_ID1,"TEste")
        basicNotification(CHANNEL_ID1,"teste","ghflsdkhgjfklsd")

    }




    private fun basicNotification(channel: String,title: String, text: String){
        var builder = NotificationCompat.Builder(this, channel)
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            if(ActivityCompat.checkSelfPermission(
                    applicationContext,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ){
                return
            }
            notify(1,builder.build())
        }
    }

    private fun createNotificationChannel(chanelid: String, description: String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                chanelid,
                "First channel", NotificationManager.IMPORTANCE_DEFAULT)
                    channel.description = description


            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}