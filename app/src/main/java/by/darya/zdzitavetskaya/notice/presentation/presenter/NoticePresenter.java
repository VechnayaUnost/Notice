package by.darya.zdzitavetskaya.notice.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import by.darya.zdzitavetskaya.notice.model.NoticeModel;
import by.darya.zdzitavetskaya.notice.presentation.view.NoticeView;
import io.realm.Realm;
import io.realm.RealmResults;

@InjectViewState
public class NoticePresenter extends MvpPresenter<NoticeView>{

    private Realm realm;

    public NoticePresenter() {
        realm = Realm.getDefaultInstance();
        //NoticeModel noticeModel = new NoticeModel("title", "ergerger", true);
        //addNoteInDatabase(noticeModel);
        //getNoticesFromDatabase();
        //getNoticeFromDatabase("a1d7f7ba-063e-45bb-ba71-cf6b0b21df83");
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
