package com.jojo.diary.memo;

import android.app.Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.jojo.diary.R;

public class AlarmReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_FLAG = 1;

    private String memoInfo = "test";

    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "闹钟响了", Toast.LENGTH_SHORT).show();
        Log.i("AlarmReceiver","闹钟响了");
        Bundle bundle=intent.getExtras();
        Log.i("AlarmReceiver","接收传值"+bundle.getString("name"));


       PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
               new Intent(context,MemoFragment.class),PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel("chanel_id","channel_name",NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableVibration(true);
        channel.enableLights(true);
        manager.createNotificationChannel(channel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_mood_happy)
                .setContentTitle("备忘录:")
                .setContentText(memoInfo)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setChannelId("chanel_id");

       Notification notify = new Notification.Builder(context)
               .setSmallIcon(R.drawable.ic_mood_happy)
               .setContentTitle("备忘录:")
               .setContentText(memoInfo)
               .setChannelId("chanel_id")
               .setContentIntent(pendingIntent).setNumber(1).getNotification();
       notify.flags |= Notification.FLAG_AUTO_CANCEL; // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。
       // 在Android进行通知处理，首先需要重系统哪里获得通知管理器NotificationManager，它是一个系统Service。


       manager.createNotificationChannel(channel);
       manager.notify(NOTIFICATION_FLAG, builder.build());
       // 步骤4：通过通知管理器来发起通知。如果id不同，则每click，在status哪里增加一个提示
        //notify

    }
}
