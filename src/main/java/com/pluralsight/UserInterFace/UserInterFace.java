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
                    case "X" -> {
                        System.out.println("Exiting application..."); // Notify user
                        return; // Stop program
                    }
                    //Any other input
                    default ->
                            System.out.println("Invalid choice! Please enter N for New Order or X to Exit."); //Invalid input
                }
            }
        }


//---------------------- Method to create a sandwich with user input -------------------//
private static Product addSandwich() {

    System.out.println("\n--- Add Sandwich ---");

       // ======== BREAD CHOICE ========
            int breadChoice;
            while (true) {
                System.out.println("Please choose a bread type:");
                for (int i = 0; i < BreadType.TYPES.length; i++) {
                    System.out.println((i + 1) + ") " + BreadType.TYPES[i]);
                }
                breadChoice = ConsoleHelper.promptForInt("Select your bread #");

                if (BreadType.isValid(breadChoice))
                    break;
                System.out.println("Invalid choice! Please select an option above.");
            }
            String bread = BreadType.TYPES[breadChoice - 1];
            // ======== SIZE ========
            System.out.println("\nSandwich Sizes:");
            System.out.println("4\"  - $5.50");
            System.out.println("8\"  - $7.00");
            System.out.println("12\" - $8.50");

            String size;
            while (true) {
                size = ConsoleHelper.promptForString("Choose your size (4/8/12 inches)");
                if (size.equals("4") || size.equals("8") || size.equals("12")) break;
                System.out.println("Invalid size! Please enter only 4, 8, or 12.");
            }
            // ======== TOASTED ========
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
            Sandwich s = new Sandwich(bread, size, toasted);

            // ======== PREMIUM PRICING DISPLAY ========
            System.out.println("\n=== Extra Pricing ===");
            System.out.println("Extra Meat:");
            System.out.println("4\" = $0.50   | 8\" = $1.00   | 12\" = $1.50");
            System.out.println("Extra Cheese:");
            System.out.println("4\" = $0.30   | 8\" = $0.60   | 12\" = $0.90\n");

            // ======== TOPPINGS ========
            while (true) {
                System.out.println("\nChoose topping type:");
                System.out.println("1) MEAT");
                System.out.println("2) CHEESE");
                System.out.println("3) REGULAR");
                System.out.println("4) SAUCE");
                System.out.println("5) SIDE");
                System.out.println("6) DONE");

                int typeChoice = ConsoleHelper.promptForInt("Select type #");
                if (typeChoice == 6) {
                    System.out.println("Finished adding toppings.");
                    break;// Done adding toppings
                }
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
                        System.out.println("Invalid choice! Please select 1–6.");
                        continue;
                    }
                }
                //Show available toppings for selected type
                System.out.println("\nAvailable " + type + " toppings:");
                for (int i = 0; i < choices.length; i++) {
                    System.out.println((i + 1) + ") " + choices[i]);
                }

                int choice;
                while (true) {
                    choice = ConsoleHelper.promptForInt("Choose topping #");
                    if (choice >= 1 && choice <= choices.length) break;
                    System.out.println("Invalid topping number! Try again.");
                }

                boolean extra = false;
                if (type.equals("MEAT") || type.equals("CHEESE")) {
                    while (true) {
                        String e = ConsoleHelper.promptForString("Extra? (Y/N)");
                        if (e.equalsIgnoreCase("Y")) {
                            extra = true;
                            ToppingItem tempTopping = new ToppingItem(choices[choice - 1], type, true);
                            double extraCost = s.getExtraPrice(tempTopping);

                            System.out.println("Added extra " + choices[choice - 1] + " (" + type + ") $"
                                    + String.format("%.2f", extraCost));
                            break;

                        } else if (e.equalsIgnoreCase("N")) {
                            extra = false;
                            System.out.println("Added " + choices[choice - 1] + " (" + type + ")");
                            break;
                        }
                        System.out.println("Invalid input! Please enter Y or N.");
                    }
                }
                s.addTopping(new ToppingItem(choices[choice - 1], type, extra));
            }
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

            // Validate drink size
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

            // Display flavors with numbers
            System.out.println("\nAvailable Drink Flavors:");
            String[] flavors = DrinkFlavor.FLAVORS;
            for (int i = 0; i < flavors.length; i++) {
                System.out.printf("%d - %s%n", i + 1, flavors[i]);
            }

            int choice = 0;
            boolean availableChoice = false;

            // Validate number input for flavor
            do {
                choice = ConsoleHelper.promptForInt("Choose a drink flavor by number: ");
                if (choice >= 1 && choice <= flavors.length) {
                    availableChoice = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number from the list.");
                }
            } while (!availableChoice);

            String selectedFlavor = flavors[choice - 1]; // map number to flavor
            System.out.println("You selected: " + selectedFlavor);
            System.out.println("Your drink flavor has been saved!");
            return new Drink(size, selectedFlavor);
        }


        //----------------- Method to create chips ------------------//
        private static Product addChips() {
            System.out.println("\n--- Add Chips ---");

            // Display flavors with numbers
            System.out.println("Available Chip Flavors (Price: $1.50 each):");
            String[] flavors = ChipsFlavors.Flavors;
            for (int i = 0; i < flavors.length; i++) {
                System.out.printf("%d - %s%n", i + 1, flavors[i]);
            }

            int choice = 0;
            boolean availableChoice = false;

            // Loop until valid number is entered
            do {
                choice = ConsoleHelper.promptForInt("Choose a chip flavor by number: ");

                if (choice >= 1 && choice <= flavors.length) {
                    availableChoice = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number from the list.");
                }
            } while (!availableChoice);

            String selectedFlavor = flavors[choice - 1]; // map number to flavor
            System.out.println("You selected: " + selectedFlavor);
            System.out.println("Your chip flavor has been saved!");
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
                if (command == 1 || command == 2) break;
                System.out.println("Invalid choice! Please select 1 or 2.");
            }
            Sandwich s;
            if (command == 1) s = SignatureSandwich.BLT();
            else s = SignatureSandwich.PhillyCheeseSteak();

            System.out.println(" Signature sandwich has been selected!");

            //let the user customize toppings
            customizeToppings(s); // reuse your topping loop
            return s;
        }


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