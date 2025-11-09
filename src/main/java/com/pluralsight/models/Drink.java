package com.pluralsight.models;

public class Drink extends Product {

    private String size; //Size S/M/L
    private String flavor; //Drink flavor

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    //Get price based on size
    @Override
    public double getPrice() {
        if(size.equals("S"))
            return 2.00;
        else if(size.equals("M"))
            return 2.50;
        else
            return 3.00; //Large size
    }

    //Return description for receipt
    @Override
    public String getDescription() {
        return size + "Drink (" + flavor + ")";
    }
}