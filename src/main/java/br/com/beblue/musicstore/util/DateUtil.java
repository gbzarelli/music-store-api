package br.com.beblue.musicstore.util;

import java.util.Calendar;

public class DateUtil {
    public static int getDayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }
}
