package rvt;
import java.sql.*;
import java.util.Scanner;

public class TodoList {
    private Connection conn;

    public TodoList() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:todo.db");

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS todo (" +
                "id INTEGER PRIMARY KEY, " +
                "task TEXT NOT NULL" +
                ")"
            );
            stmt.close();

        } catch (SQLException e) {
            System.out.println("DB kļūda: " + e.getMessage());
        }
    }

    public void add(String task) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO todo(task) VALUES(?)"
            );
            stmt.setString(1, task);
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Neizdevās pievienot!");
        }
    }

    public void removeById(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM todo WHERE id = ?"
            );
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            stmt.close();

            if (rows == 0) {
                System.out.println("Uzdevums ar ID " + id + " nav atrasts!");
            }

        } catch (SQLException e) {
            System.out.println("Neizdevās dzēst!");
        }
    }

    public void findAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM todo");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("task"));
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Neizdevās nolasīt!");
        }
    }

    public boolean checkEventString(String value) {
        if (value == null) {
            return false;
        }

        if (value.length() < 3) {
            return false;
        }

        return value.matches("[\\p{L}\\p{N}\\p{P}\\s]+");
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Neizdevās aizvērt DB!");
        }
    }

    public static void main(String[] args) {
        TodoList list = new TodoList();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Command(stop, add, remove, list): ");
            String command = input.nextLine().trim();

            if (command.equals("stop")) {
                break;

            } else if (command.equals("add")) {
                System.out.print("To add: ");
                String text = input.nextLine();

                if (list.checkEventString(text)) {
                    list.add(text);
                } else {
                    System.out.println("Nederīgs teksts!");
                }

            } else if (command.equals("remove")) {
                list.findAll();
                System.out.print("Which one is removed? ");
                
                try {
                    list.removeById(Integer.parseInt(input.nextLine()));
                } catch (NumberFormatException e) {
                    System.out.println("Jāievada skaitlis (ID)!");
                }

            } else if (command.equals("list")) {
                list.findAll();
            }
        }

        list.close();
        input.close();
    }
}