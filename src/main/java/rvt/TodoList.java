package rvt;
import java.io.*;
import java.util.*;

public class TodoList {
    private ArrayList<String> tasks = new ArrayList<>();
    private final String filePath = "data/todo.csv";

    public TodoList() {
        loadFromFile();
    }

    private void loadFromFile() {
        try (Scanner reader = new Scanner(new File(filePath))) {
            if (reader.hasNextLine()) {
                reader.nextLine();
            }
            while (reader.hasNextLine()) {
                tasks.add(reader.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Fails todo.csv netika atrasts!");
        }
    }

    private int getLastId() {
        if (tasks.isEmpty()) {
            return 0;
        }

        String lastLine = tasks.get(tasks.size() - 1);
        String[] parts = lastLine.split(",", 2);
        return Integer.parseInt(parts[0]);
    }

    private void updateFile() {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.println("id,task");
            for (String task : tasks) {
                writer.println(task);
            }
        } catch (Exception e) {
            System.out.println("Neizdevās saglabāt failu!");
        }
    }

    public void add(String taskText) {
        int newId = getLastId() + 1;
        tasks.add(newId + "," + taskText);
        updateFile();
    }

    public void remove(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            String[] parts = tasks.get(i).split(",");
            if (Integer.parseInt(parts[0]) == id) {
                tasks.remove(i);
                updateFile();
                return;
            }
        }

        System.out.println("Uzdevums ar ID " + id + " nav atrasts!");
    }

    public void print() {
        for (String task : tasks) {
            String[] parts = task.split(",", 2);
            System.out.println(parts[0] + ": " + parts[1]);
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

    public static void main(String[] args) {
        TodoList list = new TodoList();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Command: ");
            String command = input.nextLine().trim();

            if (command.equals("stop")) {
                break;

            } else if (command.equals("add")) {
                System.out.print("To add: ");
                String text = input.nextLine();

                if (list.checkEventString(text)) {
                    list.add(text);
                } else {
                    System.out.println("Nederīgs teksts! Min. 3 simboli, tikai burti, cipari un atstarpes.");
                }

            } else if (command.equals("remove")) {
                System.out.print("Which one is removed? ");
                list.remove(Integer.parseInt(input.nextLine()));

            } else if (command.equals("list")) {
                list.print();
            }
        }

        input.close();
    }
}
