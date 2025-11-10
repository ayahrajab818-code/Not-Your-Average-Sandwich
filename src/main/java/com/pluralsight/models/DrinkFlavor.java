package com.pluralsight.models;


/*
 * This class stores all drink flavors.
 * static = belongs to class (no need to create object)
 * public = other classes can access it
 */

public class DrinkFlavor {
    public static String[] FLAVORS = {
            "Coke",
            "Pepsi",
            "Sprite",
            "Fanta",
            "Dr Pepper",
            "Root Beer"
    };
    public static boolean isValid(int choice) {
        // Valid if choice is greater than or equal to 1 AND less than or equal to the number of bread types
        return choice >= 1 && choice <= FLAVORS.length;
    }
}
