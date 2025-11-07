package com.pluralsight;


import java.util.ArrayList;

public class Sandwich extends Product{
    private String bread; //Bread type
    private String size; //Sandwich size
    private boolean toasted; //Toasted or not
    private ArrayList<ToppingItem> t = new ArrayList<>(); // List of toppings

    public Sandwich(String bread, String size, boolean toasted, ArrayList<ToppingItem> t) {
        this.bread = bread;
        this.size = size;
        this.toasted = toasted;
        this.t = t;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "";
    }
}

