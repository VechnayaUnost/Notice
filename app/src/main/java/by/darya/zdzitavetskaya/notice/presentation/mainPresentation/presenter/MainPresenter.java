package by.darya.zdzitavetskaya.notice.presentation.mainPresentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import by.darya.zdzitavetskaya.notice.App;
import by.darya.zdzitavetskaya.notice.common.constants.Constants;
import by.darya.zdzitavetskaya.notice.model.NoteModel;
import by.darya.zdzitavetskaya.notice.model.view.BaseViewModel;
import by.darya.zdzitavetskaya.notice.model.view.NoticeViewModel;
import by.darya.zdzitavetskaya.notice.presentation.mainPresentation.view.MainView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    Realm realm;

    public MainPresenter() {
        App.getAppComponent().inject(this);
    }

    public void deleteAllSolvedFromDatabase() {
        realm.beginTransaction();
        RealmResults<NoteModel> realmResults = realm
                .where(NoteModel.class)
                .equalTo("isSolved", true)
                .findAll();

        realmResults.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    public void searchNotices(String search) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        Observable.just(search).debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap(this::getNotes)
                .subscribe(new Observer<RealmResults<NoteModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(RealmResults<NoteModel> noteModels) {
                        List<BaseViewModel> notes = new ArrayList<>();
                        for (NoteModel model : realm.copyFromRealm(noteModels)) {
                            notes.add(new NoticeViewModel(model));
                        }
                        getViewState().search(notes);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Observable<RealmResults<NoteModel>> getNotes(final String query) {

        return realm
                .where(NoteModel.class)
                .contains("title", query)
                .or()
                .contains("description", query)
                .sort(Constants.FIELD_NAME_DATE, Sort.DESCENDING)
                .findAll()
                .where()
                .equalTo("isSolved", false)
                .findAll()
                .asFlowable()
                .toObservable();
    }
}
