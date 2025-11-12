package com.pluralsight.UserInterFace;

import com.pluralsight.challengeYourself.SignatureSandwich;
import com.pluralsight.models.*;
import com.pluralsight.orders.Order;
import com.pluralsight.orders.ReceiptFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UserInterFace {
    
        //--------- Main Menu here --------------//
        public void display() {
            String homeMenu = """
                           ============================
                                 Welcome to our 
                              NOT-YOU-AVERAGE-SANDWICH
                          ==============================
                    //------------Main Menu------------//      
                       What do you want to do?
                       1) New Order
                       0) Exit
                    """;
            while (true) {
                System.out.println(homeMenu);
                String command = ConsoleHelper.promptForString("Enter command (1, 0)");
                switch (command) {
                    case "1" -> {//Start new order
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
                                case "5" -> {
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
                    case "0" -> {
                        System.out.println("Exiting application..."); // Notify user
                        return; // Stop program
                    }
                    //Any other input
                    default ->
                            System.out.println("Invalid choice! Please enter 1 for New Order or 0 to Exit."); //Invalid input
                }
            }
        }


//---------------------- Method to create a sandwich with user input -------------------//
    private static Product addSandwich() {
        Sandwich s = ConsoleHelper.buildSandwich();
        return s;
    }



//------------------ Method to create a drink -------------------//
    private static Product addDrink() {
        System.out.println("\n--- Add Drink ---");

        // Show drink sizes and prices
        System.out.println("Drink Sizes:");
        System.out.println("S - $2.00");
        System.out.println("M - $2.50");
        System.out.println("L - $3.00");

        // Variable to store the user's drink size input
        String size;

        // Flag to track if a valid size has been selected
        boolean availableSize = false;

        // Loop until the user inputs a valid size (S, M, or L)
        do {
            // Prompt user for drink size and convert to uppercase for consistency
            size = ConsoleHelper.promptForString("Choose drink size (S/M/L): ").toUpperCase();

            // Check if the input matches one of the allowed sizes
            if (size.equals("S") || size.equals("M") || size.equals("L")) {
                availableSize = true; // Valid input, exit loop
            } else {
                System.out.println("Invalid size. Please enter S, M, or L."); // Invalid input, retry
            }
        } while (!availableSize);

        // Display available drink flavors with numbered options for selection
        System.out.println("\nAvailable Drink Flavors:");
        String[] flavors = DrinkFlavor.FLAVORS; // Retrieve array of drink flavors
        for (int i = 0; i < flavors.length; i++) {
            System.out.printf("%d - %s%n", i + 1, flavors[i]); // Print index + 1 and flavor name
        }

        // Variable to store user's numeric flavor choice
        int choice = 0;

        // Flag to track if a valid flavor number has been selected
        boolean availableChoice = false;

        // Loop until the user enters a valid flavor number
        do {
            choice = ConsoleHelper.promptForInt("Choose a drink flavor by number: "); // Ask for numeric input

            // Check if the user's numeric choice corresponds to a valid drink flavor
            // DrinkFlavor.isValid(choice) returns true if the number is within the bounds of the flavors array
            if (DrinkFlavor.isValid(choice)) {
                availableChoice = true; // Valid number, exit loop
            } else {
                System.out.println("Invalid choice. Please enter a number from the list."); // Invalid, retry
            }
        } while (!availableChoice);

        // Map the numeric choice to the actual flavor string
        String selectedFlavor = flavors[choice - 1];

        // Notify user of the selected flavor
        System.out.println("You selected: " + selectedFlavor);
        System.out.println("Your drink flavor has been saved!");

        // Return a new Drink object with the chosen size and flavor
        return new Drink(size, selectedFlavor);
    }


//----------------- Method to create chips ------------------//
    private static Product addChips() {
            System.out.println("\n--- Add Chips ---");

        System.out.println("Available Chip Flavors (Price: $1.50 each):");

        // Get the array of available chip flavors from the ChipsFlavors class
        String[] flavors = ChipsFlavors.Flavors;

        // Display each flavor with a corresponding number for user selection
        for (int i = 0; i < flavors.length; i++) {
            System.out.printf("%d - %s%n", i + 1, flavors[i]); // Print number and flavor name
        }

        // Variable to store the user's numeric choice
        int choice = 0;

        // Flag to track whether a valid choice has been made
        boolean availableChoice = false;

        // Loop until the user enters a valid number corresponding to a flavor
        do {
            // Prompt the user to select a chip flavor by its number
            choice = ConsoleHelper.promptForInt("Choose a chip flavor by number: ");

            // Check if the entered number is valid using the ChipsFlavors helper method
            if (ChipsFlavors.isValid(choice)) {
                availableChoice = true; // Valid choice, exit loop
            } else {
                System.out.println("Invalid choice. Please enter a number from the list."); // Invalid input, retry
            }
        } while (!availableChoice);

        // Map the numeric choice to the actual flavor string
        String selectedFlavor = flavors[choice - 1];

        // Notify the user which flavor was selected
        System.out.println("You selected: " + selectedFlavor);
        System.out.println("Your chip flavor has been saved!");

        // Return a new Chips object with the selected flavor
        return new Chips(selectedFlavor);

    }


//----------------- Method to create Signature Sandwich ------------------//
    private static Product addSignatureSandwich() {
            System.out.println("\n--- Signature Sandwich ---");

            int command;
            while (true) {
                System.out.println("Choose a signature sandwich");
                System.out.println("1) BLT");
                System.out.println("2) Philly Cheese Steak");

                command = ConsoleHelper.promptForInt("Please entre your choice (1 or 2)");
                // Loop continues until the user enters a valid signature sandwich choice (1 or 2)
                if (command == 1 || command == 2)
                    break;// Exit the loop if the input is valid
                System.out.println("Invalid choice! Please select 1 or 2.");
            }
            // Declare a Sandwich variable to hold the selected signature sandwich
            Sandwich s;
            // Assign the appropriate signature sandwich based on the user's choice
            if (command == 1) s = SignatureSandwich.BLT();// If 1, create a BLT sandwich
            else s = SignatureSandwich.PhillyCheeseSteak();

            System.out.println(" Signature sandwich has been selected!");

            // Let the user customize toppings
            customizeToppings(s); // reuse your topping loop
            return s;
        }


//----------------- Method to create customizeToppings ------------------//
    private static void customizeToppings(Sandwich s) {
            while (true) {
                System.out.println("\nToppings customization menu:");
                System.out.println("1) Add topping");
                System.out.println("2) Remove topping");
                System.out.println("3) Done");

                int action = ConsoleHelper.promptForInt("Select action #: ");

                if (action == 3) {
                    System.out.println("Finished customizing toppings.");
                    break;
                }

                if (action == 2) {
                    // Remove topping
                    ArrayList<ToppingItem> toppings = s.getToppings(); // Add getter in Sandwich
                    if (toppings.isEmpty()) {
                        System.out.println("No toppings to remove!");
                        continue;
                    }

                    System.out.println("Current toppings:");
                    for (int i = 0; i < toppings.size(); i++) {
                        ToppingItem t = toppings.get(i);
                        System.out.println((i + 1) + ") " + t.getName() + (t.isExtra() ? " (extra)" : ""));
                    }

                    int removeIndex;
                    while (true) {
                        removeIndex = ConsoleHelper.promptForInt("Select topping # to remove: ");
                        if (removeIndex >= 1 && removeIndex <= toppings.size()) break;
                        System.out.println("Invalid number! Try again.");
                    }

                    ToppingItem removed = toppings.remove(removeIndex - 1);
                    System.out.println(" Removed " + removed.getName() + (removed.isExtra() ? " (extra)" : ""));
                    continue;
                }

                if (action == 1) {
                    // Add topping
                    System.out.println("\nChoose topping type to add:");
                    System.out.println("1) MEAT");
                    System.out.println("2) CHEESE");
                    System.out.println("3) REGULAR");
                    System.out.println("4) SAUCE");
                    System.out.println("5) SIDE");
                    System.out.println("6) Done");

                    int typeChoice = ConsoleHelper.promptForInt("Select type #:");
                    if (typeChoice == 6) continue;

                    String type;
                    String[] choices;
                    switch (typeChoice) {
                        case 1 -> {
                            type = "MEAT";
                            choices = Topping.MEATS;
                        }
                        case 2 -> {
                            type = "CHEESE";
                            choices = Topping.CHEESES;
                        }
                        case 3 -> {
                            type = "REGULAR";
                            choices = Topping.REGULAR;
                        }
                        case 4 -> {
                            type = "SAUCE";
                            choices = Topping.SAUCES;
                        }
                        case 5 -> {
                            type = "SIDE";
                            choices = Topping.SIDES;
                        }
                        default -> {
                            System.out.println("Invalid choice!");
                            continue;
                        }
                    }

                    System.out.println("Available " + type + " toppings:");
                    for (int i = 0; i < choices.length; i++) {
                        System.out.println((i + 1) + ") " + choices[i]);
                    }

                    int toppingChoice;
                    while (true) {
                        toppingChoice = ConsoleHelper.promptForInt("Choose topping #: ");
                        if (toppingChoice >= 1 && toppingChoice <= choices.length) break;
                        System.out.println("Invalid topping number! Try again.");
                    }

                    boolean extra = false;
                    if (type.equals("MEAT") || type.equals("CHEESE")) {
                        while (true) {
                            String extraInput = ConsoleHelper.promptForString("Extra? (Y/N): ");
                            if (extraInput.equalsIgnoreCase("Y")) {
                                extra = true;
                                ToppingItem temp = new ToppingItem(choices[toppingChoice - 1], type, true);
                                double extraPrice = s.getExtraPrice(temp);
                                System.out.println("Added extra " + choices[toppingChoice - 1] + " (" + type + ") $" + String.format("%.2f", extraPrice));
                                break;
                            } else if (extraInput.equalsIgnoreCase("N")) {
                                System.out.println("Added " + choices[toppingChoice - 1] + " (" + type + ")");
                                break;
                            } else {
                                System.out.println("Invalid input! Please enter Y or N.");
                            }
                        }
                    } else {
                        System.out.println("Added " + choices[toppingChoice - 1] + " (" + type + ")");
                    }

                    s.addTopping(new ToppingItem(choices[toppingChoice - 1], type, extra));
                }
            }
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
            for (Product p : o.getEntry()) {
                System.out.println(p.getDescription());
            }

            //Display the total price of everything
            System.out.println("TOTAL: $" + o.getTotal());
            //Ask if user wants to confirm purchase
            if (ConsoleHelper.promptForYesNo("Would you like to confirm order? ")) {
                //Writes receipt to a file
                ReceiptFile.writeReceipt(o);
                System.out.println("Receipt saved! ");
            }
            //If declined, cancel
            else System.out.println("Order cancelled! ");
        }

}