package by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import by.darya.zdzitavetskaya.notice.model.NoteModel;
import by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.view.CurrentNoticeView;
import io.realm.Realm;
import io.realm.RealmResults;

@InjectViewState
public class CurrentNoticePresenter extends MvpPresenter<CurrentNoticeView>{

    private final Realm realm;

    public CurrentNoticePresenter() {
        realm = Realm.getDefaultInstance();
    }

    public void getNoticesFromDatabase() {
        final RealmResults<NoteModel> notices = realm.where(NoteModel.class).findAll();
        getViewState().onNoticesSuccess(notices);
    }

    public void getNoticeFromDatabase(final String id) {
        final NoteModel notice = realm.where(NoteModel.class).equalTo("id", id).findFirst();
        getViewState().onNoticeSuccess(notice);
    }
}
