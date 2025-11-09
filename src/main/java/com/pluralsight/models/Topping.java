package com.pluralsight.models;

/*
 * This class stores toppings.
 */
public class Topping {

    // Arrays of available toppings
    public static String[] MEATS = {
            "Steak",
            "Ham",
            "Salami",
            "Roast Beef",
            "Chicken",
            "Bacon" };
    public static String[] CHEESES = {
            "American",
            "Provolone",
            "Cheddar",
            "Swiss" };
    public static String[] REGULAR = {
            "Lettuce",
            "Peppers",
            "Onions",
            "Tomatoes",
            "Jalapeños",
            "Cucumbers",
            "Pickles",
            "Guacamole",
            "Mushrooms" };
    // Side toppings (optional)
    public static String[] SIDES = {
            "Au Jus",
            "Sauce"
    };
    public static String[] SAUCES = Sauce.SAUCES;

    // Instance variables
    private String name;
    private String category;
    private boolean extra;

    // Add this constructor
    public Topping(String name, String category, boolean extra) {
        this.name = name;
        this.category = category;
        this.extra = extra;
    }


}


