package com.pluralsight;
/*
 * This class stores toppings.
 */
public class Topping {
    //Meat toppings (premium)
    public static String[] MEATS = {
            "Steak",
            "Ham",
            "Salami",
            "Roast Beef",
            "Chicken",
            "Bacon"
    };
    //Cheese toppings (premium)
    public static String[] CHEESES = {
            "American",
            "Provolone",
            "Cheddar",
            "Swiss"
    };
    //Regular toppings (free)
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

