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

            switch (command) {

                //Start new order
                case "N" -> {
                    Order o = new Order();
                    boolean running = true;

                    String orderMenu = """
                            //------------Order Menu------------//
                            ========================
                            What would you like to add?
                            S) Add Sandwich
                            D) Add Drink
                            C) Add Chips
                            O) Checkout
                            X) Cancel Order
                            =======================
                            """;

                    while (running) {
                        System.out.println(orderMenu);
                        String c = ConsoleHelper.promptForString("Enter command (1, 2, 3, 4, 0): ").toUpperCase();

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
            }
        }
    }

    private static Product addSandwich() {
    }

    private static Product addDrink() {
    }

    private static Product addChips() {
    }

    private static void checkout(Order o) {
    }
}