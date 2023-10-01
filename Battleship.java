import java.util.Scanner;

public class Battleship {
    static Board pBoard = new Board(); // Player Board
    static Board cBoard = new Board(); // Computer Board

    static Ship p2 = new Ship();
    static Ship p3a = new Ship();
    static Ship p3b = new Ship();
    static Ship p4 = new Ship();
    static Ship p5 = new Ship();

    static Ship c2 = new Ship();
    static Ship c3a = new Ship();
    static Ship c3b = new Ship();
    static Ship c4 = new Ship();
    static Ship c5 = new Ship();

    public static void promptShipPlacement(int len, Board board, Ship ship, String ind) {
        Scanner scan = new Scanner(System.in);
        int x;
        int y;
        String orientation;

        System.out.println("Please specify an X coordinate for your ship with length " + len + ".");
        do {
            x = scan.nextInt();
        } while (!(x >= 0 && x <= 10));

        System.out.println("Please specify a Y coordinate for your ship with length " + len + ".");
        do {
            y = scan.nextInt();
        } while (!(y >= 0 && y <= 10));

        System.out.println("Please specify an orientation for your ship with length " + len + ".");
        System.out.println("Specify north with n, south with s, east with e, and west with w.");
        do {
            scan.nextLine(); // To clear the new line created after scanning an int
            orientation = scan.nextLine();
            /* For debugging: */ System.out.println(shipPlacementCheck(x, y, len, orientation));
        } while (!(orientation == "n" || orientation == "s" || orientation == "e" || orientation == "w") 
                 || 
                 !(shipPlacementCheck(x, y, len, orientation) == true));

        System.out.println("Let's see your ship on the board...\n");

        ship.setShipParams(len, x, y, orientation);

        for (int i = 0; i < len; i++) {
            board.setIndicator(ship.getPosition(i,0), ship.getPosition(i,1), ind); // this works??????
        }

        board.printBoard();
    }

    public static boolean shipPlacementCheck(int x, int y, int len, String orientation) {
        int[][] pos = new int[len][3];
        pos[0][0] = x;
        pos[0][1] = y;
        for (int i = 0; i < len; i++) {
            pos[i][0] = x + i*(Boolean.compare(orientation == "w", true)) - i*(Boolean.compare(orientation == "e", true));
            pos[i][1] = y + i*(Boolean.compare(orientation == "s", true)) - i*(Boolean.compare(orientation == "n", true));
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 1; j++) {
                if (pos[i][j] > 10 || pos[i][j] < 0) {
                    return false;
                }
            }
        }

        return true;
    }
    public static void main(String[] args) {
        System.out.println("--- Battleship ---");

        System.out.println("Welcome to Battleship!");
        System.out.println("First, place your ships.");
        System.out.println("Please see a map of the board below:\n");

        pBoard.printBoard();

        System.out.println("As you may know, you will have to place five ships.");
        System.out.println("These five ships have lengths 2, 3, 3, 4, and 5.");
        System.out.println("You'll see them on the map as a, b, c, d, and e.");
        System.out.println("Let's begin placing ships.\n");

        promptShipPlacement(2, pBoard, p2, "a");
        promptShipPlacement(3, pBoard, p3a, "b");
        promptShipPlacement(3, pBoard, p3b, "c");
        promptShipPlacement(4, pBoard, p4, "d");
        promptShipPlacement(5, pBoard, p5, "e");
    }
}