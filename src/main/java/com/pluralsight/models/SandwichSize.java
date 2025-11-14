package com.pluralsight.models;
/*
 * This class stores sandwich sizes.
 */
public class SandwichSize {
    public static String[] SIZES = {
            "4",
            "8",
            "12"
    };
    public static boolean isValid(int choice) {
        // Valid if choice is greater than or equal to 1 AND less than or equal to the number of bread types
        return choice >= 1 && choice <= SIZES.length;
    }

}
