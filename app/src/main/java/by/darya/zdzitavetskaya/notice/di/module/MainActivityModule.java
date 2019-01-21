package by.darya.zdzitavetskaya.notice.di.module;

import javax.inject.Singleton;

import by.darya.zdzitavetskaya.notice.MainActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Singleton
    @Provides
    public MainActivity context() {
        return mainActivity;
    }
}