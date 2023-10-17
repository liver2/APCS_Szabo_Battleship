import java.util.InputMismatchException;
import java.util.Scanner;

public class Battleship { // The main "hub" where most methods that work with the Board and Ship classes reside and are called upon in the normalGame() and fastGame() methods
    private Scanner scanNum = new Scanner(System.in); // two scanners for different types
    private Scanner scanString = new Scanner(System.in); // reduce to 1 scanner?

    public void normalGame() { // Uses different methods within the Battleship class to structure and run normalGame()
        Board p1Board = new Board(10); // just like the battleship game, there should be 4 boards; 2 for players checking where they want their ships to go
        Board p1Guess = new Board(10); // and 2 for checking & tracking guesses on the other board
        Board p2Board = new Board(10);
        Board p2Guess = new Board(10);

        Ship[] p1 = new Ship[5]; // for player 1. creating new ships. no constructors yet, because the parameters of the ships are yet to be decided...
        Ship[] p2 = new Ship[5]; // for player 2

        for (int i = 0; i < 5; i++) {
            p1[i] = new Ship();
            p2[i] = new Ship();
        }

        System.out.println("Let's begin with Player 1. Player 2, please step away.\n");

        promptShipPlacement(2, p1Board, p1[0], "a"); // ship placement method
        promptShipPlacement(3, p1Board, p1[1], "b");
        promptShipPlacement(3, p1Board, p1[2], "c");
        promptShipPlacement(4, p1Board, p1[3], "d");
        promptShipPlacement(5, p1Board, p1[4], "e");

        System.out.println("Now let's continue with Player 2. Player 1, please step away.\n"); // transition to ship placcement, player 2
    
        promptShipPlacement(2, p2Board, p2[0], "a");
        promptShipPlacement(3, p2Board, p2[1], "b");
        promptShipPlacement(3, p2Board, p2[2], "c");
        promptShipPlacement(4, p2Board, p2[3], "d");
        promptShipPlacement(5, p2Board, p2[4], "e");

        System.out.println("Let's begin the guessing phase.");

        // Guessing phase

        while (true) { // don't worry, this won't infinitely loop!! using break when someone has won
            guess(p1Guess, p2Board, p2, 5, "1"); // guessing & checking if guess hits

            if (winCheck(p2, 5) == true) {
                System.out.println("All of Player 2's ships have been sunk; Player 1 has won!");
                System.out.println("Thank you for playing!");
                break; // breaks the loop and exits the program by doing so
            }

            guess(p2Guess, p1Board, p1, 5, "2");

            if (winCheck(p1, 5) == true) {
                System.out.println("All of Player 1's ships have been sunk; Player 2 has won!");
                System.out.println("Thank you for playing!");
                break;
            }
        }
    }

    public void fastGame() { // Uses different methods within the Battleship class to structure and run fast game
        int counter = 0; // counter to check score

        Board aiBoard = new Board(8); // two boards: one for the ai to place...
        Board pGuess = new Board(8); // and one for the player to guess on

        Ship[] aiShip = new Ship[3]; // similarly placing ships on the ai board

        for (int i = 0; i < 3; i++) {
            aiShip[i] = new Ship();
        }

        System.out.println("Please wait while the computer generates ships...");

        randomPlace(aiShip[0], aiBoard, 2); // repeatedly generates random ship configurations until the system finds one that is valid.
        randomPlace(aiShip[1], aiBoard, 3);
        randomPlace(aiShip[2], aiBoard, 4);
        
        // aiBoard.printBoard(); for debugging purposes.

        while (true) {
            guess(pGuess, aiBoard, aiShip, 3, "1");
            counter++;

            if (winCheck(aiShip, 3) == true) {
                System.out.println("You've sunk all the ships! You took " + counter + " turns.");
                break;
            }
        }
    }

    public void promptShipPlacement(int len, Board board, Ship ship, String ind) { // Method that goes through the ship placement process
        int x = 0; // for ship args
        int y = 0; // for ship args
        String orientation; // to set the parameters of the ship, we should declare a local variable specific to the function

        do {
            do {
                try {
                    System.out.println("Please specify an X coordinate for your ship with length " + len + ".");
                    x = scanNum.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please input a number from 1 to 10.");
                }
            } while (!(x >= 0 && x <= board.getSideLength())); // do while loop uses "not" conditional so that the condition to succeed is more clear

            do {
                try {
                    System.out.println("Please specify a Y coordinate for your ship with length " + len + ".");
                    y = scanNum.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please input a number from 1 to 10.");
                }
            } while (!(y >= 0 && y <= board.getSideLength()));

            System.out.println("Please specify an orientation for your ship with length " + len + ".");
            System.out.println("Specify north with n, south with s, east with e, and west with w."); // hopefully this is clear enough..
            orientation = scanString.nextLine();

            if (!(orientation.equals("n") || orientation.equals("s") || orientation.equals("e") || orientation.equals("w"))
                 || // ln. 40: if orientation satisfies the specified directions, move to the next check.
                 !(shipPlacementCheck(x, y, len, orientation, board) == true)) {
                    System.out.println("Your ship input is invalid. Let's try again.");
                }
        } while (!(orientation.equals("n") || orientation.equals("s") || orientation.equals("e") || orientation.equals("w"))
                 || // ln. 40: if orientation satisfies the specified directions, move to the next check.
                 !(shipPlacementCheck(x, y, len, orientation, board) == true)); // checks if ship goes off the board or overlaps

        System.out.println("Let's see your ship on the board...\n");

        ship.setShipParams(len, x, y, orientation); // sets the parameters of the ship according to the specified inputs

        for (int i = 0; i < len; i++) {
            board.setIndicator(ship.getPosition(i,0), ship.getPosition(i,1), ind); // setting each indicator on the board
        } 

        board.printBoard();
    }

