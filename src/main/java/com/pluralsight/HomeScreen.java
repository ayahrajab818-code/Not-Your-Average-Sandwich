package com.pluralsight;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeScreen {
    public static void main(String[] args) {

        String homeMenu = """
                //------------Main Menu------------//
                ========================
                What do you want to do?
                1) New Order
                0) Exit
                =======================
                """;

        while (true) {
            System.out.println(homeMenu);
            String command = ConsoleHelper.promptForString("Enter command (N, X): ").toUpperCase();

            switch (command) {

                //Start new order
                case "N" -> {
                    Order o = new Order();
                    boolean running = true;

                    String orderMenu = """
                            //------------Order Menu------------//
                            ========================
                            What would you like to add?
                            1) Add Sandwich
                            2) Add Drink
                            3) Add Chips
                            4) Checkout
                            0) Cancel Order
                            =======================
                            """;

                    while (running) {
                        System.out.println(orderMenu);
                        String c = ConsoleHelper.promptForString("Enter command (1, 2, 3, 4, 0): ").toUpperCase();

                        switch (c) {
                            case "1" -> o.add(addSandwich());
                            case "2" -> o.add(addDrink());
                            case "3" -> o.add(addChips());
                            case "4" -> {
                                checkout(o);
                                running = false;
                            }
                            case "0" -> {
                                System.out.println("Exiting application.");
                                return;
                            }
                            default -> System.out.println("INVALID COMMAND! Please try again.");
                        }
                    }
                }
            }
        }
    }

    private static Product addSandwich() {
        //Prompt user to choose bread type
        System.out.println("Please choose a bread type: ");

        //Display all available bread types with numbers
        for (int i = 0; i < BreadType.TYPES.length; i++) {
            System.out.println((i + 1) + ") " + BreadType.TYPES[i]);
        }

        //Read the user's selection and get the corresponding bread type
        String bread = BreadType.TYPES[ConsoleHelper.promptForInt("Select your brad  #: ") - 1];

        //Ask the user for the sandwich size (4, 8, or 12 inches)
        String size = ConsoleHelper.promptForString("Choose your size (4/8/12 inches): ");

        //Ask if the sandwich should be toasted
        boolean toasted = ConsoleHelper.promptForYesNo("Toasted?");

        //Create a new Sandwich object using the chosen bread, size, and toasted option
        Sandwich s = new Sandwich(bread, size, toasted);

        //Start a loop to add toppings one by one
        while(true){
            //Ask user for the topping type or "DONE" to finish
            String category = ConsoleHelper.promptForString(
                    "Add your topping type (MEAT/CHEESE/REGULAR/SAUCE) or DONE to finish: "
            ).toUpperCase();

            // Exit the loop if the user types "DONE"
            if(category.equals("DONE")) break;

            // Ask user for the topping name
            String name = ConsoleHelper.promptForString("What's your topping name: ");
            boolean extra = false;

            //If the topping is meat or cheese, ask if the user wants extra
            if(category.equals("MEAT") || category.equals("CHEESE")){
                extra = ConsoleHelper.promptForYesNo("Do you want to add extras?");
            }
            //Add the topping to the sandwich
            s.addTopping(new ToppingItem(name, category, extra));
        }

        //Return the Sandwich as a Product allows storing it in a list of Products
        return s;

    }
    //Creates a drink and returns it
    private static Product addDrink() {
        String size = ConsoleHelper.promptForString("Drink size (S/M/L):");
        String flavor = ConsoleHelper.promptForString("Drink flavor: ");
        //Create and return Drink object
        return new Drink(size, flavor);
    }
    //Creates chips
    private static Product addChips() {
        String flavor = ConsoleHelper.promptForString("what's your chips flavor");

        //Create and return Chips object
        return new Chips(flavor);
    }
    //Shows the order summary and writes receipt file
    private static void checkout(Order o) {
        //Get current date/time
        LocalDateTime now = LocalDateTime.now();
        //Format how the date/time will look on screen
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("\n=== ORDER SUMMARY ===");
        System.out.println("Order Date/Time: " + now.format(displayFormat));//Show formatted date/time in console

        //Loop through all entry and print description
        for(Product p : o.getEntry()){
            System.out.println(p.getDescription());
        }

        //Display the total price of everything
        System.out.println("TOTAL: $" + o.getTotal());
        //Ask if user wants to confirm purchase
        if(ConsoleHelper.promptForYesNo("Would you like to confirm order? ")) {
            //Writes receipt to a file
            ReceiptFile.writeReceipt(o);
            System.out.println("Receipt saved! ");
        }
        //If declined, cancel
        else System.out.println("Order cancelled! ");

    }
}