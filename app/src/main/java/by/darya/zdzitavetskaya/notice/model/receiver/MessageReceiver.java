package by.darya.zdzitavetskaya.notice.model.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String str = intent.getStringExtra("task");

            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }
}
