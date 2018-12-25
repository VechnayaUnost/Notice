package by.darya.zdzitavetskaya.notice.presentation.tabPresentation.presenter;

import android.support.design.widget.TabLayout;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import by.darya.zdzitavetskaya.notice.presentation.tabPresentation.view.TabView;

@InjectViewState
public class TabPresenter extends MvpPresenter<TabView> {

    public void onTabChanged(TabLayout.Tab tab, int color, float size) {
        getViewState().onTabChanged(tab, color, size);
    }
}
