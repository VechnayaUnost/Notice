package by.darya.zdzitavetskaya.notice.presentation.tabPresentation.view;

import android.support.design.widget.TabLayout;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface TabView extends MvpView {

    void onTabChanged(TabLayout.Tab tab, int color, float size);
}
