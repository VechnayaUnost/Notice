package by.darya.zdzitavetskaya.notice.presentation.completedNoticePresentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import by.darya.zdzitavetskaya.notice.App;
import by.darya.zdzitavetskaya.notice.common.constants.Constants;
import by.darya.zdzitavetskaya.notice.model.NoteModel;
import by.darya.zdzitavetskaya.notice.model.view.BaseViewModel;
import by.darya.zdzitavetskaya.notice.model.view.NoticeViewModel;
import by.darya.zdzitavetskaya.notice.presentation.completedNoticePresentation.view.CompletedNoticeView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class CompletedNoticePresenter extends MvpPresenter<CompletedNoticeView> {

    @Inject
    Realm realm;

    public CompletedNoticePresenter() {
        App.getAppComponent().inject(this);
    }

    public void getNoticesFromDatabase() {
        loadData();
    }

    private Observable<RealmResults<NoteModel>> getNotes() {

        return realm
                .where(NoteModel.class)
                .equalTo("isSolved", true)
                .sort(Constants.FIELD_NAME_DATE, Sort.DESCENDING)
                .findAll()
                .asFlowable()
                .toObservable();
    }

    private void loadData() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        getNotes()
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
                        getViewState().onNoticesSuccess(notes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }
}
