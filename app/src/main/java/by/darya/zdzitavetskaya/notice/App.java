package by.darya.zdzitavetskaya.notice;

import android.app.Application;

import by.darya.zdzitavetskaya.notice.utils.realm.CustomRealmConfiguration;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        setRealm();
    }

    public void setRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = CustomRealmConfiguration.getRealmConfiguration();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
