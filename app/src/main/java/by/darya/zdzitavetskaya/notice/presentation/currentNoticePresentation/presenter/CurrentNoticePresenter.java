package by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import by.darya.zdzitavetskaya.notice.model.NoticeModel;
import by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.view.CurrentNoticeView;
import io.realm.Realm;
import io.realm.RealmResults;

@InjectViewState
public class CurrentNoticePresenter extends MvpPresenter<CurrentNoticeView>{

    private final Realm realm;

    public CurrentNoticePresenter() {
        realm = Realm.getDefaultInstance();
    }

    public void addNoteInDatabase(final NoticeModel notice) {
        realm.executeTransaction(realm -> realm.insertOrUpdate(notice));    //don't use viewState
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
