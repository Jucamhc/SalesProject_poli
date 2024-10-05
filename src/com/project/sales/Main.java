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
        File folder = new File(folderPath);
        File[] salesFiles = folder.listFiles((dir, name) -> name.startsWith("sales_") && name.endsWith(".txt"));

        if (salesFiles != null) {
            for (File salesFile : salesFiles) {
                try (BufferedReader br = new BufferedReader(new FileReader(salesFile))) {
                    String line = br.readLine(); // Leer la primera línea (vendedor)
                    String[] parts = line.split(";");
                    if (parts.length < 2) {
                        System.err.println("Invalid format in sales file: " + salesFile.getName());
                        continue;
                    }
                    String salesmanId = parts[1];

                    Salesman salesman = salesmen.get(salesmanId);
                    if (salesman == null) {
                        System.err.println("Salesman with ID " + salesmanId + " not found.");
                        continue;
                    }

                    // Leer las ventas de productos
                    while ((line = br.readLine()) != null) {
                        parts = line.split(";");
                        if (parts.length < 2) {
                            System.err.println("Invalid format in product sales data in file: " + salesFile.getName());
                            continue;
                        }
                        String productId = parts[0];
                        int quantitySold = Integer.parseInt(parts[1]);

                        Product product = products.get(productId);
                        if (product == null) {
                            System.err.println("Product with ID " + productId + " not found.");
                            continue;
                        }

                        // Sumar la venta al vendedor
                        double saleAmount = product.price * quantitySold;
                        salesman.totalSales += saleAmount;

                        // Sumar la cantidad vendida al producto
                        product.totalQuantitySold += quantitySold;
                    }
                }
            }
        }
    }

    public static void writeSalesmanReport(String filePath, Map<String, Salesman> salesmen) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            salesmen.values().stream()
                    .sorted((s1, s2) -> Double.compare(s2.totalSales, s1.totalSales))
                    .forEach(salesman -> {
                        try {
                            writer.write(salesman.name + ";" + salesman.totalSales + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            System.out.println("Salesman report generated successfully.");
        }
    }

    public static void writeProductReport(String filePath, Map<String, Product> products) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            products.values().stream()
                    .sorted((p1, p2) -> Integer.compare(p2.totalQuantitySold, p1.totalQuantitySold))
                    .forEach(product -> {
                        try {
                            double totalRevenue = product.totalQuantitySold * product.price;
                            writer.write(product.name + ";" + product.totalQuantitySold + ";" + product.price + ";"
                                    + totalRevenue + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            System.out.println("Product report generated successfully.");
        }
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