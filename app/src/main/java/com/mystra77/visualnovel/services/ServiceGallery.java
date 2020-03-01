package com.mystra77.visualnovel.services;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.mystra77.visualnovel.R;

public class ServiceGallery extends Service {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        //ARREGLAR
        NotificationManager nmanager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("principal", "principal", NotificationManager.IMPORTANCE_DEFAULT);
        nmanager.createNotificationChannel(channel);
        channel.setLightColor(Color.BLUE);
        AudioAttributes audioAttr = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build();
        channel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM), audioAttr);
        Notification.Builder builder = new Notification.Builder(this, "principal");
        builder.setContentTitle("hola");
        builder.setContentText("gracias");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLights(Color.BLUE, 1, 0);
        builder.setColor(Color.BLUE);
        builder.setOngoing(false);
        builder.setColorized(true);
        nmanager.notify(1, builder.build());
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return onBind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        this.stopSelf();
        super.onDestroy();
    }

}
