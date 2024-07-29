package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


//- Viajantes próximo (basica)
//- Promoções perto (expandida)
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

        var button1 = findViewById<Button>(R.id.basic_notification) as Button
        var button2 = findViewById<Button>(R.id.extend_notification) as Button
        var button3 = findViewById<Button>(R.id.action_notification) as Button
        var button4 = findViewById<Button>(R.id.reply_notification) as Button


        button2.setOnClickListener {
            createNotificationChannel(CHANNEL_ID2,"Promoções perto","gfhjakslfdhjskal")
            expandedNotification(CHANNEL_ID2,"teste2","ghflsdkhgjfklsd","TESTETESJKLTSJKTLÇSJKTLSÇJKTLSÇJKSLÇJKLÇSJKELT")
        }

        button1.setOnClickListener {
            createNotificationChannel(CHANNEL_ID1,"mensagem","balkadfhjkalsdfjh")
            basicNotification(CHANNEL_ID1,"teste1","ghflsdkhgjfklsd")
        }

        button3.setOnClickListener {
            createNotificationChannel(CHANNEL_ID3,"action","balkadfhjkalsdfjh")
            actionNotification(CHANNEL_ID3,"teste3","gfjkdlsaçgjkflsçjgkflç")
        }


    }

    private fun replyNotification(channel: String,title:String,text: String, messager: String){
        val KEY_TEXT_REPLY = "data"

        val snoozeIntent = Intent(this, CustomBroadcastReceiver2::class.java).apply {
            action = "com.aimiris.demos.blah2"
        }

        var replyLabel: String = messager
        var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run{
            setLabel(replyLabel)
            build()
        }
        //PendingIntent
        var replyPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(applicationContext,
                111,
                snoozeIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        var action: NotificationCompat.Action =
            NotificationCompat.Action.Builder(
                androidx.core.R.drawable.notification_icon_background,"Reply",replyPendingIntent)
                .addRemoteInput(remoteInput)
                .build()
        val builder = NotificationCompat.Builder(this,channel)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(text)
            .addAction(action)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(1, builder.build())
        }


    }

    private fun actionNotification(channel: String,title: String,text:String) {
        val snoozeIntent = Intent(this, CustomBroadcastReceiver::class.java).apply {
            action = "com.aimiris.demos.blah"
            putExtra("data", "666")
        }
        val snoozePedingIntent: PendingIntent =
            PendingIntent.getBroadcast(this, 0, snoozeIntent, PendingIntent.FLAG_IMMUTABLE)
        val builder = NotificationCompat.Builder(this, channel)
            .setSmallIcon(androidx.core.R.drawable.notification_icon_background)
            .setContentTitle(title)
            .setContentText(text)
            .setContentIntent(snoozePedingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.ic_launcher_foreground, "text", snoozePedingIntent)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(1, builder.build())
        }
    }


    private fun expandedNotification(channel: String,title: String,text: String,bigText: String){
        val builder = NotificationCompat.Builder(this,channel)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(text)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(bigText))
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

    private fun createNotificationChannel(chanelid: String, name: String,description: String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                chanelid,
                name, NotificationManager.IMPORTANCE_DEFAULT)
                    channel.description = description


            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}