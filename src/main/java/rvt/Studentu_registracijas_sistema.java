package rvt;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Studentu_registracijas_sistema {

    private ArrayList<String> students = new ArrayList<>();
    private final String filePath = "data/students.csv";
    private Scanner sc = new Scanner(System.in);

    private void loadFromFile() {
        students.clear();
        try (Scanner r = new Scanner(new File(filePath))) {
            while (r.hasNextLine()) {
                students.add(r.nextLine());
            }
        } catch (Exception e) {
            System.out.println("File not found, a new one will be created.");
        }
    }

    private void saveToFile() {
        try (PrintWriter w = new PrintWriter(new File(filePath))) {
            for (String s : students) {
                w.println(s);
            }
        } catch (Exception e) {
            System.out.println("Error saving file!");
        }
    }

    private void register() {
        System.out.print("First Name: ");
        String name = sc.nextLine();

        System.out.print("Last Name: ");
        String surname = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Personal ID: ");
        String code = sc.nextLine();

        if (name.length() < 3 || surname.length() < 3 || !email.contains("@") || code.length() != 11) {
            System.out.println("Invalid data!");
            return;
        }

        for (String s : students) {
            String[] p = s.split(",");
            if (p[2].equals(email) || p[3].equals(code)) {
                System.out.println("This email or personal ID already exists!");
                return;
            }
        }

        String dateTime = LocalDateTime.now().toString();
        students.add(name + "," + surname + "," + email + "," + code + "," + dateTime);
        saveToFile();
        System.out.println("Student registered!");
    }

    private void show() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (String s : students) {
            String[] p = s.split(",");
            System.out.println(p[0] + " " + p[1] + " | " + p[2] + " | " + p[3] + " | " + p[4]);
        }
    }

    private void remove(int id) {
        String code = String.valueOf(id);
        for (int i = 0; i < students.size(); i++) {
            String[] p = students.get(i).split(",");
            if (p[3].equals(code)) {
                students.remove(i);
                saveToFile();
                System.out.println("Student removed!");
                return;
            }
        }
        System.out.println("Student with this ID not found!");
    }

    public void edit() {
        System.out.print("Personal ID: ");
        String code = sc.nextLine();
        for (int i = 0; i < students.size(); i++) {
            String[] p = students.get(i).split(",");
            if (p[3].equals(code)) {
                System.out.print("New Email: ");
                String email = sc.nextLine();
                if (!email.contains("@")) {
                    System.out.println("Invalid email!");
                    return;
                }
                p[2] = email;
                students.set(i, String.join(",", p));
                saveToFile();
                System.out.println("Student data updated!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    public static void main(String[] args) {
        Studentu_registracijas_sistema students = new Studentu_registracijas_sistema();
        students.loadFromFile();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Command: ");
            String command = input.nextLine().trim();

            if (command.equals("exit")) {
                break;

            } else if (command.equals("register")) {
                students.register();

            } else if (command.equals("show")) {
                students.show();

            } else if (command.equals("remove")) {
                System.out.print("Personal ID: ");

                students.remove(Integer.parseInt(input.nextLine()));
            } else if (command.equals("edit")) {
                students.edit();

            } else {
                System.out.println("Unknown command!");
            }
        }
        input.close();
    }
}