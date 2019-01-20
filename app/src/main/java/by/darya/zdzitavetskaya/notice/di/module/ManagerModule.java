package by.darya.zdzitavetskaya.notice.di.module;

import android.content.Context;

import javax.inject.Singleton;

import by.darya.zdzitavetskaya.notice.common.manager.NetworkManager;
import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {

    @Singleton
    @Provides
    NetworkManager provideNetworkManager(Context context) {
        return new NetworkManager(context);
    }
}
