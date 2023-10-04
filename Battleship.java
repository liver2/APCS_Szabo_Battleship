import java.util.Scanner;

public class Battleship {
    private Scanner scanNum = new Scanner(System.in);
    private Scanner scanString = new Scanner(System.in); // reduce to 1 scanner?

    public static void normalGame() {
        Board p1Board = new Board(10);
        Board p1Guess = new Board(10);
        Board p2Board = new Board(10);
        Board p2Guess = new Board(10);

        Ship p1l2 = new Ship();
        Ship p1l3a = new Ship();
        Ship p1l3b = new Ship();
        Ship p1l4 = new Ship();
        Ship p1l5 = new Ship(); // array

        Ship p2l2 = new Ship();
        Ship p2l3a = new Ship();
        Ship p2l3b = new Ship();
        Ship p2l4 = new Ship();
        Ship p2l5 = new Ship();

        // Ship placement phase

        System.out.println("Let's begin with Player 1. Player 2, please step away.\n");

        promptShipPlacement(2, p1Board, p1l2, "a");
        promptShipPlacement(3, p1Board, p1l3a, "b");
        promptShipPlacement(3, p1Board, p1l3b, "c");
        promptShipPlacement(4, p1Board, p1l4, "d");
        promptShipPlacement(5, p1Board, p1l5, "e");

        System.out.println("Now let's continue with Player 2. Player 1, please step away.\n");

        promptShipPlacement(2, p2Board, p2l2, "a");
        promptShipPlacement(3, p2Board, p2l3a, "b");
        promptShipPlacement(3, p2Board, p2l3b, "c");
        promptShipPlacement(4, p2Board, p2l4, "d");
        promptShipPlacement(5, p2Board, p2l5, "e");

        System.out.println("Let's begin the guessing phase.");

        // Find a way to track how many Ships on both sides have been sunk
    }

    public static void fastGame() {
        Board aiBoard = new Board(8);
        Board pGuess = new Board(8);
    }

    public static void promptShipPlacement(int len, Board board, Ship ship, String ind) { // Method that goes through the ship placement process
        int x; // for ship args
        int y; // for ship args
        String orientation; // to set the parameters of the ship, we should declare a local variable specific to the function

        do {
            System.out.println("Please specify an X coordinate for your ship with length " + len + ".");
            x = scanNum.nextInt();
        } while (!(x >= 0 && x <= 10)); // do while loop uses "not" conditional so that the condition to succeed is more clear

        do {
            System.out.println("Please specify a Y coordinate for your ship with length " + len + ".");
            y = scanNum.nextInt();
        } while (!(y >= 0 && y <= 10));

        do {
            System.out.println("Please specify an orientation for your ship with length " + len + ".");
            System.out.println("Specify north with n, south with s, east with e, and west with w."); // hopefully this is clear enough..
            orientation = scanString.nextLine();
        } while (!(orientation.equals("n") || orientation.equals("s") || orientation.equals("e") || orientation.equals("w"))
                 || // ln. 40: if orientation satisfies the specified directions, move to the next check.
                 !(shipPlacementCheck(x, y, len, orientation, board) == true)); // checks if ship goes off the board or overlaps

        System.out.println("Let's see your ship on the board...\n");

        ship.setShipParams(len, x, y, orientation); // sets the parameters of the ship according to the specified inputs

        for (int i = 0; i < len; i++) {
            board.setIndicator(ship.getPosition(i,0), ship.getPosition(i,1), ind); // setting each indicator on the board
        } 

        board.printBoard(); // shows the board
    }

    public static boolean shipPlacementCheck(int x, int y, int len, String orientation, Board board) {
        int[][] pos = new int[len][3]; // local variable/array "pos" used to "preview" the ship's x and y coordinates
        pos[0][0] = x;
        pos[0][1] = y;
        for (int i = 0; i < len; i++) {
            pos[i][0] = x + i*(Boolean.compare(orientation.equals("e"), true)) - i*(Boolean.compare(orientation.equals("w"), true));
            pos[i][1] = y + i*(Boolean.compare(orientation.equals("s"), true)) - i*(Boolean.compare(orientation.equals("n"), true));
        } // for loop adds position according to the orientation

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 2; j++) {
                if (pos[i][j] > 10 || pos[i][j] < 1) {
                    return false;
                }
            }
        }

        for (int i = 0; i < board.getSideLength(); i++) {
            for (int j = 0; j < board.getSideLength(); j++) {
                if (board.getIndicator(i,j) != ".") {
                    return false;
                }
            }
        }

        return true;
    }
    
    public static void shotCheck(int x, int y, Board conjBoard, Board board, Ship s1, Ship s2, Ship s3, Ship s4, Ship s5) { // has to be a better way
        if (conjBoard.getIndicator(x,y).equals("a") || conjBoard.getIndicator(x,y).equals("b") 
         || conjBoard.getIndicator(x,y).equals("c") || conjBoard.getIndicator(x,y).equals("d") || conjBoard.getIndicator(x,y).equals("e")) {
            s1.shot(x,y);
            s2.shot(x,y);
            s3.shot(x,y);
            s4.shot(x,y);
            s5.shot(x,y); // EFFICIENCY

            System.out.println("You've hit something!");
            board.setIndicator(x,y,"X");
            board.printBoard();
        } else {
            System.out.println("You missed.");

            board.setIndicator(x,y,"m");
            board.printBoard(); // implement sinking
        }
    }

    public static void /* void for now */ guess(Board board, Board conjBoard, Ship s1, Ship s2, Ship s3, Ship s4, Ship s5) { // board (p1Guess, p2Guess) is the Board of the one guessing. 
        // conjBoard (p2Board, p1Board) is the board of the one receiving the hit.
        int x;
        int y;

        System.out.println("Here is your board:");
        board.printBoard();

        do {
            System.out.println("What is the x-coordinate of the square you wish to fire a missle at?");
            x = scanNum.nextInt();
        } while (!(x >= 0 && x <= 10)); // do while loop uses "not" conditional so that the condition to succeed is more clear

        do {
            System.out.println("What is the y-coordinate of the square you wish to fire a missle at?");
            y = scanNum.nextInt();
        } while (!(y >= 0 && y <= 10)); // do while loop uses "not" conditional so that the condition to succeed is more clear

        shotCheck(x, y, board, conjBoard, s1, s2, s3, s4, s5);
    }
}

/* To Do: Debug placing ship on top of each other */ 