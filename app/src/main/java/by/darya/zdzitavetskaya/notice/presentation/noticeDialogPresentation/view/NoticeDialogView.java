package by.darya.zdzitavetskaya.notice.presentation.noticeDialogPresentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import by.darya.zdzitavetskaya.notice.model.NoteModel;

public interface NoticeDialogView extends MvpView {

    void onNoticeSuccess(NoteModel notice);

    @StateStrategyType(SkipStrategy.class)
    void buttonClickable(boolean isClickable);
}
