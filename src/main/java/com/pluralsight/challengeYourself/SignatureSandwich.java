package com.pluralsight.challengeYourself;

import com.pluralsight.models.Sandwich;
import com.pluralsight.models.ToppingItem;

// class to define preset "signature" sandwiches
public class SignatureSandwich {

    // -------- BLT Signature Sandwich --------
    public static Sandwich BLT() {
        // Create a new Sandwich object with:
        // Bread: White, Size: 8 inches, Toasted: true
        Sandwich s = new Sandwich("White", "8", true);

        // Add default toppings for a BLT sandwich
        s.addTopping(new ToppingItem("Bacon", "MEAT", false));
        s.addTopping(new ToppingItem("Cheddar", "CHEESE", false));
        s.addTopping(new ToppingItem("Lettuce", "REGULAR", false));
        s.addTopping(new ToppingItem("Tomato", "REGULAR", false));
        s.addTopping(new ToppingItem("Ranch", "SAUCE", false));
        // Return the fully prepared BLT sandwich object
        return s;
    }

    // -------- Philly Cheese Steak Signature Sandwich --------
    public static Sandwich PhillyCheeseSteak() {
        // Create a new Sandwich object with:
        // Bread: White, Size: 8 inches, Toasted: tru
        Sandwich s = new Sandwich("White", "8", true);

        // Add default toppings for Philly Cheese Steak
        s.addTopping(new ToppingItem("Steak", "MEAT", false));
        s.addTopping(new ToppingItem("American", "CHEESE", false));
        s.addTopping(new ToppingItem("Peppers", "REGULAR", false));
        s.addTopping(new ToppingItem("Mayo", "SAUCE", false));

        // Return the fully prepared Philly Cheese Steak sandwich
        return s;
    }

}
