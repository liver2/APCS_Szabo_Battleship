/**
 * Main.java
 * @author Oliver Szabo
 * @since 10/19/2023
 * 
 * The main class for my Battleship program serves as a "menu," 
 * with an introduction, rules, and a place from which 
 * the user can launch either a normal game (n) or a fast game (f).
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) { 
        Scanner scanString = new Scanner(System.in); // create scanner to scan string;two scanners b/c it gets buggy with num and string used under one.

        Battleship game = new Battleship(); // create new battleship instance (battleship is where all the methods are stored, less cluttered)
        String s1; // for future player input

        System.out.println("--- Battleship ---");
        System.out.println("Would you like to hear the instructions? (y/n)");
        do {
            s1 = scanString.nextLine();

            if (!(s1.equals("y") || s1.equals("n"))) {
                System.out.println("Please input y or n.");
            }

            if (s1.equals("y")) {
                System.out.println("Welcome to Battleship!");
                System.out.println("For this version of Battleship, you can choose 2 modes: Fast and Regular.");
                System.out.println("Let me explain the rules.\n"); // Places new line
                System.out.println("In Regular mode, you and a friend will each place 5 ships — whose positions the other will then try to guess — on a 10x10 grid.");
                System.out.println("The ships will be lengths 2, 3, 3, 4, and 5; this means they will occupy spaces horizontally or vertically next to each other.");
                System.out.println("Ships cannot be placed diagonally; only horizontally and vertically. They cannot extend off the board, and they cannot overlap.");
                System.out.println("After you've placed your ships, you and your friend will take turns guessing the positions of each others' ships.");
                System.out.println("You will do this using coordinates: You will fire one shot at a single spot.");
                System.out.println("If you hit one part of a ship, an X will show where you fired your shot.");
                System.out.println("If you miss, there will be a lowercase M there instead.");
                System.out.println("When you've sunk a ship, the indicator S will appear over the squares where the ship formerly was.");
                System.out.println("If you can sink your friend's 5 ships, you win! Vice versa; whoever gets all their five ships sunk first loses.\n");
                System.out.println("In Fast mode, a computer will randomize the placement of 3 ships on an 8x8 board.");
                System.out.println("The ships will be lengths 2, 3, and 4.");
                System.out.println("You will guess where the computer's ships are; same rules about indicators apply.");
                System.out.println("Remember: X is hit, S is sunk, m is miss.");
                System.out.println("When you sink all the ships, you will learn how many turns it took you; lower is better!\n");
                break;
            }

            if (s1.equals("n")) {
                break; // exiting loop and moving to the "select mode"
            }
        } while (!s1.equals("y") && !s1.equals("n"));

        do { // do-while to check for correct input
            System.out.println("So, tell me; what mode would you like to play today? Fast (input f, then enter) or Normal (input n, then enter)?");
            s1 = scanString.nextLine();

            if (!(s1.equals("n")) && !(s1.equals("f"))) { // check that this works properly
                System.out.println("Please input n or f.");
            }
        } while (!s1.equals("n") && !s1.equals("f")); // only exits the loop when "n" or "f" are inputted.

        if (s1.equals("n")) {
            game.normalGame();
        } else if (s1.equals("f")) {
            game.fastGame();
        } // two different methods govern which game is played
    }
}
