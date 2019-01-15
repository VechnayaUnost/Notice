package by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;
import java.util.concurrent.Callable;

import by.darya.zdzitavetskaya.notice.constants.Constants;
import by.darya.zdzitavetskaya.notice.model.NoteModel;
import by.darya.zdzitavetskaya.notice.model.view.BaseViewModel;
import by.darya.zdzitavetskaya.notice.model.view.NoticeViewModel;
import by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.view.CurrentNoticeView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class CurrentNoticePresenter extends MvpPresenter<CurrentNoticeView>{

    private final Realm realm;

    public CurrentNoticePresenter() {
        realm = Realm.getDefaultInstance();
    }

    public void getNoticesFromDatabase() {
        loadData();
//        final RealmResults<NoteModel> notices = realm.where(NoteModel.class).findAll();
//        getViewState().onNoticesSuccess(notices);
    }

//    public void getNoticeFromDatabase(final String id) {
//        final NoteModel notice = realm.where(NoteModel.class).equalTo("id", id).findFirst();
//        getViewState().onNoticeSuccess(notice);
//    }

    private Observable<BaseViewModel> onLoadFromDb() {
        return Observable.fromCallable(getCallableNoteList())
                .flatMap(Observable::fromIterable)
                .flatMap(note -> Observable.fromCallable(() -> new NoticeViewModel(note)));
    }

    private Callable<List<NoteModel>> getCallableNoteList() {
        return () -> {
            RealmResults<NoteModel> realmResults = realm
                    .where(NoteModel.class)
                    .sort(Constants.FIELD_NAME_DATE, Sort.DESCENDING)
                    .findAll();
            return realm.copyToRealm(realmResults);
        };
    }

    private void loadData() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(
                onLoadFromDb()
                        .toList()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                notes -> getViewState().onNoticesSuccess(notes),
                                Throwable::printStackTrace
                        ));
    }
}
