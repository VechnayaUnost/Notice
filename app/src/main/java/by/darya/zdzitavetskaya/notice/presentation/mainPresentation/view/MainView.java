package by.darya.zdzitavetskaya.notice.presentation.mainPresentation.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import by.darya.zdzitavetskaya.notice.model.view.BaseViewModel;

public interface MainView extends MvpView {

    void onDeletedAllSolved();

    void search(final List<BaseViewModel> notices);
}
