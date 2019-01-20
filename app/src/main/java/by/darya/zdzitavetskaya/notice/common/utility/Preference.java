package by.darya.zdzitavetskaya.notice.common.utility;

import android.content.Context;
import android.content.SharedPreferences;

public final class Preference {

    private static final String PREFS_NAME = "notice";
    private static final String COLOR = "color";
    private static final String DEF_VALUE = "#D81B60";

    public static String getColor(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getString(COLOR, DEF_VALUE);
    }

    public static void setColor(Context context, String color) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(COLOR, color).apply();
    }
}
