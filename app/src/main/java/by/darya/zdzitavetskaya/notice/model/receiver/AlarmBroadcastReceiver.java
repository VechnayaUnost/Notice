package by.darya.zdzitavetskaya.notice.model.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import by.darya.zdzitavetskaya.notice.MainActivity;
import by.darya.zdzitavetskaya.notice.R;
import by.darya.zdzitavetskaya.notice.model.NoteModel;
import io.realm.Realm;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String id = intent.getStringExtra("task");
            showNotification(context, id);
        }
    }

    private void showNotification(Context context, String id) {
        int notifyID = 1;

        //TODO: "to remake this"
        final Realm realm = Realm.getDefaultInstance();
        final NoteModel notice = realm.where(NoteModel.class).equalTo("id", id).findFirst();

        NotificationChannel channel = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("NOTICE", "NOTIFICATION_NAME", NotificationManager.IMPORTANCE_HIGH);
        }

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        Bitmap largeBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round);

        Notification notification;

        String contentText = null;
        String contentTitle = null;

        if (notice != null) {
            contentText = notice.getDescription();
            contentTitle = notice.getTitle();
        }

        if (largeBitmap != null) {
            notification = new NotificationCompat.Builder(context, "NOTICE")
                            .setContentTitle(contentTitle)
                            .setContentText(contentText)
                            .setSmallIcon(R.drawable.ic_av_timer)
                            .setContentIntent(contentIntent)
                            .setAutoCancel(true)
                            .setLargeIcon(largeBitmap)
                            .build();
        } else {
            notification = new NotificationCompat.Builder(context, "NOTICE")
                            .setContentTitle(contentTitle)
                            .setContentText(contentText)
                            .setSmallIcon(R.drawable.ic_av_timer)
                            .setContentIntent(contentIntent)
                            .setAutoCancel(true)
                            .build();
        }

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        notification.flags |= android.app.Notification.FLAG_AUTO_CANCEL;
        if (notificationManager != null) {
            notificationManager.notify(notifyID, notification);
        }
    }
}