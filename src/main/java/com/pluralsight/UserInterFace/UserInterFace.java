package com.pluralsight.UserInterFace;

import com.pluralsight.challengeYourself.SignatureSandwich;
import com.pluralsight.models.*;
import com.pluralsight.orders.Order;
import com.pluralsight.orders.ReceiptFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class UserInterFace {
    // Public: can be accessed from any other class
    // Static: belongs to the class itself, no object needed to access it
    // Final: constant, value cannot be changed once assigned
    // String: type of variable
    // LIGHT_PURPLE: name of the constant
    //"\u001B the Escape character. It signals the terminal that a formatting command is coming.
    // [95m": The control code for light purple text.
    public static final String LIGHT_PURPLE = "\u001B[95m";  // Light purple / bright magenta
    public static final String RESET = "\u001B[0m";          // Reset to default color
        //--------- Home Menu here --------------//
        public void display() {
            // Home screen menu text
            String homeMenu =LIGHT_PURPLE + """
                       ============================
                             Welcome to our 
                          NOT-YOU-AVERAGE-SANDWICH
                      ==============================
                //------------Main Menu------------//      
                   What do you want to do?
                   1) New Order
                   0) Exit
                """ + RESET;

            while (true) { // Keep showing the home menu until the user exits
                System.out.println(homeMenu);

                // Prompt user for their main menu choice
                String command = ConsoleHelper.promptForString("Enter command (1, 0)");

                switch (command) {
                    case "1" -> { // Start a new order
                        Order o = new Order(); // Create a new order object

                        boolean running = true; // Controls the order menu loop

                        // Order menu text
                        String orderMenu = LIGHT_PURPLE +"""
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
                            """ +RESET;

                        while (running) { // Keep showing the order menu until user cancels or checks out
                            System.out.println(orderMenu);

                            // Prompt for choice in the order menu
                            String c = ConsoleHelper.promptForString("Enter command (1, 2, 3, 4, 5, 0)").toUpperCase();

                            switch (c) {
                                case "1" -> o.add(addSandwich()); // Add a sandwich
                                case "2" -> o.add(addDrink());    // Add a drink
                                case "3" -> o.add(addChips());    // Add chips
                                case "4" -> {                     // Checkout
                                    checkout(o);
                                    running = false; // Exit order menu after checkout
                                }
                                case "5" -> o.add(addSignatureSandwich()); // Add a signature sandwich
                                case "0" -> { // Cancel the order
                                    System.out.println("Order canceled. Returning to home screen...");
                                    running = false; // Stop order menu loop
                                    // Do NOT return or exit program just break out to home menu
                                }
                                default -> System.out.println("INVALID COMMAND! Please try again."); // Handle invalid input
                            }
                        }
                    }

                    case "0" -> { // Exit the whole application
                        System.out.println("Exiting application Thank you for ordering at Not-Your-Average-Sandwich");
                        return; // End the program completely
                    }

                    default -> // Invalid input at home screen
                            System.out.println("Invalid choice! Please enter 1 for New Order or 0 to Exit.");
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
        System.out.println(LIGHT_PURPLE +"\n--- Add Drink ---"+RESET); // Header for the drink section

        // ======== DRINK SIZE SELECTION (NUMBERED MENU) ========
        System.out.println("\nDrink Sizes:"); // Print the menu header
        String[] sizes = {"S", "M", "L"}; // Array of drink sizes
        double[] prices = {2.00, 2.50, 3.00}; // Corresponding prices for each size

        // Display numbered menu for easy selection
        for (int i = 0; i < sizes.length; i++) {
            System.out.println((i + 1) + ") " + sizes[i] + " - $" + String.format("%.2f", prices[i]));
            // Prints number, size, and formatted price
        }

        int sizeChoice; // Variable to store user's numeric size choice

        // Loop until user selects a valid number
        while (true) {
            sizeChoice = ConsoleHelper.promptForInt("Select drink size #"); // Ask user for number
            if (DrinkFlavor.isValid(sizeChoice)) break; // Valid selection, exit loop
            System.out.println("Invalid choice! Please select 1-" + sizes.length + "."); // Invalid input, retry
        }

        String selectedSize = sizes[sizeChoice - 1]; // Map numeric choice to size string
        double selectedPrice = prices[sizeChoice - 1]; // Get the corresponding price
        System.out.println("You selected: " + selectedSize + " - $" + String.format("%.2f", selectedPrice));
        // Display the user's selected size and price

        // ======== DRINK FLAVOR SELECTION ========
        System.out.println(LIGHT_PURPLE+"\nAvailable Drink Flavors:"+RESET); // Header for flavor menu
        String[] flavors = DrinkFlavor.FLAVORS; // Retrieve array of available drink flavors

        // Display numbered flavor menu
        for (int i = 0; i < flavors.length; i++) {
            System.out.printf("%d - %s%n", i + 1, flavors[i]);
            // Print number and flavor name
        }

        int choice;
        boolean availableChoice = false; // Flag to validate selection

        // Loop until a valid flavor number is selected
        do {
            choice = ConsoleHelper.promptForInt("Choose a drink flavor by number: "); // Prompt for flavor number
            if (DrinkFlavor.isValid(choice)) {
                availableChoice = true; // Valid choice
            } else {
                System.out.println("Invalid choice. Please enter a number from the list."); // Retry message
            }
        } while (!availableChoice);

        String selectedFlavor = flavors[choice - 1]; // Map numeric choice to flavor string
        System.out.println("You selected: " + selectedFlavor); // Show selected flavor
        System.out.println("Your drink flavor has been saved!"); // Confirmation

        // ======== RETURN DRINK OBJECT ========
        return new Drink(selectedSize, selectedFlavor);
        // Create and return a new Drink object with the selected size and flavor
    }


//----------------- Method to create chips ------------------//
    private static Product addChips() {
            System.out.println(LIGHT_PURPLE+ "\n--- Add Chips ---"+RESET);

        System.out.println("Available Chip Flavors (Price: $1.50 each):");

        // Get the array of available chip flavors from the ChipsFlavors class
        String[] flavors = ChipsFlavors.Flavors;

        // Display each flavor with a corresponding number for user selection
        for (int i = 0; i < flavors.length; i++) {
            System.out.printf("%d - %s%n", i + 1, flavors[i]); // Print number and flavor name
        }

        // Variable to store the user's numeric choice
        int choice;

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
            System.out.println(LIGHT_PURPLE +"\n--- Signature Sandwich ---"+RESET);

            int command;
            while (true) {
                System.out.println(LIGHT_PURPLE +"Choose a signature sandwich"+RESET);
                System.out.println(LIGHT_PURPLE +"1) BLT"+RESET);
                System.out.println(LIGHT_PURPLE +"2) Philly Cheese Steak"+RESET);

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
                System.out.println(LIGHT_PURPLE +"\nToppings customization menu:"+ RESET);
                System.out.println(LIGHT_PURPLE +"1) Add topping"+ RESET);
                System.out.println(LIGHT_PURPLE +"2) Remove topping"+RESET);
                System.out.println(LIGHT_PURPLE +"3) Done"+RESET);
                // Prompt user to choose an action (1 = Add, 2 = Remove, 3 = Done)
                int action = ConsoleHelper.promptForInt("Select action #: ");

                if (action == 3) {
                    System.out.println("Finished customizing toppings.");
                    break;
                    // Exit the topping customization loop if user is done
                }

                if (action == 2) {
                    // Remove topping section
                    ArrayList<ToppingItem> toppings = s.getToppings(); // Get current toppings from the sandwich
                    if (toppings.isEmpty()) {
                        System.out.println("No toppings to remove!"); // Inform if no toppings exist
                        continue; // Go back to the action menu
                    }

                    System.out.println("Current toppings:"); // List existing toppings
                    for (int i = 0; i < toppings.size(); i++) {
                        ToppingItem t = toppings.get(i);
                        System.out.println((i + 1) + ") " + t.getName() + (t.isExtra() ? " (extra)" : ""));
                        // Display topping number, name, and mark if it's extra
                    }

                    int removeIndex; // Variable for user's choice to remove
                    while (true) {
                        removeIndex = ConsoleHelper.promptForInt("Select topping # to remove: ");
                        if (removeIndex >= 1 && removeIndex <= toppings.size()) break;
                        // Validate input within list range
                        System.out.println("Invalid number! Try again.");
                    }

                    ToppingItem removed = toppings.remove(removeIndex - 1);
                    // Remove topping from list
                    System.out.println(" Removed " + removed.getName() + (removed.isExtra() ? " (extra)" : ""));
                    // Confirmation message
                    continue; // Return to the action menu
                }

                if (action == 1) {
                    // Add topping section
                    System.out.println("\nChoose topping type to add:");
                    System.out.println("1) MEAT");
                    System.out.println("2) CHEESE");
                    System.out.println("3) REGULAR");
                    System.out.println("4) SAUCE");
                    System.out.println("5) SIDE");
                    System.out.println("6) Done");

                    int typeChoice = ConsoleHelper.promptForInt("Select type #:");
                    // Prompt user to select a topping type
                    if (typeChoice == 6) continue;
                    // If Done is selected, go back to the main action menu

                    String type; // Variable to store topping type string
                    String[] choices; // Array to store toppings of selected type

                    // Map numeric choice to topping type array
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
                            continue; // Invalid selection, back to topping type menu
                        }
                    }

                    // Display available toppings of selected type
                    System.out.println("Available " + type + " toppings:");
                    for (int i = 0; i < choices.length; i++) {
                        System.out.println((i + 1) + ") " + choices[i]);
                    }

                    int toppingChoice; // Variable to store user's topping selection
                    while (true) {
                        toppingChoice = ConsoleHelper.promptForInt("Choose topping #: ");
                        if (toppingChoice >= 1 && toppingChoice <= choices.length) break;
                        // Validate input within the topping array bounds
                        System.out.println("Invalid topping number! Try again.");
                    }

                    boolean extra = false; // Flag to track if this topping is extra
                    if (type.equals("MEAT") || type.equals("CHEESE")) {
                        // Only MEAT or CHEESE can be extra
                        while (true) {
                            String extraInput = ConsoleHelper.promptForString("Extra? (Y/N): ");
                            // Ask user if they want extra
                            if (extraInput.equalsIgnoreCase("Y")) {
                                extra = true; // Mark as extra
                                ToppingItem temp = new ToppingItem(choices[toppingChoice - 1], type, true);
                                // Create a temp topping to calculate extra price
                                double extraPrice = s.getExtraPrice(temp);
                                System.out.println("Added extra " + choices[toppingChoice - 1] + " (" + type + ") $" + String.format("%.2f", extraPrice));
                                // Show price for extra topping
                                break;
                            } else if (extraInput.equalsIgnoreCase("N")) {
                                System.out.println("Added " + choices[toppingChoice - 1] + " (" + type + ")");
                                // Added normally
                                break;
                            } else {
                                System.out.println("Invalid input! Please enter Y or N.");
                                // Retry for valid input
                            }
                        }
                    } else {
                        System.out.println("Added " + choices[toppingChoice - 1] + " (" + type + ")");
                        // Non-MEAT/CHEESE toppings added without extra
                    }

                    // Add the topping to the sandwich
                    s.addTopping(new ToppingItem(choices[toppingChoice - 1], type, extra));
                    // Stores topping with name, type, and extra flag
                }
            }
        }
        

//------------------ Checkout method to show summary and save receipt --------------------//
    private static void checkout(Order o) {
            //Get current date/time
            LocalDateTime now = LocalDateTime.now();
            //Format how the date/time will look on screen
            DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");

            System.out.println(LIGHT_PURPLE +"\n=== ORDER SUMMARY ==="+ RESET);
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