package rvt;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Composition
public class TodoUI {
    private JFrame window;
    private JPanel panel;
    private JButton button;

    public TodoUI() {
        initialize();
    }
    
    private void initialize() {
        window = new JFrame("Todo App");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(1000, 1000);
        window.setLocationRelativeTo(null);

        window.setLayout(new BorderLayout(10, 10));

        window.add(new JButton("NORTH"), BorderLayout.NORTH);
        window.add(new JButton("WEST"), BorderLayout.WEST);
        window.add(new JButton("EAST"), BorderLayout.EAST);
        window.add(new JButton("SOUTH"), BorderLayout.SOUTH);

        panel = new JPanel();
        panel.setBackground(Color.MAGENTA);
        window.add(panel, BorderLayout.CENTER);

        button = new JButton("Click");
        window.add(button, BorderLayout.CENTER);
    }

    public void show() {
        window.setVisible(true);
    }

    public static void main(String[] args) {
        // Creating instance of TodoUI
        TodoUI ui = new TodoUI();

        ui.show();
    }
}