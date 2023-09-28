public class Board {
    int numRows;
    int numCols;

    public Board(int rows, int columns) {
        numRows = rows;
        numCols = columns;
    }

    public Board(){
        numRows = 10;
        numCols = 10;
    }

    public void printBoard() {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                System.out.print(game[r][c] + " ");
            }
            System.out.print("\n");
        }
    }
}