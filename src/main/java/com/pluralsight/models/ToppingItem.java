package com.pluralsight.models;

// Represents a single topping selected for a sandwich
public class ToppingItem {
    private String name;   // Name of topping
    private String category;   //topping: MEAT, CHEESE, REGULAR, SAUCE
    private boolean extra; // True if extra portion is requested

    // Constructor
    public ToppingItem(String name, String category, boolean extra) {
        this.name = name;   // Assign name
        this.category= category;
        this.extra = extra; // Assign extra flag
    }

    // Getter for topping name
    public String getName() {
        return name;
    }


    public String getCategory() {
        return category;
    }

    // Getter for extra flag
    public boolean isExtra() {
        return extra;
    }
}


