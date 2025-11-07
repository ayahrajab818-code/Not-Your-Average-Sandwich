package com.pluralsight;

public class ToppingItem {
    private String name;
    private String category ; //Topping type: MEAT, CHEESE, REGULAR, SAUCE
    private boolean extra; //True if customer wants extra amount

    public ToppingItem(String name, String category , boolean extra) {
        this.name = name;
        this.category  = category ;
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory () {
        return category ;
    }

    public void setCategory (String category ) {
        this.category  = category ;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }


}
