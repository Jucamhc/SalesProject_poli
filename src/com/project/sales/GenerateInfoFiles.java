
//package com.project.sales;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {
    private static final String[] FIRST_NAMES = {"John", "Alice", "Mark", "Sophia", "Edward"};
    private static final String[] LAST_NAMES = {"Doe", "Smith", "Johnson", "Williams", "Brown"};
    
    public static void createSalesMenFile(int randomSalesCount, String name, long id) {
        Random rand = new Random();
        try (FileWriter writer = new FileWriter("data/sales_" + id + ".txt")) {
            writer.write("CC;" + id + "\n");
            for (int i = 0; i < randomSalesCount; i++) {
                int productId = rand.nextInt(5) + 1;
                int quantity = rand.nextInt(10) + 1;
                writer.write(productId + ";" + quantity + "\n");
            }
            System.out.println("Sales file created successfully for salesman: " + name);
        } catch (IOException e) {
            System.err.println("Error writing sales file for salesman: " + name);
        }
    }

    public static void createProductsFile(int productsCount) {
        try (FileWriter writer = new FileWriter("data/products.txt")) {
            Random rand = new Random();
            for (int i = 1; i <= productsCount; i++) {
                String productName = "Product" + i;
                double price = (rand.nextInt(1500) + 50) * 1.0;
                writer.write(i + ";" + productName + ";" + price + "\n");
            }
            System.out.println("Products file created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing products file.");
        }
    }

    public static void createSalesManInfoFile(int salesmanCount) {
        try (FileWriter writer = new FileWriter("data/salesman_info.txt")) {
            Random rand = new Random();
            for (int i = 0; i < salesmanCount; i++) {
                String firstName = FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)];
                String lastName = LAST_NAMES[rand.nextInt(LAST_NAMES.length)];
                long id = 10000000L + rand.nextInt(90000000);
                writer.write("CC;" + id + ";" + firstName + ";" + lastName + "\n");
            }
            System.out.println("Salesman info file created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing salesman info file.");
        }
    }

    public static void main(String[] args) {
        createSalesMenFile(5, "John Doe", 12345678L);
        createProductsFile(5);
        createSalesManInfoFile(5);
    }
}
