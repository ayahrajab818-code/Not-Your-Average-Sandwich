package com.pluralsight;


import com.pluralsight.challengeYourself.BLT;
import com.pluralsight.challengeYourself.PhillyCheeseSteak;
import com.pluralsight.models.*;
import com.pluralsight.orders.Order;
import com.pluralsight.orders.ReceiptFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeScreen {

    //--------- Main Menu here --------------//
    public static void main(String[] args) {

        String homeMenu = """
                    ============================
                          Welcome to our 
                       NOT-YOU-AVERAGE-SANDWICH
                   ==============================
             //------------Main Menu------------//      
                What do you want to do?
                N) New Order
                X) Exit
                """;
        while (true) {
            System.out.println(homeMenu);
            String command = ConsoleHelper.promptForString("Enter command (N, X)").toUpperCase();
            switch (command) {
                case "N" -> {//Start new order
                    Order o = new Order();
                    boolean running = true;
                    String orderMenu = """
                    //------------------------//        
                            Order Menu
                 //------------------------//         
                    What would you like to add?
                    1) Add Sandwich
                    2) Add Drink
                    3) Add Chips
                    4) Checkout
                    5) Signature Sandwiches
                    0) Cancel Order
                    """;
                    while (running) {
                        System.out.println(orderMenu);
                        String c = ConsoleHelper.promptForString("Enter command (1, 2, 3, 4, 5, 0)").toUpperCase();
                        switch (c) {
                            case "1" -> o.add(addSandwich());
                            case "2" -> o.add(addDrink());
                            case "3" -> o.add(addChips());
                            case "4" -> {
                                checkout(o);
                                running = false;
                            }
                            case "5" ->{
                                o.add(addSignatureSandwich());
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


    //---------------------- Method to create a sandwich with user input -------------------//
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

            breadChoice = ConsoleHelper.promptForInt("Select your bread #");

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
         size = ConsoleHelper.promptForString("Choose your size (4/8/12 inches)");

        if (size.equals("4") || size.equals("8") || size.equals("12")) {
            break;
        }
        System.out.println("Invalid size! Please enter only 4, 8, or 12.");
    }
        //Toasted option
        //Variable to store toasted choice
        boolean toasted;
        while (true) {
        String t = ConsoleHelper.promptForString("Toasted? (Y/N)");
            if (t.equalsIgnoreCase("Y")) {
                toasted = true;
                break;
            } else if (t.equalsIgnoreCase("N")) {
                toasted = false;
                break;
            }

            System.out.println("Invalid input! Please enter Y or N.");
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
                choice = ConsoleHelper.promptForInt("Choose topping #");
                if (choice >= 1 && choice <= choices.length) break;
                System.out.println("Invalid topping number! Try again.");
            }
            //Extra option for premium toppings
            boolean extra = false;
            if (type.equals("MEAT") || type.equals("CHEESE")) {
                while (true) {
                    String extraInput = ConsoleHelper.promptForString("Extra? (Y/N): ");
                    if (extraInput.equalsIgnoreCase("Y")) {
                        extra = true;
                        break; // valid, exit loop
                    } else if (extraInput.equalsIgnoreCase("N")) {
                        extra = false;
                        break; // valid, exit loop
                    } else {
                        System.out.println("Invalid input! Please enter only Y or N."); // invalid
                    }
                }
            }
            //Add topping to sandwich
            s.addTopping(new ToppingItem(choices[choice - 1], type, extra));
        }

        //Return the Sandwich as a Product allows storing it in a list of Products
        return s;

    }


    //------------------ Method to create a drink -------------------//
    private static Product addDrink() {
        System.out.println("\n--- Add Drink ---");

        // Show sizes & prices
        System.out.println("Drink Sizes:");
        System.out.println("S - $2.00");
        System.out.println("M - $2.50");
        System.out.println("L - $3.00");

        //drink size
        String size;
        boolean availableSize = false;

        do {
            size = ConsoleHelper.promptForString("Choose drink size (S/M/L): ").toUpperCase();
            if (size.equals("S") || size.equals("M") || size.equals("L")) {
                availableSize = true;
            } else {
                System.out.println("Invalid size. Please enter S, M, or L.");
            }
        } while (!availableSize);

        // Print available flavors
        System.out.println("\nAvailable drink flavors:");
        for (String f : DrinkFlavor.FLAVORS) {
            System.out.println(" - " + f);
        }

        //flavor selection
        String flavor;
        boolean availableFlavor = false;

        do {
            flavor = ConsoleHelper.promptForString("Choose drink flavor: ");
            for (String f : DrinkFlavor.FLAVORS) {
                if (f.equalsIgnoreCase(flavor)) {
                    availableFlavor = true;
                    break;
                }
            }
            if (!availableFlavor) {
                System.out.println("That flavor is not listed.");
            }
        } while (!availableFlavor);

        System.out.println("Your drink order has been saved!");

        //Drink object contains its own pricing logic
        return new Drink(size, flavor);
    }



    //----------------- Method to create chips ------------------//
    private static Product addChips() {
        System.out.println("\n--- Add Chips ---");
        String flavor = ConsoleHelper.promptForString("what's your chips flavor");

        //Create and return Chips object
        return new Chips(flavor);
    }


    //----------------- Method to create Signature Sandwich ------------------//
    private static Product addSignatureSandwich(){
        System.out.println("\n--- Signature Sandwich ---");

        int command;
        while(true){
            System.out.println("Choose a signature sandwich");
            System.out.println("1) BLT");
            System.out.println("2) Philly Cheese Steak");

            command = ConsoleHelper.promptForInt("Please entre your choice (1 or 2)");
            if(command == 1 || command == 2) break;
            System.out.println("Invalid choice! Please select 1 or 2.");
        }
        Sandwich s;
        if(command == 1) s = new BLT(); //Create BLT sandwich
        else s = new PhillyCheeseSteak(); //Create Philly Cheese Steak


        System.out.println("Signature sandwich has been selected!");
        return s;
    }


    //------------------ Checkout method to show summary and save receipt --------------------//
    private static void checkout(Order o) {
        //Get current date/time
        LocalDateTime now = LocalDateTime.now();
        //Format how the date/time will look on screen
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");

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