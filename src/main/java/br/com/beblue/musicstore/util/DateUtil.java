package br.com.beblue.musicstore.util;

import lombok.experimental.UtilityClass;

import java.util.Calendar;

@UtilityClass
public class DateUtil {
    public int getDayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }
}
