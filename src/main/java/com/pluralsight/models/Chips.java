package com.pluralsight.models;

public class Chips extends Product {
    private String flavor; //Flavor of chips

    public Chips(String flavor) {
        this.flavor = flavor;
    }
    //Chips price
    @Override
    public double getPrice() {
        return 1.50;
    }

    //Return description for receipt
    @Override
    public String getDescription() {
        return "chips (" + flavor + ")";
    }
}
