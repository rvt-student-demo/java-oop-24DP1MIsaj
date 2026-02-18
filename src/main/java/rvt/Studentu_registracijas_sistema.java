package rvt;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Studentu_registracijas_sistema {

    private ArrayList<String> students = new ArrayList<>();
    private final String filePath = "data/students.csv";
    private Scanner sc = new Scanner(System.in);

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

    private void loadFromFile() {
        students.clear();
        try (Scanner r = new Scanner(new File(filePath))) {
            while (r.hasNextLine()) {
                students.add(r.nextLine());
            }
        } catch (Exception e) {
            System.out.println(YELLOW + "File not found, new file will be created." + RESET);
        }
    }

    private void saveToFile() {
        try (PrintWriter w = new PrintWriter(new File(filePath))) {
            for (String s : students) {
                w.println(s);
            }
        } catch (Exception e) {
            System.out.println(RED + "Error saving file!" + RESET);
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
            System.out.println(RED + "Invalid data!" + RESET);
            return;
        }

        for (String s : students) {
            String[] p = s.split(",");
            if (p[2].equals(email) || p[3].equals(code)) {
                System.out.println(YELLOW + "Email or Personal ID already exists!" + RESET);
                return;
            }
        }

        String dateTime = LocalDateTime.now().toString();
        students.add(name + "," + surname + "," + email + "," + code + "," + dateTime);
        saveToFile();
        System.out.println(GREEN + "Student registered!" + RESET);
    }

    private void show() {
        if (students.isEmpty()) {
            System.out.println(YELLOW + "No students found." + RESET);
            return;
        }

        for (String s : students) {
            String[] p = s.split(",");
            System.out.println(BLUE + p[0] + " | " + p[1] + " | " + p[2] + " | " + p[3] + " | " + p[4] + RESET);
        }
    }

    private void remove(int id) {
        String code = String.valueOf(id);
        for (int i = 0; i < students.size(); i++) {
            String[] p = students.get(i).split(",");
            if (p[3].equals(code)) {
                students.remove(i);
                saveToFile();
                System.out.println(GREEN + "Student removed!" + RESET);
                return;
            }
        }
        System.out.println(RED + "Student with this ID not found!" + RESET);
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
                    System.out.println(RED + "Invalid email!" + RESET);
                    return;
                }
                p[2] = email;
                students.set(i, String.join(",", p));
                saveToFile();
                System.out.println(GREEN + "Student data updated!" + RESET);
                return;
            }
        }
        System.out.println(RED + "Student not found!" + RESET);
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