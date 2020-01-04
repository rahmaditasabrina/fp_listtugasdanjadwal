package com.amikom.two;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppReceiver extends BroadcastReceiver {

    private static final int ALARM_REQUEST_CODE = 134;
    //set interval notifikasi 10 detik

    String NOTIFICATION_CHANNEL_ID = "rasupe_channel_id";
    String NOTIFICATION_CHANNEL_NAME = "rasupe channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent alarmIntent = new Intent(context, com.amikom.two.AppReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, alarmIntent, 0);
        //set waktu sekarang berdasarkan interval
        Calendar cal = Calendar.getInstance();
        int interval_seconds = 10;
        cal.add(Calendar.SECOND, interval_seconds);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //set alarm manager dengan memasukkan waktu yang telah dikonversi menjadi milliseconds
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        } else if (android.os.Build.VERSION.SDK_INT >= 19) {
            manager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        } else {
            manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        }
        //kirim notifikasi
        sendNotification(context);
    }
    //handle notification
    private void sendNotification(Context context) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
        String datetimex = sdf.format(new Date());
        String notif_title = "Coba AlarmManager Notif";
        String notif_content = "Notif time "+datetimex;
        NotificationManager alarmNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent newIntent = new Intent(context, MainActivity.class);
        newIntent.putExtra("notifkey", "notifvalue");
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                newIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //cek jika OS android Oreo atau lebih baru
        //kalau tidak di set maka notifikasi tidak akan muncul di OS tersebut
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance);
            alarmNotificationManager.createNotificationChannel(mChannel);
        }

        //Buat notification
        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        alamNotificationBuilder.setContentTitle(notif_title);
        alamNotificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        alamNotificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        alamNotificationBuilder.setContentText(notif_content);
        alamNotificationBuilder.setAutoCancel(true);
        alamNotificationBuilder.setContentIntent(contentIntent);

        //Tampilkan notifikasi
        int NOTIFICATION_ID = 1;
        alarmNotificationManager.notify(NOTIFICATION_ID, alamNotificationBuilder.build());
    }
}
