package by.darya.zdzitavetskaya.notice.presentation.tabPresentation.view;

import android.support.design.widget.TabLayout;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface TabView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void onTabChanged(TabLayout.Tab tab, int color, float size);
}
