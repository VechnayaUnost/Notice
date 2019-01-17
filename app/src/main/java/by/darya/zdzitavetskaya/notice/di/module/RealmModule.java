package by.darya.zdzitavetskaya.notice.di.module;

import android.content.Context;

import javax.inject.Singleton;

import by.darya.zdzitavetskaya.notice.utils.realm.CustomRealmConfiguration;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module
public class RealmModule {

    @Provides
    @Singleton
    Realm provideRealm(Context context) {
        Realm.init(context);
        RealmConfiguration realmConfiguration = CustomRealmConfiguration.getRealmConfiguration();
        Realm.setDefaultConfiguration(realmConfiguration);
        return Realm.getDefaultInstance();
    }
}
