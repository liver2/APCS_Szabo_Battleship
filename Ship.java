import java.util.Scanner;

public class Ship {
    int length;
    int[][] position; // x position, y position, status
    boolean sunk = false;

    public void setShipParams (int len, int posStartX, int posStartY, String orientation) {
        length = len;
        position = new int[length][3];
        position[0][0] = posStartX;
        position[0][1] = posStartY;
        for (int i = 0; i < length; i++) {
            position[i][0] = posStartX + i*(Boolean.compare(orientation == "WEST", true)) - i*(Boolean.compare(orientation == "EAST", true));
            position[i][1] = posStartY + i*(Boolean.compare(orientation == "SOUTH", true)) - i*(Boolean.compare(orientation == "NORTH", true));
            position[i][2] = 1; // 1 means not sunk. 0 means sunk.
        }
    }

    public static void promptShipPlacement(int len, Board board) {
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

        setShipParams(len, x, y, orientation);

        for (int i = 0; i < len; i++) {
            board.setIndicator(); // this works??????
        }
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

    public void shot (int x, int y) {
        int counter = 0;

        for (int k = 0; k < length; k++) {
            if (position[k][0] == x && position[k][1] == y) {
                position[k][2] = 0;
            }
        }

        for (int l = 0; l < length; l++) {
            counter += position[l][2];
        }

        if (counter == length) {
            sunk = true;
        }
    }

    public int getPosition(int x, int y) {
        return position[x][y];
    }

    public int getLength() {
        return length;
    }

    public int getStatus(int x, int y) {
        for (int m = 0; m < length; m++) {
            if (position[m][0] == x && position[m][1] == y) {
                return position[m][2];
            }
        }
        return -1; // no ships match up with called arguments
    }

    public boolean getSunk() {
        return sunk;
    }
}