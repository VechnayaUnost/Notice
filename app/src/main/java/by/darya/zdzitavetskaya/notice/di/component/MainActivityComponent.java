package by.darya.zdzitavetskaya.notice.di.component;

import javax.inject.Singleton;

import by.darya.zdzitavetskaya.notice.di.module.MainActivityModule;
import by.darya.zdzitavetskaya.notice.ui.holder.NoticeViewHolder;
import dagger.Component;

@Singleton
@Component(modules = {MainActivityModule.class})
public interface MainActivityComponent {

    void inject(NoticeViewHolder noticeViewHolder);
}
