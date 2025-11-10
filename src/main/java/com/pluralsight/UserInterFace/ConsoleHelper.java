package com.pluralsight.UserInterFace;

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
}
