package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // Causes this Window to be sized to fit the preferred size and layouts
        // of its subcomponents (=GamePanel)
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setLocation(4000, 500);

        gamePanel.startGameThread();
    }
}
