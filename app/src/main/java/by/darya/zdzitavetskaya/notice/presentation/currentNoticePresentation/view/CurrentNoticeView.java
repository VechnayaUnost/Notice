package by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import by.darya.zdzitavetskaya.notice.model.NoteModel;
import by.darya.zdzitavetskaya.notice.model.view.BaseViewModel;

public interface CurrentNoticeView extends MvpView {

    void onNoticesSuccess(List<BaseViewModel> notices);

    void onNoticeSuccess(NoteModel notice);

}
