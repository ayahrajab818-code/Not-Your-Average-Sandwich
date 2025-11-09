package com.pluralsight;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFile {
    public static void writeReceipt(Order o){
        try{
            LocalDateTime now = LocalDateTime.now(); //Get current date/time

            //File name format yyyyMMdd-HHmmss.txt
            DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

        }
    }

}
