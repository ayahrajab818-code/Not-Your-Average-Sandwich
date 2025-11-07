package com.pluralsight;


import java.util.ArrayList;

public class Sandwich extends Product{
    private String bread; //Bread type
    private String size; //Sandwich size
    private boolean toasted; //Toasted or not
    private ArrayList<ToppingItem> toppings = new ArrayList<>(); // List of toppings

    public Sandwich(String bread, String size, boolean toasted) {
        this.bread = bread;
        this.size = size;
        this.toasted = toasted;
    }

    //Add topping to the sandwich
    public void addTopping(ToppingItem t) {
        toppings.add(t); //Add topping object to list
    }


        // Base price by size
        @Override
        public double getPrice() {
            //Start with a base sandwich price depending on size (in inches)
        double base = 0;
            //Determine base price based on sandwich size
            switch(size) {
                case "4":
                    base = 5.50; //Base price for a 4-inch sandwich
                    break;
                case "8":
                    base = 7.00; //Base price for an 8-inch sandwich
                    break;
                case "12":
                    base = 8.50; //Base price for a 12-inch sandwich
                    break;
                default:
                    base = 0;  //Unknown size results in zero charge
                    break;
            }
            // Start calculating total with the base sandwich price
            double total = base;





    @Override
    public String getDescription() {
        return "";
    }
}

