public class Ship {
    int length;
    int[][] position; // x position, y position

    public Ship (int len, int posStartX, int posStartY, String orientation) {
        length = len;
        position = new int[length][2];
        position[0][0] = posStartX;
        position[0][1] = posStartY;
        for (int i = 1; i < length; i++) {
            position[i][0] = posStartX + i*(Boolean.compare(orientation == "WEST", true)) - i*(Boolean.compare(orientation == "EAST", true));
            position[i][1] = posStartY + i*(Boolean.compare(orientation == "SOUTH", true)) - i*(Boolean.compare(orientation == "NORTH", true));
        }
    }
}
