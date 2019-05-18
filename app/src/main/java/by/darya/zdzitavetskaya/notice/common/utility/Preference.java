package by.darya.zdzitavetskaya.notice.common.utility;

import android.content.Context;
import android.content.SharedPreferences;

public final class Preference {

    private static final String PREFS_NAME = "notice";
    private static final String THEME_NAME = "themeName";
    private static final String DEF_VALUE = "#D81B60";

    public static String getThemeName(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getString(THEME_NAME, DEF_VALUE);
    }

    public static void setThemeName(Context context, String themeName) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(THEME_NAME, themeName).apply();
    }
}
