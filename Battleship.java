public class Battleship {
    public static void main(String[] args) {
        int numRows = 10;
        int numCols = 10;
        int[] ships = new int[5];

        int[][] game = new int[numRows][numCols];

        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                System.out.print(game[r][c] + " ");
            }
            System.out.print("\n");
        }
    }
}