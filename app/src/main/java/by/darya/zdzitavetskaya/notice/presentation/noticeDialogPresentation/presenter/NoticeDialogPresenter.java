package by.darya.zdzitavetskaya.notice.presentation.noticeDialogPresentation.presenter;

import android.widget.EditText;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.jakewharton.rxbinding3.widget.RxTextView;

import javax.inject.Inject;

import by.darya.zdzitavetskaya.notice.App;
import by.darya.zdzitavetskaya.notice.model.NoteModel;
import by.darya.zdzitavetskaya.notice.presentation.noticeDialogPresentation.view.NoticeDialogView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;

@InjectViewState
public class NoticeDialogPresenter extends MvpPresenter<NoticeDialogView> {

    @Inject
    Realm realm;

    public NoticeDialogPresenter() {
        App.getAppComponent().inject(this);
    }

    public void addNoteInDatabase(final NoteModel notice) {
        realm.executeTransaction(realm -> realm.insertOrUpdate(notice));    //don't use viewState
    }

    public void getNoticeFromDatabase(final String id) {
        final NoteModel notice = realm.where(NoteModel.class).equalTo("id", id).findFirst();
        getViewState().onNoticeSuccess(notice);
    }

    public void setUi(EditText etTitle, EditText etDescription){
        Observable<String> title = RxTextView.textChanges(etTitle).switchMap(charSequence -> Observable.just(charSequence.toString()));
        Observable<String> description = RxTextView.textChanges(etDescription).switchMap(charSequence -> Observable.just(charSequence.toString()));

        Observable
                .combineLatest(title, description, (s, s2) -> !s.isEmpty() & !s2.isEmpty())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(clickable -> getViewState().buttonClickable(clickable));
    }
}
