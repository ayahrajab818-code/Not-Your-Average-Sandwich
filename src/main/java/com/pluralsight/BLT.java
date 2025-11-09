package com.pluralsight;

import com.pluralsight.models.ToppingItem;

public class BLT extends SignatureSandwich {
    public BLT(){
        super("white", "8",true);

        //Add default toppings
        addTopping(new ToppingItem("Bacon", "MEAT", false));
        addTopping(new ToppingItem("Cheddar", "CHEESE", false));
        addTopping(new ToppingItem("Lettuce", "REGULAR", false));
        addTopping(new ToppingItem("Tomato", "REGULAR", false));
        addTopping(new ToppingItem("Ranch", "SAUCE", false));
    }
}
