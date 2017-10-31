package com.example.kaname.samplenotificationlistenerservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private static int NOTIFICATION_MINIMUM_ID = 10;
    private static int NOTIFICATION_CUSTOMLAYOUT_ID  = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });
    }

    private void showNotification() {
        //TODO クリックされたIntentに飛ばす用
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);


        //TODO 簡単なやりかた
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Title");
        builder.setContentText("text");
        builder.setSubText("Sub text");
        builder.setContentInfo("Info");
        builder.setWhen(System.currentTimeMillis());

        //クリックされたらIntentへ飛ばす
        builder.setContentIntent(pi);

        //バイブレーションがなる
        //long[] vibrate = {0, 100, 300, 1000};
        //builder.setVibrate(vibrate);

        //LEDライト LEDは画面がOFFの時に出力してる
        //builder.setDefaults(Notification.DEFAULT_LIGHTS); //この行を削除すると点滅しつづける。
        //builder.setLights(Color.BLUE, 5000, 10); //発光色,発光時間,消灯時間

        //通知の音とバイブ
        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

        builder.setAutoCancel(true);//クリックで通知バーから削除

        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        manager.notify(NOTIFICATION_MINIMUM_ID, builder.build());


        //TODO カスタムレイアウト
        /*
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);

        RemoteViews customView = new RemoteViews(getPackageName(), R.layout.custom_notificationlistener);
        customView.setTextViewText(R.id.textview_text, "TEST");
        builder.setContent(customView);

        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        manager.notify(NOTIFICATION_CUSTOMLAYOUT_ID, builder.build());
        */
    }
}
