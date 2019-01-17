package by.darya.zdzitavetskaya.notice.di.component;

import javax.inject.Singleton;

import by.darya.zdzitavetskaya.notice.MainActivity;
import by.darya.zdzitavetskaya.notice.di.module.ApplicationModule;
import by.darya.zdzitavetskaya.notice.di.module.ManagerModule;
import by.darya.zdzitavetskaya.notice.di.module.RealmModule;
import by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.presenter.CurrentNoticePresenter;
import by.darya.zdzitavetskaya.notice.presentation.noticeDialogPresentation.presenter.NoticeDialogPresenter;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RealmModule.class})
public interface AppComponent {

    void inject(MainActivity activity);

    //presenters
    void inject(CurrentNoticePresenter currentNoticePresenter);
    void inject(NoticeDialogPresenter noticeDialogPresenter);

}
