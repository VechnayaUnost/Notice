package by.darya.zdzitavetskaya.notice;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import by.darya.zdzitavetskaya.notice.di.component.AppComponent;
import by.darya.zdzitavetskaya.notice.di.component.DaggerAppComponent;
import by.darya.zdzitavetskaya.notice.di.module.ApplicationModule;

public class App extends Application {

    private RefWatcher refWatcher;

    private static AppComponent sAppComponent;

    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();
        setLeakCanary();
    }

    public void setLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    private void initComponent() {
        sAppComponent = DaggerAppComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

}
