import java.util.Scanner;

public class Battleship {
    static Scanner scanNum = new Scanner(System.in);
    static Scanner scanString = new Scanner(System.in);

    public static void normalGame() {
        Board p1Board = new Board(10);
        Board p1Guess = new Board(10);
        Board p2Board = new Board(10);
        Board p2Guess = new Board(10);

        Ship p1l2 = new Ship();
        Ship p1l3a = new Ship();
        Ship p1l3b = new Ship();
        Ship p1l4 = new Ship();
        Ship p1l5 = new Ship();

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

        // Guessing phase
    }

    public static void fastGame() {
        Board aiBoard = new Board(8);
        Board pGuess = new Board(8);
    }

    public static void promptShipPlacement(int len, Board board, Ship ship, String ind) { // Method that goes through the ship placement process
        Scanner scan = new Scanner(System.in); // scanner for numbers
        Scanner strScan = new Scanner(System.in); // scanner for strings (one scanner didn't work properly)
        int x; // for ship args
        int y; // for ship args
        String orientation; // to set the parameters of the ship, we should declare a local variable specific to the function

        do {
            System.out.println("Please specify an X coordinate for your ship with length " + len + ".");
            x = scan.nextInt();
        } while (!(x >= 0 && x <= 10)); // do while loop uses "not" conditional so that the condition to succeed is more clear

        do {
            System.out.println("Please specify a Y coordinate for your ship with length " + len + ".");
            y = scan.nextInt();
        } while (!(y >= 0 && y <= 10));

        do {
            System.out.println("Please specify an orientation for your ship with length " + len + ".");
            System.out.println("Specify north with n, south with s, east with e, and west with w."); // hopefully this is clear enough..
            orientation = strScan.nextLine();
        } while (!(orientation.equals("n") || orientation.equals("s") || orientation.equals("e") || orientation.equals("w"))
                 || // ln. 40: if orientation satisfies the specified directions, move to the next check.
                 !(shipPlacementCheck(x, y, len, orientation) == true)); // checks if ship goes off the board

        System.out.println("Let's see your ship on the board...\n");

        ship.setShipParams(len, x, y, orientation); // sets the parameters of the ship according to the specified inputs

        for (int i = 0; i < len; i++) {
            board.setIndicator(ship.getPosition(i,0), ship.getPosition(i,1), ind); // setting each indicator on the board
        } 

        board.printBoard(); // shows the board
    }

    public static boolean shipPlacementCheck(int x, int y, int len, String orientation) {
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

        return true;
    }
    
    public static void shotCheck(int x, int y, Board board, Ship s1, Ship s2, Ship s3, Ship s4, Ship s5) {
        if (board.getIndicator(x,y).equals("a") || board.getIndicator(x,y).equals("b") 
         || board.getIndicator(x,y).equals("c") || board.getIndicator(x,y).equals("d") || board.getIndicator(x,y).equals("e")) {
            s1.shot(x,y);
            s2.shot(x,y);
            s3.shot(x,y);
            s4.shot(x,y);
            s5.shot(x,y); // EFFICIENCY
        }
    }
    
    public static void main(String[] args) {
        String s1;

        System.out.println("--- Battleship ---");
        System.out.println("Welcome to Battleship!");
        System.out.println("For this version of Battleship, you can choose 2 modes: Fast and Regular.");
        System.out.println("Let me explain the rules.\n"); // Places new line
        System.out.println("In Regular mode, you and a friend will each place 5 ships on a 10x10 board.");
        System.out.println("The ships will be lengths 2, 3, 3, 4, and 5.");
        System.out.println("Ships cannot be placed diagonally; only horizontally and vertically. They cannot extend off the board.");
        System.out.println("Then, you and your friend will take turns guessing the positions of each others' ships.");
        System.out.println("You will know when you hit a ship, and you will know when a complete ship has been sunk.");
        System.out.println("If you can sink your friend's 5 ships, you win! Vice versa; whoever gets all five ships sunk first loses.\n");
        System.out.println("In Fast mode, a computer will randomize the placement of 3 ships on an 8x8 board.");
        System.out.println("The ships will be lengths 2, 3, and 4.");
        System.out.println("You will guess where the computer's ships are."); 
        System.out.println("You will know when you hit a ship, and you will know when a complete ship has been sunk.");
        System.out.println("When you sink all the ships, you will learn how many turns it took you; lower is better.\n");
        do {
            System.out.println("So, tell me; what mode would you like to play today? Fast (input f, then enter) or Normal (input n, then enter)?");
            s1 = scanString.nextLine();
            if (!(s1.equals("n") || s1.equals("f"))) {
                System.out.println("Please input n or f.");
            }
        } while (!s1.equals("n") && !s1.equals("f"));

        if (s1.equals("n")) {
            /* Implementation not done yet: normal game */
        } else if (s1.equals("f")) {
            /* Implementation not done yet: fast game */
        }
    }
}