package br.com.helpdev.musicstore.util;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @Test
    void valid_day_of_week() {
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        assertEquals(dayOfWeek, DateUtil.getDayOfWeek());
    }
}