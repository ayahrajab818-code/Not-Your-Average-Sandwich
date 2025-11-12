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
        while (true) {
            System.out.println("Please choose a bread type:");
            for (int i = 0; i < BreadType.TYPES.length; i++) {
                System.out.println((i + 1) + ") " + BreadType.TYPES[i]);
            }

            breadChoice = ConsoleHelper.promptForInt("Select your bread #");
            if (BreadType.isValid(breadChoice)) break;
            System.out.println("Invalid choice! Please select an option above.");
        }

        String bread = BreadType.TYPES[breadChoice - 1];

        // ======== SIZE SELECTION ========
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

        // ======== TOASTED OPTION ========
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

        Sandwich s = new Sandwich(bread, size, toasted); // Create sandwich object

        // ======== SHOW EXTRA PRICING ========
        System.out.println("\n=== Extra Pricing ===");
        System.out.println("Extra Meat:");
        System.out.println("4\" = $0.50   | 8\" = $1.00   | 12\" = $1.50");
        System.out.println("Extra Cheese:");
        System.out.println("4\" = $0.30   | 8\" = $0.60   | 12\" = $0.90\n");

        // ======== TOPPINGS SELECTION ========
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
                break;
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

            // Loop for toppings within same type
            boolean doneWithThisType = false;

            while (!doneWithThisType) {
                System.out.println("\nAvailable " + type + " toppings:");
                for (int i = 0; i < choices.length; i++) {
                    System.out.println((i + 1) + ") " + choices[i]);
                }

                int toppingChoice = ConsoleHelper.promptForInt("Choose topping #");
                if (!Topping.isValidChoice(toppingChoice, choices)) {
                    System.out.println("Invalid topping number! Try again.");
                    continue;
                }

                String selectedTopping = choices[toppingChoice - 1];
                boolean extra = false;

                if (type.equals("MEAT") || type.equals("CHEESE")) {
                    // Handle extra option for meat/cheese
                    while (true) {
                        String extraInput = ConsoleHelper.promptForString("Extra? (Y/N)");
                        if (extraInput.equalsIgnoreCase("Y")) {
                            extra = true;
                            ToppingItem temp = new ToppingItem(selectedTopping, type, true);
                            double extraPrice = s.getExtraPrice(temp);
                            System.out.println("Added extra " + selectedTopping + " (" + type + ") $" +
                                    String.format("%.2f", extraPrice));
                            break;
                        } else if (extraInput.equalsIgnoreCase("N")) {
                            System.out.println("Added " + selectedTopping + " (" + type + ")");
                            break;
                        } else {
                            System.out.println("Invalid input! Please enter Y or N.");
                        }
                    }
                    s.addTopping(new ToppingItem(selectedTopping, type, extra));

                } else {
                    // Handle regular, sauce, or side toppings
                    System.out.println("\nWould you like to add " + selectedTopping + "?");
                    System.out.println("1) Yes");
                    System.out.println("2) No");

                    int addChoice = ConsoleHelper.promptForInt("Enter your choice (1-2): ");

                    if (addChoice == 1) {
                        s.addTopping(new ToppingItem(selectedTopping, type, false));
                        System.out.println(selectedTopping + " added successfully! (Free topping)");

                        // Ask if they want to add another topping
                        System.out.println("\nWould you like to add another topping?");
                        System.out.println("1) Yes");
                        System.out.println("2) No");

                        int another = ConsoleHelper.promptForInt("Enter your choice (1-2): ");

                        if (another == 1) {
                            // Go back immediately to choose another topping
                            break; // exits this inner prompt and shows topping selection again
                        } else if (another == 2) {
                            System.out.println("Exiting to main topping menu...");
                            doneWithThisType = true; // exit current topping type loop
                        } else {
                            System.out.println("Invalid choice! Please enter 1 or 2.");
                        }

                    } else if (addChoice == 2) {
                        System.out.println("Skipping this topping. Choose another topping.");
                        continue; // go back to topping selection
                    } else {
                        System.out.println("Invalid choice! Please enter 1 or 2.");
                    }

                }

                // Ask to continue or exit this topping type
                if (type.equals("MEAT") || type.equals("CHEESE")) {
                    String pickAnother = ConsoleHelper.promptForString("Pick another " + type + "? (Y/N)");
                    if (pickAnother.equalsIgnoreCase("N")) doneWithThisType = true;
                }
            }
        }

        // ======== FINAL SUMMARY ========
        double finalPrice = s.getPrice();
        System.out.println("\n========================================");
        System.out.println("     SANDWICH PLACED SUCCESSFULLY!");
        System.out.println("========================================");
        System.out.println(s.getDescription());
        System.out.println("\nTotal Sandwich Price: $" + String.format("%.2f", finalPrice));
        System.out.println("========================================\n");

        return s;
    }


}
