package br.com.beblue.musicstore.util;

import java.util.Random;

public class PriceUtil {
    public static Double generateRandomPriceDisc() {
        int rangeMin = 10;
        int rangeMax = 100;
        return rangeMin + (rangeMax - rangeMin) * new Random(System.nanoTime()).nextDouble();
    }
}
