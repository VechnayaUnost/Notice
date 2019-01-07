package by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import by.darya.zdzitavetskaya.notice.model.NoteModel;

public interface CurrentNoticeView extends MvpView {

    void onNoticesSuccess(List<NoteModel> notices);

    void onNoticeSuccess(NoteModel notice);

}
