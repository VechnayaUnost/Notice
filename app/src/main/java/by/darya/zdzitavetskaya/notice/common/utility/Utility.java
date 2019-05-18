package by.darya.zdzitavetskaya.notice.common.utility;

import android.text.format.DateFormat;

import java.util.Date;

public final class Utility {

    public static String getFormatDate(final Date date) {
        if (date == null) {
            return "";
        }
        return DateFormat.format("d MMM yyyy hh:mm", date).toString();
    }
}
