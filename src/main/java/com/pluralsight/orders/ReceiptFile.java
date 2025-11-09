package com.pluralsight.orders;


import com.pluralsight.models.Product;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFile {
    public static void writeReceipt(Order o){
        try {
            LocalDateTime now = LocalDateTime.now(); //Get current date/time

            //File name format yyyyMMdd-HHmmss.txt
            DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String fileName = "receipts/" + now.format(fileFormat) + ".txt";

            FileWriter w = new FileWriter(fileName); //Create file writer

            //Format date/time to 12-hour with AM/PM
            DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
            w.write("\n=== Not-Your-Average-Sandwich Receipt ===");
            w.write("Order Date/Time: " + now.format(displayFormat) + "\n\n");

            // Write each product
            for (Product p : o.getEntry()) {
                w.write(p.getDescription() + " - $" + p.getPrice() + "\n");
            }
            w.write("--------------------------\n");
            w.write("\nTotal = $" + o.getTotal() + "\n");
            w.write("--------------------------\n");

            w.close(); //Close file

            System.out.println("Receipt saved to file successfully : " + fileName);

        } catch (Exception e) {
            System.out.println("Receipt error: " + e.getMessage());
        }
     }
   }


