package by.darya.zdzitavetskaya.notice;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.crashlytics.android.Crashlytics;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import by.darya.zdzitavetskaya.notice.common.constants.Constants;
import by.darya.zdzitavetskaya.notice.common.interfaces.UpdateListener;
import by.darya.zdzitavetskaya.notice.common.utility.Preference;
import by.darya.zdzitavetskaya.notice.ui.adapter.ViewPagerAdapter;
import by.darya.zdzitavetskaya.notice.ui.dialog.NoticeDialog;
import by.darya.zdzitavetskaya.notice.ui.fragment.CompletedNoticeFragment;
import by.darya.zdzitavetskaya.notice.ui.fragment.CurrentNoticeFragment;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends MvpAppCompatActivity implements UpdateListener, SensorEventListener {

    @BindView(R.id.bottom_app_bar)
    BottomAppBar bottomAppBar;

    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    Unbinder unbinder;
    ViewPagerAdapter adapter;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 5000;
    private static final int SHAKE_TIMES = 2;
    private int shakeTimes = 0;

    @OnClick(R.id.fab)
    public void fabClick() {
        if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
            createNewNote();
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = Objects.requireNonNull(sensorManager).getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        setSupportActionBar(bottomAppBar);
        setFabColor();
        setupViewPager(viewPager);
        initTabBarLayout(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CurrentNoticeFragment(), getString(R.string.current));
        adapter.addFragment(new CompletedNoticeFragment(), getString(R.string.completed));
        viewPager.setAdapter(adapter);
    }

    private void initTabBarLayout(ViewPager viewPager) {
        tabs.setupWithViewPager(viewPager);

        for (int i = 0; i < tabs.getTabCount(); i++) {
            TabLayout.Tab currentTab = tabs.getTabAt(i);
            if (currentTab != null) {
                currentTab.setCustomView(R.layout.custom_view_tab);
                View customView = currentTab.getCustomView();
                if (customView != null) {
                    TextView tvTabName = customView.findViewById(R.id.tv_tab_name);
                    ImageView ivTabIcon = customView.findViewById(R.id.iv_tab_icon);
                    switch (i) {
                        case Constants.CURRENT_NOTICE_TAB:
                            tvTabName.setText(getString(R.string.current_fragment));
                            ivTabIcon.setImageDrawable(ContextCompat.getDrawable(this,
                                    R.drawable.ic_current_white));
                            break;
                        case Constants.COMPLETED_NOTICE_TAB:
                            tvTabName.setText(getString(R.string.completed_fragment));
                            ivTabIcon.setImageDrawable(ContextCompat.getDrawable(this,
                                    R.drawable.ic_done_white));
                            ivTabIcon.setColorFilter(getResources().getColor(android.R.color.black));
                            break;
                    }
                }
            }
        }

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabChanged(tab, android.R.color.white, 14);
                setFab();
                bottomAppBar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                onTabChanged(tab, android.R.color.black, 12);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        TabLayout.Tab currentTab = tabs.getTabAt(Constants.CURRENT_NOTICE_TAB);
        if (currentTab != null) {
            onTabChanged(currentTab, android.R.color.white, 14);
        }
    }

    private void setFab() {
        if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
            setFabIcon(R.drawable.ic_delete_white);
        } else {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            setFabIcon(R.drawable.ic_add_white);
        }
    }

    private void setFabIcon(int id) {
        fab.setImageDrawable(ContextCompat.getDrawable(this, id));
    }

    private void setFabColor() {
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Preference.getColor(this))));
    }

    private void onTabChanged(TabLayout.Tab tab, int color, float size) {
        View customView = tab.getCustomView();
        if (customView != null) {
            TextView tvTabName = customView.findViewById(R.id.tv_tab_name);
            if (tvTabName != null) {
                tvTabName.setTextColor(ContextCompat.getColor(MainActivity.this, color));
                tvTabName.setTextSize(size);
            }

            ImageView ivTabIcon = customView.findViewById(R.id.iv_tab_icon);
            ivTabIcon.setColorFilter(getResources().getColor(color));
        }
    }

    private void createNewNote() {
        NoticeDialog noticeDialog = NoticeDialog.newInstance(null);
        noticeDialog.setCancelable(false);
        noticeDialog.show(getSupportFragmentManager(), getString(R.string.dialog));
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.bottomappbar_menu, menu);
        return true;
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.color_pink:
                fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                Preference.setColor(this, getResources().getString(R.color.colorAccent));
                break;
            case R.id.color_orange:
                fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorOrange)));
                Preference.setColor(this, getResources().getString(R.color.colorOrange));
                break;
            case R.id.color_blue:
                fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlue)));
                Preference.setColor(this, getResources().getString(R.color.colorBlue));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNoticesUpdate() {
        CurrentNoticeFragment currentNoticeFragment = (CurrentNoticeFragment) adapter.getItem(0);
        currentNoticeFragment.updateList();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    shakeTimes++;
                    if (shakeTimes == SHAKE_TIMES) {
                        createNewNote();
                        shakeTimes = 0;
                    }
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }
}
