package com.example.prahathessrengasamy.roomie;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){

        String message = intent.getStringExtra ("message");
        String title = intent.getStringExtra ("title");

        Intent notIntent = new Intent (context,CreateTask.class);
        PendingIntent contentIntent = PendingIntent.getActivity (context, 0, notIntent,PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);

        NotificationCompat style = new NotificationCompat();



        //Generate a notification with just short text and small icon
        NotificationCompat.Builder builder = new NotificationCompat.Builder (context)
        .setContentIntent (contentIntent)
        .setSmallIcon (R.drawable.fun)
        .setContentTitle(title)
        .setContentText(message)
        .setWhen(System.currentTimeMillis());


        Notification notification = builder.build();
        manager.notify(0, notification);
        }


}