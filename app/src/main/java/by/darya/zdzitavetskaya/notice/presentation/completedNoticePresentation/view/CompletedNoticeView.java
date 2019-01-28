package by.darya.zdzitavetskaya.notice.presentation.completedNoticePresentation.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import by.darya.zdzitavetskaya.notice.model.view.BaseViewModel;

public interface CompletedNoticeView extends MvpView {

    void onNoticesSuccess(List<BaseViewModel> notices);
}
