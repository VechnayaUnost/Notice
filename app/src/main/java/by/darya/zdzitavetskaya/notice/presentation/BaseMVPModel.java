package by.darya.zdzitavetskaya.notice.presentation;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseMVPModel {

    protected final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void onDestroyPresenter() {
        compositeDisposable.clear();
    }
}

