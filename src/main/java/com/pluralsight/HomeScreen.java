package com.pluralsight;


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

            switch (command){
                
            }
        }
    }
}