/*
Authors: Zahra Suhail, Akshaya Varakunan, Lucy Qi
Date: 01/27/2022
Description: Creates game board
*/

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel {
    Letter[][] letters;
    HashSet<String> words = new HashSet<>();
    // used a stack because a stack is useful to keep track of the order that the letters are being used
    Stack<Letter> currentWord = new Stack<>();
    static Color bkg = new Color(255, 252, 252);
    static Color off = new Color(26, 24, 84);
    static boolean active = false;
    // count total score 
    public static int countTotal = 0;
    

    public Board() {

    	getWords();
      setBackground(Color.black);

    	setLayout(new GridLayout(4,4,5,5));
    	letters = new Letter[4][4];
    	for (int i = 0; i < 4; i++) {
    	    for (int j = 0; j < 4; j++) {
    	        double rx = Math.random();
                Letter lt;
                // generates letters that are more likely to be common
                if (rx < 0.5) {
                    lt = new Letter(0);
                } else if (rx < 0.9) {
                    lt = new Letter(1);
                } else {
                    lt = new Letter(2);
                }
    	        letters[i][j] = lt;
              // handles input to the letters
              // creates a letter, and adds a mouse listenere to the letter. if it's active, add it to the stack of letters, and change its color
    	        lt.addMouseListener(new MouseAdapter() {
    	            public void mousePressed(MouseEvent e) {
    	                active = true;
    	                currentWord.push(lt);
    	                lt.setForeground(bkg);
    	                lt.setBackground(off);
    	            }
                  /*
                  Description: when the player lets go of the mouse, then set active to false, convert the stack to a letter array and loop through it to determine the word and number of points
                  Precondition: --
                  Postcondition: --
                  */
    	            public void mouseReleased(MouseEvent e) {
    	                active = false;
    	                Letter[] word = currentWord.toArray(new Letter[0]);
    	                int points = 0;
    	                String s = "";
    	                for (int i = 0; i < word.length; i++) {
    	                    s += word[i].getLetter();
    	                    points += word[i].getValue();
    	                }
    	                System.out.println(s);
                     // if the word is in the wordlist, add that many points and remove it from the wordlist so that the plaser can't get it again and then just go through the letter list and clear the color of all of them
    	                if (words.contains(s.toLowerCase())) {
                          JLabel scoreLabel = GamePanel.scoreLabel;
                          countTotal+=points;
                          scoreLabel.setText("Score: "+countTotal); 
    	                    System.out.println("Player won " + points + " points.");
    	                    words.remove(s.toLowerCase());
    	                }
    	                while (!currentWord.isEmpty()) {
    	                    Letter l = currentWord.pop();
    	                    l.setForeground(off);
    	                    l.setBackground(bkg);
    	                }
    	            }
                  /*
                  Description: gets triggered if the player moves the mouse onto a new tile.  if the letter is already in the list, we keep removing letters from the list until we're at the current letter
                  Precondition: --
                  Postcondition: --
                  */
    	            public void mouseEntered(MouseEvent e) {
    	                if (!active) return;
    	                if (!currentWord.contains(lt)) {
    	                    currentWord.push(lt);
    	                    lt.setForeground(bkg);
    	                    lt.setBackground(off);
    	                } else {
    	                    while (currentWord.peek() != lt) {
    	                        Letter l = currentWord.pop();
    	                        l.setForeground(off);
    	                        l.setBackground(bkg);
    	                    }
    	                }
    	            }
    	        });
    	        add(lt);
    	    }
    	}

    }

    /*
    Description: checks if a given string is a valid word in the word list
    Precondition: s is not null
    Postcondition: returns a boolean that is true if s is valid and false otherwise
    */
    public boolean isString(String s) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("words.txt"));
        while (true) {
            String word = br.readLine();
            if (word == null) {
                break;
            } if (word.compareTo(s) > 0) {
                return false;
            } else if (word.compareTo(s) == 0) {
                return true;
            }
        }
        return false;
    }
    
    /*
    Description: get the words from the file and add them to the list of words in the board constructor
    Precondition: --
    Postcondition: --
    */
    private void getWords() {
        try (Scanner file = new Scanner(new File("words.txt"))){
            while (file.hasNext()) {
                words.add(file.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
