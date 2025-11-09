package com.pluralsight.challengeYourself;

import com.pluralsight.models.ToppingItem;

public class PhillyCheeseSteak extends SignatureSandwich {
    public PhillyCheeseSteak(){
        super("white","8", true);

        addTopping(new ToppingItem("Steak", "MEAT", false));
        addTopping(new ToppingItem("American", "CHEESE", false));
        addTopping(new ToppingItem("Peppers", "REGULAR", false));
        addTopping(new ToppingItem("Mayo", "SAUCE", false));
    }
}
