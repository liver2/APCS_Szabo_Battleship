public class Board {
    String[][] indicators = new String[10][10];

    public Board() {
        for (int c = 0; c < 10; c++) {
            for (int d = 0; d < 10; d++) {
                indicators[c][d] = ".";
            }
        }
    }

    public void printBoard () {
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        
        for (int b = 1; b <= 10; b++) {
            if (b < 10) {
                System.out.print(b + "  ");
            } else {
                System.out.print(b + " ");
            }

            for (int a = 0; a < 10; a++) {
                System.out.print(indicators[a][b-1] + " ");
            }

            System.out.println("");
        }

        System.out.println("");
    }

    public void setIndicator (int x, int y, String ind) {
        indicators[x-1][y-1] = ind;
    }
}