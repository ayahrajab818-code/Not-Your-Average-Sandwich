package com.pluralsight;

import com.pluralsight.models.Sandwich;

public abstract class SignatureSandwich extends Sandwich {
    public SignatureSandwich(String bread, String size, boolean toasted) {
        super(bread, size, toasted);
    }

}
