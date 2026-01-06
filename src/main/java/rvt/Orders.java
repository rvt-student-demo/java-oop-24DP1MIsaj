package rvt;
import java.io.File;
import java.util.Scanner;

public class Orders {
    try (Scanner reader = new Scanner(new File("rvt/orders.csv"))) {
        reader.nextLine();
        while (reader.hasNextLine()) {
            String row = reader.nextLine();
            String[] parts = row.split(",");

            String OrderID = parts[0];
            String Customer = parts[1];
            String Product = parts[2];
            int Quantity = Integer.valueOf(parts[3]);
            float Price = Integer.valueOf(parts[4]);

            System.out.println("Pasūtījums #" + OrderID + ": " + Customer + " pasūtīja " + Quantity + " x " + Product + " (" + Price + " EUR) -> Kopā: " + (Quantity * Price) + " EUR");

        }
    }
}
