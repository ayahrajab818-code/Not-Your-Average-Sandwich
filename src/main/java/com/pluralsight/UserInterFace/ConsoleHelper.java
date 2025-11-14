package com.pluralsight.UserInterFace;

import com.pluralsight.models.*;

import java.util.Scanner;


public class ConsoleHelper {
    private static Scanner scanner = new Scanner(System.in);

    //This method asks the user to type a whole number (integer) and returns it
    public static int promptForInt(String prompt) {
        int result;

        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim(); // read inside the loop

            try {
                result = Integer.parseInt(input);
                return result; // valid integer entered
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }



    //I used a string here because it's a plain text
    public static String promptForString(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim(); // Read input and remove extra spaces

            if (!input.isEmpty()) {
                return input;  // Return valid text
            } else {
                System.out.println("Invalid input! Please enter text.");
            }
        }
    }


    /*
     * Prompt the user with a yes/no question
     * Returns true if user enters Y/y
     */
    public static boolean promptForYesNo(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " (Y/N): ");
                String input = scanner.nextLine().trim();
                return input.equalsIgnoreCase("Y");
            } catch (Exception ex) {
                System.out.println("Invalid Entry, please enter (y/n) ");
            }
        }
    }

    public static Sandwich buildSandwich() {

        // ======== BREAD SELECTION ========
        int breadChoice; // Variable to store the user's bread selection
        while (true) { // Loop until the user enters a valid bread choice
            System.out.println("Please choose a bread type:"); // Prompt user to choose bread
            for (int i = 0; i < BreadType.TYPES.length; i++) { // Loop through all bread types
                System.out.println((i + 1) + ") " + BreadType.TYPES[i]); // Display bread options numbered
            }
            breadChoice = ConsoleHelper.promptForInt("Select your bread #"); // Ask user to enter a bread number
            if (BreadType.isValid(breadChoice)) break; // Exit loop if the selected bread is valid
            System.out.println("Invalid choice! Please select an option above."); // Display error for invalid input
        }

        String bread = BreadType.TYPES[breadChoice - 1]; // Store the selected bread type

        // ======== SIZE SELECTION ========
        String[] sizes = SandwichSize.SIZES; // Get list of available sandwich sizes
        double[] prices = {5.50, 7.00, 8.50}; // Prices corresponding to each sandwich size

        System.out.println("\nSandwich Sizes:"); // Display sandwich size header
        for (int i = 0; i < sizes.length; i++) { // Loop through sizes
            System.out.println((i + 1) + ") " + sizes[i] + "\" - $" + String.format("%.2f", prices[i])); // Display each size with price
        }

        int sizeChoice; // Variable to hold user's chosen size
        while (true) { // Repeat until user selects valid size
            sizeChoice = ConsoleHelper.promptForInt("Select size #"); // Ask user for size number
            if (SandwichSize.isValid(sizeChoice)) break; // Exit loop if valid choice
            System.out.println("Invalid choice! Please select 1-" + sizes.length + "."); // Show error message
        }

        String size = sizes[sizeChoice - 1]; // Store the chosen size (e.g. 4, 8, or 12 inches)
        double sizePrice = prices[sizeChoice - 1]; // Get price of chosen size
        System.out.println("You selected: " + size + "\" - $" + String.format("%.2f", sizePrice)); // Confirm size and price to user

        // ======== TOASTED OPTION ========
        boolean toasted; // Boolean to store whether sandwich should be toasted
        while (true) { // Repeat until valid Y/N input
            String t = ConsoleHelper.promptForString("Toasted? (Y/N)"); // Ask if toasted
            if (t.equalsIgnoreCase("Y")) { // If user enters Y
                toasted = true; // Set toasted to true
                break; // Exit loop
            } else if (t.equalsIgnoreCase("N")) { // If user enters N
                toasted = false; // Set toasted to false
                break; // Exit loop
            }
            System.out.println("Invalid input! Please enter Y or N."); // Handle invalid input
        }

        Sandwich s = new Sandwich(bread, size, toasted); // Create new Sandwich object using user's selections

        // ======== SHOW EXTRA PRICING ========
        System.out.println(LIGHT_PURPLE +"\n=== Extra Pricing ==="+ RESET); // Display header for extra pricing section
        System.out.println(LIGHT_PURPLE +"Extra Meat:"); // Label for extra meat
        System.out.println(LIGHT_PURPLE +"4\" = $0.50   | 8\" = $1.00   | 12\" = $1.50"+ RESET); // Show extra meat pricing
        System.out.println(LIGHT_PURPLE +"Extra Cheese:"); // Label for extra cheese
        System.out.println(LIGHT_PURPLE + "4\" = $0.30   | 8\" = $0.60   | 12\" = $0.90\n"+ RESET); // Show extra cheese pricing

        // ======== TOPPINGS SELECTION ========
        while (true) { // Loop for topping category selection
            System.out.println("\nChoose topping type:"); // Display topping type menu
            System.out.println("1) MEAT");
            System.out.println("2) CHEESE");
            System.out.println("3) REGULAR");
            System.out.println("4) SAUCE");
            System.out.println("5) SIDE");
            System.out.println("6) DONE"); // Option to finish toppings

            int typeChoice = ConsoleHelper.promptForInt("Select type #"); // Ask for topping type number
            if (typeChoice == 6) { // If user selects DONE
                System.out.println("Finished adding toppings."); // Show completion message
                break; // Exit toppings loop
            }

            String type; // To hold current topping type
            String[] choices; // To hold available toppings for chosen type

            switch (typeChoice) { // Determine topping category based on user choice
                case 1 -> { type = "MEAT"; choices = Topping.MEATS; } // Meat toppings
                case 2 -> { type = "CHEESE"; choices = Topping.CHEESES; } // Cheese toppings
                case 3 -> { type = "REGULAR"; choices = Topping.REGULAR; } // Regular toppings (e.g. lettuce)
                case 4 -> { type = "SAUCE"; choices = Topping.SAUCES; } // Sauce toppings
                case 5 -> { type = "SIDE"; choices = Topping.SIDES; } // Side toppings
                default -> { // Handle invalid type number
                    System.out.println("Invalid choice! Please select 1–6.");
                    continue; // Skip rest and go back to top of loop
                }
            }

            boolean doneWithThisType = false; // Flag to track when user finishes one topping type

            while (!doneWithThisType) { // Loop for selecting toppings within the same type
                System.out.println("\nAvailable " + type + " toppings:"); // Display available toppings of this type
                for (int i = 0; i < choices.length; i++) { // Loop through toppings
                    System.out.println((i + 1) + ") " + choices[i]); // Display each topping option
                }

                int toppingChoice = ConsoleHelper.promptForInt("Choose topping #"); // Ask for topping number
                if (!Topping.isValidChoice(toppingChoice, choices)) { // Validate topping input
                    System.out.println("Invalid topping number! Try again."); // Show error message
                    continue; // Retry same type
                }

                String selectedTopping = choices[toppingChoice - 1]; // Store selected topping
                boolean extra = false; // Track whether user wants extra of this topping

                if (type.equals("MEAT") || type.equals("CHEESE")) { // Handle extra option for meats/cheeses
                    while (true) { // Ask repeatedly until valid input
                        String extraInput = ConsoleHelper.promptForString("Extra? (Y/N)"); // Ask if extra
                        if (extraInput.equalsIgnoreCase("Y")) { // If extra
                            extra = true; // Mark as extra
                            ToppingItem temp = new ToppingItem(selectedTopping, type, true); // Create temp topping for price
                            double extraPrice = s.getExtraPrice(temp); // Get price for extra portion
                            System.out.println("Added extra " + selectedTopping + " (" + type + ") $" +
                                    String.format("%.2f", extraPrice)); // Show confirmation with extra cost
                            break; // Exit loop
                        } else if (extraInput.equalsIgnoreCase("N")) { // If not extra
                            System.out.println("Added " + selectedTopping + " (" + type + ")"); // Confirm added
                            break; // Exit loop
                        } else {
                            System.out.println("Invalid input! Please enter Y or N."); // Error for invalid input
                        }
                    }
                    s.addTopping(new ToppingItem(selectedTopping, type, extra)); // Add topping to sandwich object

                } else { // For regular, sauce, or side toppings
                    System.out.println("\nWould you like to add " + selectedTopping + "?"); // Confirm add
                    System.out.println("1) Yes");
                    System.out.println("2) No");

                    int addChoice = ConsoleHelper.promptForInt("Enter your choice (1-2): "); // Ask to add or skip

                    if (addChoice == 1) { // If yes
                        s.addTopping(new ToppingItem(selectedTopping, type, false)); // Add topping without extra charge
                        System.out.println(selectedTopping + " added successfully! (Free topping)"); // Confirmation message

                        System.out.println("\nWould you like to add another topping?"); // Ask to continue same type
                        System.out.println("1) Yes");
                        System.out.println("2) No");

                        int another = ConsoleHelper.promptForInt("Enter your choice (1-2): "); // Ask for next action

                        if (another == 1) { // If user wants to add another topping
                            break; // Go back to same topping menu
                        } else if (another == 2) { // If done with this type
                            System.out.println("Exiting to main topping menu..."); // Inform user
                            doneWithThisType = true; // End this type loop
                        } else {
                            System.out.println("Invalid choice! Please enter 1 or 2."); // Invalid input message
                        }

                    } else if (addChoice == 2) { // If user skips topping
                        System.out.println("Skipping this topping. Choose another topping."); // Notify skip
                        continue; // Go back to topping menu
                    } else {
                        System.out.println("Invalid choice! Please enter 1 or 2."); // Handle invalid input
                    }
                }

                if (type.equals("MEAT") || type.equals("CHEESE")) { // After adding meat/cheese
                    String pickAnother = ConsoleHelper.promptForString("Pick another " + type + "? (Y/N)"); // Ask to add more
                    if (pickAnother.equalsIgnoreCase("N")) doneWithThisType = true; // Exit if not adding more
                }
            }
        }

        // ======== FINAL SUMMARY ========
        double finalPrice = s.getPrice(); // Calculate total price for sandwich
        System.out.println(LIGHT_PURPLE +"\n========================================"); // Formatting line
        System.out.println(LIGHT_PURPLE +"     SANDWICH PLACED SUCCESSFULLY!"); // Success message
        System.out.println(LIGHT_PURPLE +"========================================"); // Formatting line
        System.out.println(s.getDescription()); // Print sandwich details (bread, size, toppings)
        System.out.println("\nTotal Sandwich Price: $" + String.format("%.2f", finalPrice)); // Display final cost
        System.out.println("========================================\n"); // End of order summary

        return s; // Return the completed sandwich object

    }
    // ANSI Color Codes
    public static final String LIGHT_PURPLE = "\u001B[95m";  // Light purple / bright magenta
    public static final String RESET = "\u001B[0m";


}
