package br.com.beblue.musicstore.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class PriceUtil {
    public Double generateRandomPriceDisc() {
        int rangeMin = 10;
        int rangeMax = 100;
        return rangeMin + (rangeMax - rangeMin) * new Random(System.nanoTime()).nextDouble();
    }
}
