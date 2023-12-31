/**
 * Ship.java
 * 
 * @author Oliver Szabo
 * @since 10/19/2023
 * 
 * This class emulates the ships which the players physically place and guess the positions.
 * The class contains a method to set the Ship Parameters, which are not initialized,
 * and contains basic methods to return its parameters,
 * as well as a method to detect where a successful guess hits the ship.
 */

import java.util.Scanner;

public class Ship { 
    int length; // ship length
    int[][] position; // x position, y position, status
    boolean sunk = false; // sunk status

    public void setShipParams (int len, int posStartX, int posStartY, String orientation) { // Sets the ship parameters, because a constructor is a bit hard to work into a user input method
        length = len;
        position = new int[length][3];
        position[0][0] = posStartX;
        position[0][1] = posStartY;
        for (int i = 0; i < length; i++) {
            position[i][0] = posStartX - i*(Boolean.compare(orientation.equals("w"), true)) + i*(Boolean.compare(orientation.equals("e"), true));
            position[i][1] = posStartY + i*(Boolean.compare(orientation.equals("s"), true)) - i*(Boolean.compare(orientation.equals("n"), true));
            position[i][2] = 1; // 1 means not sunk. 0 means sunk.
        }
    }

    // checks where the shot hit
    public void shot (int x, int y) { 
        int counter = 0; // counter: if the ship is still alive the counter != 0 and thus, ship is still alive (true)

        for (int k = 0; k < length; k++) {
            if (position[k][0] == x && position[k][1] == y) {
                position[k][2] = 0;
            }
        }

        for (int l = 0; l < length; l++) {
            counter += position[l][2];
        }

        if (counter == 0) {
            sunk = true;
        }
    }

    // gets position from 2d array acc. to arguments
    public int getPosition(int x, int y) { 
        return position[x][y];
    }

    // returns length of the ship
    public int getLength() { 
        return length;
    }

    // returns status of the ship. if the ship is sunk "true" and if not "false"
    public boolean getSunk() { 
        return sunk;
    }
}