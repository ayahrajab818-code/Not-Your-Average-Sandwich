package com.pluralsight;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeScreen {

    //------Main Menu here-----
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
            String command = ConsoleHelper.promptForString("Enter command (N, X) ").toUpperCase();

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
                        String c = ConsoleHelper.promptForString("Enter command (1, 2, 3, 4, 0) ").toUpperCase();

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
                case "X" -> {
                    System.out.println("Exiting application..."); // Notify user
                    return; // Stop program
                }
                //Any other input
                default -> System.out.println("Invalid choice! Please enter N for New Order or X to Exit."); //Invalid input
            }
        }
    }


    //Method to create a sandwich with user input
    private static Product addSandwich() {
        System.out.println("\n--- Add Sandwich ---");
        //Variable to store bread choice
        int breadChoice;
        //Repeat until valid choice
        while (true) {
            System.out.println("Please choose a bread type ");
            //Loop through available breads
            for (int i = 0; i < BreadType.TYPES.length; i++) {
                System.out.println((i + 1) + ") " + BreadType.TYPES[i]);//Display option
            }

            breadChoice = ConsoleHelper.promptForInt("Select your bread # ");

            if (breadChoice >= 1 && breadChoice <= BreadType.TYPES.length) {
                break;
            }

            System.out.println("Invalid choice! Please select one of the options above.");
        }
        //Store chosen bread
        String bread = BreadType.TYPES[breadChoice - 1];

        //Ask the user for the sandwich size (4, 8, or 12 inches)
        String size;
        while (true) {
         size = ConsoleHelper.promptForString("Choose your size (4/8/12 inches) ");

        if (size.equals("4") || size.equals("8") || size.equals("12")) {
            break;
        }
        System.out.println("Invalid size! Please enter only 4, 8, or 12.");
    }
        //Toasted option
        //Variable to store toasted choice
        boolean toasted;
        while (true) {
        String t = ConsoleHelper.promptForString("Toasted? (Y/N) ");
            if (t.equalsIgnoreCase("Y")) {
                toasted = true;
                break;
            } else if (t.equalsIgnoreCase("N")) {
                toasted = false;
                break;
            }

            System.out.println("Invalid input! Please enter Y or N only.");
        }

        //Create a new Sandwich object using the chosen bread, size, and toasted option
        Sandwich s = new Sandwich(bread, size, toasted);

        //Start a loop to add toppings one by one
        while (true) {
            String type = ConsoleHelper.promptForString(
                    "Add topping type (MEAT/CHEESE/REGULAR/SAUCE/SIDE) or DONE "
            ).toUpperCase();

            if (type.equals("DONE")) break;

            String[] choices = null;

            //Array to store available toppings
            switch (type) {
                case "MEAT" -> choices = Topping.MEATS;
                case "CHEESE" -> choices = Topping.CHEESES;
                case "REGULAR" -> choices = Topping.REGULAR;
                case "SAUCE" -> choices = Topping.SAUCES;
                case "SIDE" -> choices = Topping.SIDES;
                default -> {
                    System.out.println("Invalid type! Please choose MEAT, CHEESE, REGULAR, SAUCE, SIDE or DONE.");
                    continue;
                }
            }

            // Show available toppings
            System.out.println("Available " + type + " toppings");
            for (int i = 0; i < choices.length; i++) {
                System.out.println((i + 1) + ") " + choices[i]); //List toppings
            }

            int choice;
            while (true) {
                choice = ConsoleHelper.promptForInt("Choose topping # ");
                if (choice >= 1 && choice <= choices.length) break;
                System.out.println("Invalid topping number! Try again.");
            }
            //Extra option for premium toppings
            boolean extra = false;
            if (type.equals("MEAT") || type.equals("CHEESE")) {
                extra = ConsoleHelper.promptForYesNo("Extra?");
            }
            //Add topping to sandwich
            s.addTopping(new ToppingItem(choices[choice - 1], type, extra));
        }

        //Return the Sandwich as a Product allows storing it in a list of Products
        return s;

    }


    //Method to create a drink
    private static Product addDrink() {
        System.out.println("\n--- Add Drink ---");

        String size = ConsoleHelper.promptForString("Drink size (S/M/L)");
        String flavor = ConsoleHelper.promptForString("Drink flavor ");
        //Create and return Drink object
        return new Drink(size, flavor);
    }


    //Method to create chips
    private static Product addChips() {
        System.out.println("\n--- Add Chips ---");
        String flavor = ConsoleHelper.promptForString("what's your chips flavor");

        //Create and return Chips object
        return new Chips(flavor);
    }


    //Checkout method to show summary and save receipt
    private static void checkout(Order o) {
        //Get current date/time
        LocalDateTime now = LocalDateTime.now();
        //Format how the date/time will look on screen
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("\n=== ORDER SUMMARY ===");
        System.out.println("Order Date/Time " + now.format(displayFormat));//Show formatted date/time in console

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