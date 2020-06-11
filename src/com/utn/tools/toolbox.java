package com.utn.tools;

import java.util.Random;
import java.util.UUID;

public class toolbox {

    public static String setId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
            //Get a random UUID , transform it to String, reduce it to 10 character long and replace the " - " symbol
            // in order to get a clean alphanumeric id
    }

    public static Double costRandom(){
        //Return value from 150 to 300 with only 2 decimals
        return Math.round(Math.random()*(150-300+1)+300 * 100d) / 100d;
    }



}
