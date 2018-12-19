package by.darya.zdzitavetskaya.notice;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import by.darya.zdzitavetskaya.notice.utils.realm.CustomRealmConfiguration;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        setLeakCanary();
        setRealm();
    }

    public void setRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = CustomRealmConfiguration.getRealmConfiguration();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public void setLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        refWatcher = LeakCanary.install(this);
    }
}
