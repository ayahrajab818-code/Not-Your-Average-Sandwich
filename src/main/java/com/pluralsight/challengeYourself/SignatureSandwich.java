package com.pluralsight.challengeYourself;

import com.pluralsight.models.Sandwich;
import com.pluralsight.models.ToppingItem;

public abstract class SignatureSandwich {
    public static Sandwich BLT() {
        Sandwich s = new Sandwich("White", "8", true);
        s.addTopping(new ToppingItem("Bacon", "MEAT", false));
        s.addTopping(new ToppingItem("Cheddar", "CHEESE", false));
        s.addTopping(new ToppingItem("Lettuce", "REGULAR", false));
        s.addTopping(new ToppingItem("Tomato", "REGULAR", false));
        s.addTopping(new ToppingItem("Ranch", "SAUCE", false));
        return s;
    }

    public static Sandwich PhillyCheeseSteak() {
        Sandwich s = new Sandwich("White", "8", true);
        s.addTopping(new ToppingItem("Steak", "MEAT", false));
        s.addTopping(new ToppingItem("American", "CHEESE", false));
        s.addTopping(new ToppingItem("Peppers", "REGULAR", false));
        s.addTopping(new ToppingItem("Mayo", "SAUCE", false));
        return s;
    }

}
