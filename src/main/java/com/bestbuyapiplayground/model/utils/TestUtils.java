package com.bestbuyapiplayground.model.utils;

import java.util.Random;

public class TestUtils {

    public static String getRandomValue(){
        Random random = new Random();
        int randomInt = random.nextInt(100);
        return Integer.toString(randomInt);
    }

    public static int getRandomIntegerValue(){
        Random random = new Random();
        int randomInteger = random.nextInt(100);
        return randomInteger;
    }
}
