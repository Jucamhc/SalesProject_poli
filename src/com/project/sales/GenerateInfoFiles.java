import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GenerateInfoFiles {
    private static final String[] FIRST_NAMES = { "John", "Alice", "Mark", "Sophia", "Edward" };
    private static final String[] LAST_NAMES = { "Doe", "Smith", "Johnson", "Williams", "Brown" };
    private static final List<Long> salesmanIds = new ArrayList<>(); // Lista para almacenar los IDs de vendedores

    public static void createSalesMenFile(int randomSalesCount, long id) {
        Random rand = new Random();
        try (FileWriter writer = new FileWriter("data/sales_" + id + ".txt")) {
            writer.write("CC;" + id + "\n");
            for (int i = 0; i < randomSalesCount; i++) {
                int productId = rand.nextInt(5) + 1;
                int quantity = rand.nextInt(10) + 1;
                writer.write(productId + ";" + quantity + "\n");
            }
            System.out.println("Sales file created successfully for salesman ID: " + id);
        } catch (IOException e) {
            System.err.println("Error writing sales file for salesman ID: " + id);
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
                salesmanIds.add(id); // Almacena el ID en la lista
                writer.write("CC;" + id + ";" + firstName + ";" + lastName + "\n");
            }
            System.out.println("Salesman info file created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing salesman info file.");
        }
    }

    public static void main(String[] args) {
        // Crear el directorio data si no existe
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            boolean created = dataDirectory.mkdir();
            if (!created) {
                System.err.println("No se pudo crear el directorio 'data'. Verifica los permisos.");
                return;
            }
        }

        createSalesManInfoFile(5); // Primero crea el archivo de información de vendedores

        for (long id : salesmanIds) { // Luego crea los archivos de ventas usando los IDs generados
            createSalesMenFile(5, id);
        }

        createProductsFile(5);
    }
}
