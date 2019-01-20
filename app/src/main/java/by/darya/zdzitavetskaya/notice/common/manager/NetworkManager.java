package by.darya.zdzitavetskaya.notice.common.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.realm.internal.Util;

public class NetworkManager {

    private Context context;

    public NetworkManager(Context context) {
        this.context = context;
    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if(connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        return ((networkInfo != null && networkInfo.isConnected()) || Util.isEmulator());
    }

    private Callable<Boolean> isReachable() {   //for ex. if need to check access to the site
        return () -> {
            try {
                if (!isOnline()) {
                    return false;
                }

                URL url = new URL("https://api.vk.com"); //url for ex.
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(2000);
                httpURLConnection.connect();

                return true;
            } catch (Exception e) {
                return false;
            }
        };
    }

    public Observable<Boolean> getNetworkObservable() {
        return Observable.fromCallable(this::isOnline);
    }
}
