package com.utn.tools;

import java.util.UUID;

public class idHandler {

    public static int setId() {
        //"Try and catch" parseInt throw an exception if the String don't have the correct format ex: "null"
        try {
            return Integer.parseInt(UUID.randomUUID().toString().replace("-", "").substring(0, 10));
            //Get a random UUID , transform it to String, reduce it to 10 character long and replace the " - " symbol
            // in order to get a clean alphanumeric id
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
