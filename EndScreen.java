/*
Authors: Zahra Suhail, Akshaya Varakunan, Lucy Qi
Date: 01/27/2022
Description: Creates end screen
*/

import javax.swing.*;
import java.awt.*;

public class EndScreen extends JFrame {
    int score;
    JLabel label;
    JLabel dScore;
    public EndScreen() {

        this.setTitle("Word Hunt");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600,400);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.black);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        setBackground(new Color(0,0,0));
        score = Board.countTotal;

        // format text

        label = new JLabel("GAME OVER");
        label.setFont(new Font("Helvetica", Font.BOLD, 25));
        label.setForeground(new Color(214, 195, 255));
        label.setSize(400,100);
        label.setLocation(220,35);

        dScore = new JLabel("SCORE: " + score);
        dScore.setFont(new Font("Helvetica", Font.PLAIN, 17));
        dScore.setSize(200,50);
        dScore.setLocation(250,100);
        dScore.setForeground(new Color(255,255,255));
        add(label);
        add(dScore);

    }

}
