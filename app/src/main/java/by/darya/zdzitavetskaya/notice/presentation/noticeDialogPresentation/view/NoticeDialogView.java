package by.darya.zdzitavetskaya.notice.presentation.noticeDialogPresentation.view;

import com.arellomobile.mvp.MvpView;

import by.darya.zdzitavetskaya.notice.model.NoteModel;

public interface NoticeDialogView extends MvpView {

    void onNoticeSuccess(NoteModel notice);
}
