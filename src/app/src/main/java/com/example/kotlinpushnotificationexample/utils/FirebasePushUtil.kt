package com.example.kotlinpushnotificationexample.utils

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebasePushUtil : FirebaseMessagingService() {

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    override fun onMessageReceived(p0: RemoteMessage) {
        if(p0 != null) {
            // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
            Log.d(TAG, "From: ${p0?.from}")

            // Check if message contains a data payload.
            p0?.data?.let {
                Log.d(TAG, "Message data payload: " + p0.data)
            }

            // Check if message contains a notification payload.
            p0?.notification?.let {
                Log.d(TAG, "Message Notification Body: ${it.body}")
                sendNotification(it.body!!)
            }
        }
    }
    // [END receive_message]

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(p0: String) {
        Log.d(TAG, "Refreshed token: $p0")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(p0)
    }
    // [END on_new_token]


    /**
     * Persist token to third-party servers.
     *
     * @param token The new token.
     */
    private fun sendRegistrationToServer(token: String?) {
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private fun sendNotification(messageBody: String) {
        val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager
        notificationManager.sendNotification(messageBody, applicationContext)
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}