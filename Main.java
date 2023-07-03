/*
Authors: Zahra Suhail, Akshaya Varakunan, Lucy Qi
Date: 01/27/2022
Description: Runs program
*/

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GamePanel();
        });
    }
}