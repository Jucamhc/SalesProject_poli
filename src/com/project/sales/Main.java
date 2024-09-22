import java.io.*;
import java.util.*;

public class Main {
    static class Salesman {
        String id;
        String name;
        double totalSales;

        public Salesman(String id, String name) {
            this.id = id;
            this.name = name;
            this.totalSales = 0.0;
        }
    }

    static class Product {
        String id;
        String name;
        double price;
        int totalQuantitySold;

        public Product(String id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.totalQuantitySold = 0;
        }
    }

    public static Map<String, Salesman> loadSalesmanInfo(String filePath) throws IOException {
        Map<String, Salesman> salesmen = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String id = parts[1];
                String name = parts[2] + " " + parts[3];
                salesmen.put(id, new Salesman(id, name));
            }
        }
        return salesmen;
    }

    public static Map<String, Product> loadProducts(String filePath) throws IOException {
        Map<String, Product> products = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String id = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                products.put(id, new Product(id, name, price));
            }
        }
        return products;
    }

    public static void processSales(String folderPath, Map<String, Salesman> salesmen, Map<String, Product> products)
            throws IOException {
       
    }

    public static void writeSalesmanReport(String filePath, Map<String, Salesman> salesmen) throws IOException {
       
    }

    public static void writeProductReport(String filePath, Map<String, Product> products) throws IOException {
       
    }

    public static void main(String[] args) {
        try {
            Map<String, Salesman> salesmen = loadSalesmanInfo("data/salesman_info.txt");
            Map<String, Product> products = loadProducts("data/products.txt");

            processSales("data", salesmen, products);

            writeSalesmanReport("data/sales_report.csv", salesmen);
            writeProductReport("data/products_report.csv", products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/* writeSalesmanReport("data/salesman_report.csv",salesmen);
writeProductReport("data/product_report.csv",products); */