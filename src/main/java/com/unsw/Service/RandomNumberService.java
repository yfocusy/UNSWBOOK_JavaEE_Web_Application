package com.unsw.Service;

import java.util.Random;

public class RandomNumberService {

    public String randomNumberGenerator(){
        int max=999999;
        int min=100000;
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;

        return s+"";
    }
}
