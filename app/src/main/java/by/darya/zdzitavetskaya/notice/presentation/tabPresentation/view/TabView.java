package by.darya.zdzitavetskaya.notice.presentation.tabPresentation.view;

import android.support.design.widget.TabLayout;

import com.arellomobile.mvp.MvpView;

public interface TabView extends MvpView {

    void onTabChanged(TabLayout.Tab tab, int color, float size);
}
