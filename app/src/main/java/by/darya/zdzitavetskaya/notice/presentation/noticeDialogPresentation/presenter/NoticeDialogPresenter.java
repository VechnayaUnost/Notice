package by.darya.zdzitavetskaya.notice.presentation.noticeDialogPresentation.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import by.darya.zdzitavetskaya.notice.model.NoteModel;
import io.realm.Realm;

public class NoticeDialogPresenter extends MvpPresenter<MvpView> {

    private final Realm realm;

    public NoticeDialogPresenter() {
        realm = Realm.getDefaultInstance();
    }

    public void addNoteInDatabase(final NoteModel notice) {
        realm.executeTransaction(realm -> realm.insertOrUpdate(notice));    //don't use viewState
    }
}
