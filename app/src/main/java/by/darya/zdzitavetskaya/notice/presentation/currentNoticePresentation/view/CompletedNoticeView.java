package by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import by.darya.zdzitavetskaya.notice.model.NoticeModel;

public interface CompletedNoticeView extends MvpView {

    void onNoticesSuccess(List<NoticeModel> notices);

    void onNoticeSuccess(NoticeModel notice);

}
