package org.budgettracker;

import javax.swing.SwingUtilities;
//  This class launches the GUI and to use GUI this class needs
//  to run first as main class

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow main = new MainWindow();
                main.Show();

            }
        });
    }
}
