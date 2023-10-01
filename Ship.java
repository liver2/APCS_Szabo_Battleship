public class Ship {
    int length;
    int[][] position; // x position, y position, status
    boolean sunk = false;

    public Ship (int len, int posStartX, int posStartY, String orientation) {
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

    public void promptShipPlacement(int len) {
        System.out.println("Please ")
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