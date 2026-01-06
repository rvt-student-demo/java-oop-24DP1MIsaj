package rvt;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Orders {
    public static void main(String[] args) {
         try (Scanner reader = new Scanner(new File("data/orders.csv"))) {
            reader.nextLine();

            double result = 0;
            while (reader.hasNextLine()) {
                String row = reader.nextLine();
                String[] parts = row.split(",");

                String OrderID = parts[0];
                String Customer = parts[1];
                String Product = parts[2];
                int Quantity = Integer.valueOf(parts[3]);
                double Price = Double.valueOf(parts[4]);

                System.out.println("Pasūtījums #" + OrderID + ": " + Customer + " pasūtīja " + Quantity + " x " + Product + " (" + Price + " EUR) -> Kopā: " + (Quantity * Price) + " EUR");
                result += Quantity * Price;
            }

            System.out.println("Kopējā pasūtījumu summa: " + result);
            
        } catch (FileNotFoundException e) {
            System.out.println("Fails orders.csv netika atrasts!");
        }
    }
}