    public boolean shipPlacementCheck(int x, int y, int len, String orientation, Board board) { // this method basically calculates the positions of a sample boat... 
        // ...and checks whether or not those positions overlap/go off the board
        int[][] pos = new int[len][3]; // local variable/array "pos" used to "preview" the ship's x and y coordinates
        pos[0][0] = x; // 
        pos[0][1] = y;
        for (int i = 0; i < len; i++) {
            pos[i][0] = x + i*(Boolean.compare(orientation.equals("e"), true)) - i*(Boolean.compare(orientation.equals("w"), true));
            pos[i][1] = y + i*(Boolean.compare(orientation.equals("s"), true)) - i*(Boolean.compare(orientation.equals("n"), true));
        } // for loop adds position according to the orientation

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 2; j++) {
                if (pos[i][j] > board.getSideLength() || pos[i][j] < 1) { // if x or why exceeds 10...
                    return false;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            if (!(board.getIndicator(pos[i][0],pos[i][1]).equals("."))) { // indicators go from 0-9, position goes from 1-10
                return false;
            }
        }

        return true;
    }
    
    public void shotCheck(int x, int y, Board board, Board conjBoard, Ship[] s, int len, String p) { // Feedback for shots, and also updates the indicator accordingly
        if (conjBoard.getIndicator(x,y).equals("a") || conjBoard.getIndicator(x,y).equals("b") 
         || conjBoard.getIndicator(x,y).equals("c") || conjBoard.getIndicator(x,y).equals("d") || conjBoard.getIndicator(x,y).equals("e")) { // for Board board...
            // ...checks if the board it is matched up with, conjBoard, has a ship at the position on which the gues was

            System.out.println("\nPlayer " + p + ", You've hit something!");
            board.setIndicator(x,y,"X"); // X is the symbol for hit.

            for (int i = 0; i < len; i++) {
                s[i].shot(x,y);
                // checks whether ships are sunk

                if (s[i].getSunk() == true) {
                    for (int j = 0; j < s[i].getLength(); j++) {
                        board.setIndicator(s[i].getPosition(j,0),s[i].getPosition(j,1),"S");
                    }
                }
            }

            board.printBoard();
        } else {
            System.out.println("Player " + p + ", You missed.");

            board.setIndicator(x,y,"m");
            board.printBoard();
        }
    }

    public void guess(Board board, Board conjBoard, Ship[] s, int len, String p) { // goes through the guessing procedure (player input, calls method to process guess, etc.)
        // board (p1Guess, p2Guess) is the Board of the one guessing. 
        // conjBoard (p2Board, p1Board) is the board of the one receiving the hit. ships are enemy ships
        int x = 0; // for inputs
        int y = 0; // for inputs

        System.out.println("Here is your board, Player " + p + ":");
        board.printBoard();

        do {
            try {
                System.out.println("What is the x-coordinate of the square you wish to fire a missle at?");
                x = scanNum.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please input a number from 1 to 10.");
            }
        } while (!(x >= 0 && x <= 10)); // do while loop uses "not" conditional so that the condition to succeed is more clear

        do {
            try {
                System.out.println("What is the y-coordinate of the square you wish to fire a missle at?");
                y = scanNum.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please input a number from 1 to 10.");
            }
        } while (!(y >= 0 && y <= 10)); // do while loop uses "not" conditional so that the condition to succeed is more clear

        shotCheck(x, y, board, conjBoard, s, len, p);
    }

    public void randomPlace(Ship argShip, Board board, int len) { // procedure to randomly place ships during a fast game
        int x;
        int y;
        int o;
        String orientation = "init"; // because it said i needed to initialize the variable......
            
        do {
            x = (int) ((Math.random()*board.getSideLength()) + 1); y = (int) ((Math.random()*board.getSideLength()) + 1); o = (int) ((Math.random()*4) + 1);
            if (o == 1) orientation = "n";
            if (o == 2) orientation = "e";
            if (o == 3) orientation = "s";
            if (o == 4) orientation = "w"; // to convert numerical "codes" for each letter to the letters, which are used in the program
        } while (!(shipPlacementCheck(x, y, len, orientation, board))); // randomly chooses parameters for ship placement and checks whether they follow the guidelines

        argShip.setShipParams(len, x, y, orientation); // setting the parameters of the argued ship to those randomly generated that fit the criteria

        for (int j = 0; j < len; j++) {
            board.setIndicator(argShip.getPosition(j,0), argShip.getPosition(j,1), "a"); // setting each indicator on the board
        } 
    }

    public boolean winCheck(Ship[] s, int len) { // method to check for if someone wins
        for (int i = 0; i < len; i++) {
            if (s[i].getSunk() != true) {
                return false;
            }
        }

        return true;
    }
}