package com.pluralsight;

import com.pluralsight.models.ToppingItem;

public class BLT extends SignatureSandwich {
    public BLT(){
        super("white", "8",true);

        addTopping(new ToppingItem("Bacon", "MEAT", false));
    }
}
