package com.example.kotlinpushnotificationexample.utils

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.kotlinpushnotificationexample.R

private val NOTIFICATION_ID = 0

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.general_notification)
    )
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(applicationContext.getString(R.string.app_name))
        .setContentText(messageBody)
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    notify(NOTIFICATION_ID, builder.build())
}