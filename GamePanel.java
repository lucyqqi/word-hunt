/*
Authors: Zahra Suhail, Akshaya Varakunan, Lucy Qi
Date: 01/27/2022
Description: Runs and displays the game
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.*;
import javax.swing.*;

public class GamePanel extends JFrame implements ActionListener {
    static JPanel drawPanel; //used to hold content of frame
    static boolean drawOn = false;
    static boolean pressed = false;
    boolean homeScreen = true;
    JTextField username;
    JButton play;
    JMenuBar menu;
    JLabel title;
    JLabel name;
    JLabel level;
    JLabel instructions;
    JMenu m1;
    Container c = getContentPane();
    Board brd = new Board();
    static JLabel scoreLabel;
    int lvl = 0;  
    

    public GamePanel() {
         
        this.setTitle("Word Hunt");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600,400);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.black);
        this.setLocationRelativeTo(null);
       
        


        //used to create menu headings
        menu = new JMenuBar();
        m1 = new JMenu("Choose a Level");
        menu.add(m1);

        //create menu subheadings and add them under the headings
        JMenuItem easy = new JMenuItem("Easy");
        JMenuItem medium = new JMenuItem("Medium");
        JMenuItem hard = new JMenuItem("Hard");

        menu.setSize(120,30);
        menu.setLocation(330,125);

   
         // score board for the board game
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setSize(100,60);
        scoreLabel.setLocation(460,45);
        scoreLabel.setForeground(new Color(214, 195, 255));

        // adding and formatting the content
        title = new JLabel("Word Hunt Game");
        title.setSize(280,50);
        title.setLocation(210,50);
        title.setFont(new Font("Bank Gothic", Font.BOLD, 20));
        title.setForeground(new Color(214, 195, 255));

        name = new JLabel("Enter your name: ");
        name.setSize(220,50);
        name.setLocation(190,80);
        name.setForeground(new Color(236, 247, 255));

        username = new JTextField(7);
        username.setSize(100,20);
        username.setLocation(330,96);

        level = new JLabel ("Level of difficulty: ");
        level.setSize(290,200);
        level.setLocation(190,40);
        level.setForeground(new Color(236, 247, 255));

        play = new JButton("PLAY");
        play.addActionListener(this); 
        play.setSize(70,30);
        play.setLocation(260,160);
        play.setBackground(new Color(216, 225, 240));
        play.setForeground(new Color(20, 33, 84));

        c.setLayout(null);
        c.add(title);
        c.add(name);
        c.add(username);
        c.add(play);
        scoreLabel.setVisible(false); 
        play.setVisible(false);
        c.add(brd);
        c.add(level);
        c.add(scoreLabel); 

        m1.add(easy);
        m1.add(medium);
        m1.add(hard);

        brd.setSize(300,300);
        brd.setLocation(140,50);
        brd.setVisible(false);

        MouseAdapter eh = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                pressed = true;
                new Thread(() -> GamePanel.mouse(e)).start();
            }
            public void mouseDragged(MouseEvent e) {
                mousePressed(e);
            }
            public void mouseReleased(MouseEvent e) {
                pressed = false;
            }
        };

        //listen for mouse input and movement
        addMouseListener(eh);
        addMouseMotionListener(eh);

        //have the ActionListener watch the buttons in drop down menu (so we know if they have been clicked)
        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);

        //use a borderLayout to place the menu bar
        this.getContentPane().add(menu,BorderLayout.WEST);

        drawPanel = new JPanel();
        drawPanel.setPreferredSize(new Dimension(400, 400)); //leave out the drop down menu bar
        drawPanel.setBackground(Color.white); //The background is white. If the colour is changed (ie to red), the top bit won't be coloured
        this.add(drawPanel, BorderLayout.CENTER);


        // closes home page when the start button is pressed
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(true);
                JFrame frame = new JFrame();// once the game begins the timer will appear
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new Time(lvl));
                frame.pack();
                frame.setVisible(true);
            }
        }
        );
    }
    

    //main method just calls constructor
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }

    /*
    Description: changes between homescreen, instructions, and game
    Precondition: --
    Postcondition: --
    */
    public void actionPerformed(ActionEvent evt){
          instructions = new JLabel("<html>Instructions: Find as many words as you can before the time runs out!<br>Click a letter and drag your mouse to adjacent letters to select a word.<br>You can drag vertically, horizontally, or diagonally between two letters.</html>");
          instructions.setForeground(new Color(255, 255, 255));
          instructions.setSize(880,100);
          instructions.setLocation(60,70);
          c.add(instructions);
          instructions.setVisible(false);
        if (evt.getActionCommand().equals("Easy")) {
          // Allows for the instructions to be displayed
          title.setVisible(true);
          name.setVisible(false);
          username.setVisible(false);
          play.setVisible(true);
          m1.setVisible(false);
          menu.setVisible(false);
          brd.setVisible(false);
          level.setVisible(false);
          instructions.setVisible(true);
          lvl = 1;
       
         
        }
        if (evt.getActionCommand().equals("Medium")) {
          title.setVisible(true);
          name.setVisible(false);
          username.setVisible(false);
          play.setVisible(true);
          m1.setVisible(false);
          menu.setVisible(false);menu.setVisible(false);
          brd.setVisible(false);
          level.setVisible(false);
          instructions.setVisible(true);
          lvl = 2;
          
        }
        if (evt.getActionCommand().equals("Hard")) {
          title.setVisible(true);
          name.setVisible(false);
          username.setVisible(false);
          play.setVisible(true);
          m1.setVisible(false);
          menu.setVisible(false);
          brd.setVisible(false);
          level.setVisible(false);
          instructions.setVisible(true);
          lvl = 3;
          
          
        }
        repaint();
    }

    /*
    Description: does literally nothing but is necessary to override
    Precondition: --
    Postcondition: --
    */
    public static void mouse(MouseEvent e) {
    }

    /*
    Description: displays game if b is true; returns to menu if b is false
    Precondition: --
    Postcondition: --
    */
    public void startGame(boolean b) {
        if (b) {
            title.setVisible(false);
            name.setVisible(false);
            username.setVisible(false);
            play.setVisible(false);
            m1.setVisible(false);
            menu.setVisible(false);
            instructions.setVisible(false);
            brd.setVisible(true);    
            scoreLabel.setVisible(true);       
        } else {
            title.setVisible(true);
            name.setVisible(true);
            username.setVisible(true);
            play.setVisible(true);
            m1.setVisible(true);
            menu.setVisible(true);
            brd.setVisible(false);
            level.setVisible(false);
            instructions.setVisible(false);
            scoreLabel.setVisible(false);
        }
    }


}