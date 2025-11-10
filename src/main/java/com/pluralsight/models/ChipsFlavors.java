package com.pluralsight.models;

// This class stores all the chips flavors
public class ChipsFlavors {

    public static String[] Flavors = {
            "Salt & Vinegar",
            "Cheese ",
            "Hot & Spicy",
            "Ghost Pepper",
            "Flamin' Hot",
            "Lime & Black Pepper"
    };
    public static boolean isValid(int choice) {
        // Valid if choice is greater than or equal to 1 AND less than or equal to the number of bread types
        return choice >= 1 && choice <= Flavors.length;
    }
}
