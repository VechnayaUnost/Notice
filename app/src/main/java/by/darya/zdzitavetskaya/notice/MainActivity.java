package by.darya.zdzitavetskaya.notice;

import android.support.design.bottomappbar.BottomAppBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

import com.crashlytics.android.Crashlytics;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.darya.zdzitavetskaya.notice.ui.fragment.NoticeFragment;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_app_bar)
    BottomAppBar bottomAppBar;

    @OnClick(R.id.fab)
    public void fabClick() {
        setFabAlignmentMode();
        bottomAppBar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(bottomAppBar);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_container, new NoticeFragment())
                .commit();
    }

    public void setFabAlignmentMode() {
        if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        } else {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        }
    }
}
