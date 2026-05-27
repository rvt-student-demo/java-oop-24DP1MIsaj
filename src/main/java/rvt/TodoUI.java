package rvt;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Composotion
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
        window.setBackground(Color.CYAN);

        window.setLayout(new BorderLayout());
        button = new JButton();
        window.add(new JButton("NORTH"), BorderLayout.NORTH);
        window.add(new JButton("WEST"), BorderLayout.WEST);
        window.add(new JButton("EAST"), BorderLayout.EAST);
        window.add(new JButton("SOUTH"), BorderLayout.SOUTH);

        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        borderLayout.setVgap(10);

        panel = new JPanel();
        panel.setBackground(Color.MAGENTA);
        window.add(panel);

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