package com.pluralsight;


import java.util.ArrayList;

public class Order {
    private ArrayList<Product> entry = new ArrayList<>(); //List of products

    //Add a product to the order
    public void add(Product p) {
        entry.add(p);
    }
    //Return list of products
    public ArrayList<Product> getItems(){
        return entry;
    }




}
