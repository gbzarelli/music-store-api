package br.com.beblue.musicstore.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PriceUtil {
    public static Double generateRandomPriceDisc() {
        int rangeMin = 10;
        int rangeMax = 100;
        return rangeMin + (rangeMax - rangeMin) * new Random(System.nanoTime()).nextDouble();
    }
}
