package com.pluralsight.orders;


import com.pluralsight.models.Product;
import com.pluralsight.models.Sandwich;
import com.pluralsight.models.ToppingItem;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ReceiptFile {
    // Added a paymentMethod parameter to record payment type
    public static void writeReceipt(Order o) {
        try {
            LocalDateTime now = LocalDateTime.now();
            // File name format: yyyyMMdd-HHmmss.txt
            DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String fileName = "receipts/" + now.format(fileFormat) + ".txt";

            FileWriter w = new FileWriter(fileName);

            // HEADER
            w.write("===== Not-Your-Average-Sandwich =====\n");
            w.write("Date: " + now.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")) + "\n");
            w.write("================================\n\n");

            // SANDWICH SECTION
            int sandwichCount = 1;
            w.write("---- Sandwiches ----\n");

            for (Product p : o.getEntry()) {
                if (p instanceof Sandwich s) { // Java 16+ pattern matching for instanceof
                    w.write(sandwichCount + ". " + s.getSize() + "\" " + s.getBread() +
                            " (Toasted: " + (s.isToasted() ? "Yes" : "No") + ")\n");

                    // Build toppings string with extra pricing
                    StringBuilder toppingLine = new StringBuilder("   Toppings: ");
                    for (ToppingItem t : s.getToppings()) {
                        toppingLine.append(t.getName());
                        if (t.isExtra()) toppingLine.append(" (extra) +$").append(String.format("%.2f", s.getExtraPrice(t)));
                        toppingLine.append(", ");
                    }
                    if (toppingLine.length() > 0) toppingLine.setLength(toppingLine.length() - 2); // remove last comma
                    w.write(toppingLine.toString() + "\n");

                    // Write price
                    w.write("   Price: $" + String.format("%.2f", s.getPrice()) + "\n");
                    sandwichCount++;
                }
            }

            // TOTAL
            w.write("\n================================\n");
            w.write(String.format("Total: $%.2f%n", o.getTotal()));
            w.write("================================\n\n");
            w.write("Thank you for choosing Not-Your-Average-Sandwich!\n");

            w.close();

            System.out.println("Receipt saved to file successfully: " + fileName);

        } catch (Exception e) {
            System.out.println("Receipt error: " + e.getMessage());
        }
    }
}

