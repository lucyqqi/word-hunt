/*
Authors: Zahra Suhail, Akshaya Varakunan, Lucy Qi
Date: 01/27/2022
Description: Generates and stores information about a letter on the board
*/

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.*;

public class Letter extends JButton{
    // point values of each letter
    static int[] values = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    char c;
    int value;

      public Letter(int common) {
        if (common == 0) {
            c = "AEIOUS".charAt((int)(Math.random()*6));
        } else if (common == 1) {
            c = "LNRTHMPW".charAt((int)(Math.random()*8));
        } else {
            c = "BCDFGJKQVXYZ".charAt((int)(Math.random()*12));
        }
        value = values["ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(c)];

        setText(getLetter());
        setHorizontalTextPosition(SwingConstants.CENTER);
        setForeground(Board.off);
        setBackground(Board.bkg);
        setOpaque(true);
        setFont(new Font("Helvetica", Font.BOLD, 20));
    }
    
    /*
    Description: returns letter
    Precondition: --
    Postcondition: returns the letter as a String
    */
    public String getLetter() {
        return String.valueOf(c);
    }

    /*
    Description: returns the value of the letter
    Precondition: --
    Postcondition: --
    */
    public int getValue() {
        return value;
    }
}