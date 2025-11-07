package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
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


    public static double promptForDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine();
                return Double.parseDouble(input);  // Convert text to double
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input! Please enter a number");
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

    public static LocalDate promptForDate(String prompt){

        while(true){
            try{
                System.out.print(prompt + ": ");
                String dateAsString = scanner.nextLine();
                return LocalDate.parse(dateAsString);
            }
            catch(Exception ex){
                System.out.println("Invalid Entry, please enter a valid date (YYYY-MM-DD)");
            }
        }
    }
    //I used localTime here because is a spacial java class the understanding times
    public static LocalTime promptForTime(String prompt){
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String timeAsString = scanner.nextLine();
                return LocalTime.parse(timeAsString);
            } catch (Exception ex) {
                System.out.println("Invalid Entry, please enter a valid time (HH:MM:SS)");
            }
        }
    }
}
