package br.com.helpdev.musicstore.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Calendar;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {
    public static int getDayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }
}
