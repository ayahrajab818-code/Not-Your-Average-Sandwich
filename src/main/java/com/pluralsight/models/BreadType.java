package com.pluralsight.models;
/*
 * This class stores all bread options for sandwiches.
 */
public class BreadType {
    public static String[] TYPES = {
            "White",
            "Wheat",
            "Rye",
            "Wrap"
    };

    public static boolean isValid(int choice) {
        return choice >= 1 && choice <= TYPES.length;
    }
}
