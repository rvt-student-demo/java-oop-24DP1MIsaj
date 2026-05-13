package rvt;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class Main {
    public static void main(String[] args) {
        try {

            Connection conn = DriverManager.getConnection("jdbc:sqlite:todo.db");
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS todo " +
                "(id INTEGER PRIMARY KEY, task TEXT NOT NULL) STRICT"
            );

            stmt.executeUpdate(
                "INSERT INTO todo(task) VALUES('Make a dance!')"
            );

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}