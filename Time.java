/*
Authors: Zahra Suhail, Akshaya Varakunan, Lucy Qi
Date: 01/27/2022
Description: Timer countdown
*/

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class Time extends JPanel {
  JLabel label;
  Timer timer;
  int count;
  public Time(int lev) {
    label = new JLabel("...");
    setLayout(new GridBagLayout());
    add(label);
    //If player selects a particular level, the timer is set differently 
    if (lev == 1) { // easy level, gets more time
      count = 60;
    } else if (lev == 2) { // Medium level
      count = 45;
    } else { // Hard level, the least amount of time
      count = 30;
    }
    //Sets the speed for the timer
    timer = new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        count--;
        
        if (count > 0) {
          label.setText(Integer.toString(count));
        } else {
          // opens end screen
          ((Timer) (e.getSource())).stop();
          EndScreen es = new EndScreen();
        }
      }
    });
    timer.setInitialDelay(0);
    timer.start();
    
  }
  @Override
  
  public Dimension getPreferredSize() {
    return new Dimension(100, 150);
  }
}