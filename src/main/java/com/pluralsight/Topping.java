package com.pluralsight;
/*
 * This class stores toppings.
 * Meat and cheese cost extra, regular toppings are free.
 */
public class Topping {
    public static String[] MEATS = {
            "Steak",
            "Ham",
            "Salami",
            "Roast Beef",
            "Chicken",
            "Bacon"
    };

    public static String[] CHEESES = {
            "American",
            "Provolone",
            "Cheddar",
            "Swiss"
    };

    public static String[] REGULAR = {
            "Lettuce",
            "Peppers",
            "Onions",
            "Tomatoes",
            "Jalapeños",
            "Cucumbers",
            "Pickles",
            "Guacamole",
            "Mushrooms"
    };

    //reuse Sauce options
    public static String[] SAUCES = Sauce.SAUCES;
}

