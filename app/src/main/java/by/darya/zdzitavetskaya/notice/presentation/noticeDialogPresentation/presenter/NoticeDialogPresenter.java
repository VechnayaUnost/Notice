package by.darya.zdzitavetskaya.notice.presentation.noticeDialogPresentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import by.darya.zdzitavetskaya.notice.App;
import by.darya.zdzitavetskaya.notice.model.NoteModel;
import by.darya.zdzitavetskaya.notice.presentation.noticeDialogPresentation.view.NoticeDialogView;
import io.realm.Realm;

@InjectViewState
public class NoticeDialogPresenter extends MvpPresenter<NoticeDialogView> {

    @Inject
    Realm realm;

    public NoticeDialogPresenter() {
        App.getAppComponent().inject(this);
    }

    public void addNoteInDatabase(final NoteModel notice) {
        realm.executeTransaction(realm -> realm.insertOrUpdate(notice));    //don't use viewState
    }

    public void getNoticeFromDatabase(final String id) {
        final NoteModel notice = realm.where(NoteModel.class).equalTo("id", id).findFirst();
        getViewState().onNoticeSuccess(notice);
    }
}
