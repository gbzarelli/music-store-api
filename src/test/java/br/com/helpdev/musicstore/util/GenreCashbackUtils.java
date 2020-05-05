package br.com.helpdev.musicstore.util;

import java.util.ArrayList;
import java.util.List;

public class GenreCashbackUtils {

    public static final List<GenreCashbackMap> genreCashbackMaps = new ArrayList<>();

    static {
        int cashback = 1;
        for (int genreId = 1; genreId <= 4; genreId++)
            for (int weekday = 1; weekday <= 7; weekday++) {
                genreCashbackMaps.add(new GenreCashbackMap(genreId, weekday, cashback++));
            }
    }


    public static class GenreCashbackMap {
        public int genreId;
        public int weekday;
        public int cashback;

        GenreCashbackMap(int genreId, int weekday, int cashback) {
            this.genreId = genreId;
            this.weekday = weekday;
            this.cashback = cashback;
        }
    }

}
