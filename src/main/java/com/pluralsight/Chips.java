package com.pluralsight;

public class Chips extends Product{
    private String flavor; //Flavor of chips

    public Chips(String flavor) {
        this.flavor = flavor;
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
