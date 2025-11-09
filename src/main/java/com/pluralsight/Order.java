package com.pluralsight;


import java.util.ArrayList;

public class Order {
    private ArrayList<Product> items = new ArrayList<>(); //List of products

    //Add a product to the order
    public void add(Product p) {
        items.add(p);
    }



}
