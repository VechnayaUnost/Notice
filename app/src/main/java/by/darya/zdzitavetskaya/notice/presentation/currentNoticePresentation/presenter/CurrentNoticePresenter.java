package by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import by.darya.zdzitavetskaya.notice.App;
import by.darya.zdzitavetskaya.notice.common.manager.NetworkManager;
import by.darya.zdzitavetskaya.notice.common.constants.Constants;
import by.darya.zdzitavetskaya.notice.model.NoteModel;
import by.darya.zdzitavetskaya.notice.model.view.BaseViewModel;
import by.darya.zdzitavetskaya.notice.model.view.NoticeViewModel;
import by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.view.CurrentNoticeView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class CurrentNoticePresenter extends MvpPresenter<CurrentNoticeView>{

    @Inject
    Realm realm;

    @Inject
    NetworkManager networkManager;

    private boolean isLoading;

    public CurrentNoticePresenter() {
        App.getAppComponent().inject(this);
    }

    public void getNoticesFromDatabase() {
        loadData();
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
            return realm.copyFromRealm(realmResults);
        };
    }

    private void loadData() {
        if(isLoading) {
            return;
        }
        isLoading = true;
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(networkManager.getNetworkObservable()
                .flatMap(aBoolean -> {
                    if(!aBoolean)
                        return Observable.empty();

                    return aBoolean
                            ?onLoadFromDb() //need to load from network
                            :onLoadFromDb();
                })
                        .toList()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                notes -> getViewState().onNoticesSuccess(notes),
                                Throwable::printStackTrace
                        ));
    }
}
