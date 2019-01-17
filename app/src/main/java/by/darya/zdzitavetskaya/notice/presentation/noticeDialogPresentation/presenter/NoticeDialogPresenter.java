package by.darya.zdzitavetskaya.notice.presentation.noticeDialogPresentation.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import javax.inject.Inject;

import by.darya.zdzitavetskaya.notice.App;
import by.darya.zdzitavetskaya.notice.model.NoteModel;
import io.realm.Realm;

public class NoticeDialogPresenter extends MvpPresenter<MvpView> {

    @Inject
    Realm realm;

    public NoticeDialogPresenter() {
        App.getAppComponent().inject(this);
    }

    public void addNoteInDatabase(final NoteModel notice) {
        realm.executeTransaction(realm -> realm.insertOrUpdate(notice));    //don't use viewState
    }
}
