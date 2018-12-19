package by.darya.zdzitavetskaya.notice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;

import by.darya.zdzitavetskaya.notice.ui.fragment.NoticeFragment;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container_frame_layout, new NoticeFragment())
                .commit();
    }
}
