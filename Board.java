public class Board {
    public Board(int side) {
        char[][] ind = new char[side][side];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                ind[i][j] = '.';
            }
        }
    }
}