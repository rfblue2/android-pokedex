package com.rfong.pokedex;

/**
 * Created by rfong on 4/29/17.
 */

public class Utils {

    /**
     * Capitalize the first letter of input and return the new string
     * @param lower
     * @return
     */
    public static String toUpper(String lower) {
        return lower.substring(0,1).toUpperCase() + lower.substring(1); // make first letter uppercase
    }
}
