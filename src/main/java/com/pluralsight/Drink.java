package com.pluralsight;

public class Drink extends Product{

    private String size; //Size S/M/L
    private String flavor; //Drink flavor

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    //Get price based on size
    @Override
    public double getPrice() {
        return 0;
    }
    
    //Return description for receipt
    @Override
    public String getDescription() {
        return "";
    }
}