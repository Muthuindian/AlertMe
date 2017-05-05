package com.tech42labs.alertme.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tech42labs.alertme.MainActivity;
import com.tech42labs.alertme.R;

/**
 * Created by mari on 3/28/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.e("From: "  , remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.e("Message data payload: " , String.valueOf(remoteMessage.getData()));
        }
        if (remoteMessage.getNotification() != null) {
            Log.e("Notification Body: " , remoteMessage.getNotification().getBody());
        }

        createNotification(remoteMessage.getNotification().getBody());
        super.onMessageReceived(remoteMessage);
    }


    private void createNotification(String body) {

        Intent intent = new Intent( this , MainActivity. class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultIntent = PendingIntent.getActivity( this , 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        android.support.v4.app.NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder( this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("FCM Test Notification")
                .setContentText(body)
                .setAutoCancel( true )
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mNotificationBuilder.build());
    }
}
