package com.pluralsight;
/*
 * Product is a parent class for all items (Sandwich, Drink, Chips).
 * Abstract = cannot create a Product directly, only subclasses.
 */
public abstract class Product {

    //Every product must return its price
    public abstract double getPrice();

    //Every product must return a description for receipts
    public abstract String getDescription();
}
