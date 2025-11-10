package com.pluralsight.orders;


import com.pluralsight.models.Product;
import com.pluralsight.models.Sandwich;
import com.pluralsight.models.ToppingItem;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFile {
    public static void writeReceipt(Order o) {
        try {
            LocalDateTime now = LocalDateTime.now();

            //File name format yyyyMMdd-HHmmss.txt
            DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String fileName = "receipts/" + now.format(fileFormat) + ".txt";

            FileWriter w = new FileWriter(fileName);

            // HEADER
            w.write("===== Not-Your-Average-Sandwich =====\n");
            w.write("Date: " + now.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")) + "\n");
            w.write("================================\n\n");

            // SANDWICH SECTION
            w.write("---- Sandwiches ----\n");

            for (int i = 0; i < o.getEntry().size(); i++) {
                Sandwich s = o.getEntry().ge(i);

                w.write((i + 1) + ". " + s.getSize() + "\" " + s.getBread() +
                        " (Toasted: " + (s.isToasted() ? "Yes" : "No") + ")\n");
                w.write("   Toppings: ");

                // list toppings
                for (int t = 0; t < s.getToppings().size(); t++) {
                    ToppingItem top = s.getToppings().get(t);

                    w.write(top.getName());
                    if (top.isExtra()) {
                        double extraPrice = s.getExtraPrice(top);
                        w.write(" (extra) +$" + String.format("%.2f", extraPrice));
                    }

                    if (t < s.getToppings().size() - 1) {
                        w.write(", ");
                    }
                }

                w.write("\n");
                w.write(String.format("Price: $%.2f%n", s.getPrice()));
            }

            w.write("\n================================\n");
            w.write(String.format("Total: $%.2f%n", o.getTotal()));
            w.write("================================\n\n");
            w.write("Thank you for choosing DELI-SHOP!\n");

            w.close();

            System.out.println("Receipt saved to file successfully: " + fileName);

        } catch (Exception e) {
            System.out.println("Receipt error: " + e.getMessage());
        }
    }
}


