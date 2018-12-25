package by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import by.darya.zdzitavetskaya.notice.model.NoticeModel;
import by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.view.CompletedNoticeView;
import io.realm.Realm;
import io.realm.RealmResults;

@InjectViewState
public class CompletedNoticePresenter extends MvpPresenter<CompletedNoticeView>{

    private final Realm realm;

    public CompletedNoticePresenter() {
        realm = Realm.getDefaultInstance();
    }

    public void addNoteInDatabase(final NoticeModel notice) {
        realm.executeTransaction(realm -> realm.insertOrUpdate(notice));
    }

    public void getNoticesFromDatabase() {
        final RealmResults<NoticeModel> notices = realm.where(NoticeModel.class).findAll();
        getViewState().onNoticesSuccess(notices);
    }

    public void getNoticeFromDatabase(final String id) {
        final NoticeModel notice = realm.where(NoticeModel.class).equalTo("id", id).findFirst();
        getViewState().onNoticeSuccess(notice);
    }
}
