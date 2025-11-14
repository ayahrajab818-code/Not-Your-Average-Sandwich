package com.pluralsight.models;
/*
 * This class stores all bread options for sandwiches.
 */
public class BreadType {
    // A class-level (static) array that stores all available types/options for this category
    public static String[] TYPES = {
            "White",
            "Wheat",
            "Rye",
            "Wrap"
    };

    // Method to check if a given choice number is valid
    // @param choice: the number the user selected (1-based index)
    // @return true if the choice is within the valid range of bread types
    public static boolean isValid(int choice) {
        // Valid if choice is greater than or equal to 1 AND less than or equal to the number of bread types
        return choice >= 1 && choice <= TYPES.length;
    }
}
