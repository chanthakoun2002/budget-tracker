package org.budgettracker;
import javax.swing.JFrame;

public class MainWindow {
    private JFrame window;

    public MainWindow() {
        window = new JFrame();
        window.setTitle("Test");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 500);
        window.setLocationRelativeTo(null); // centers to screen

    }

    public void Show() {
        window.setVisible(true);

    }
}
