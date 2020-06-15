package com.utn.tools;

import java.io.Serializable;
import java.util.UUID;

public class toolbox implements Serializable {
    toolbox(){}

    /**Get a random UUID , transform it to String,
     * reduce it to 10 character long and replace the " - " symbol
     * in order to get a clean alphanumeric id.
     * @return {@link String} = clean alphanumeric id
     */
    public static String setId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

    /**
     * Get a random value for the cost.
     *
     * @return {@link Double} = value from 150 to 300 with only 2 decimals
     */
    public static Double costRandom(){
        return Math.round(Math.random()*(150-300+1)+300 * 100d) / 100d;
    }

}
