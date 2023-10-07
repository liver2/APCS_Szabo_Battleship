public class Board {
    String[][] indicators;
    int s;
    
    public Board(int side) {
        s = side;

        indicators = new String[s][s];

        for (int c = 0; c < s; c++) {
            for (int d = 0; d < s; d++) {
                indicators[c][d] = ".";
            }
        }
    }

    public int getSideLength() {
        return s;
    }

    public void printBoard () {
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        
        for (int b = 1; b <= s; b++) {
            if (b < s) {
                System.out.print(b + "  ");
            } else {
                System.out.print(b + " ");
            }

            for (int a = 0; a < s; a++) {
                System.out.print(indicators[a][b-1] + " ");
            }

            System.out.println("");
        }

        System.out.println("");
    }

    public void setIndicator (int x, int y, String ind) {
        indicators[x-1][y-1] = ind;
    }

    public String getIndicator (int x, int y) {
        return indicators[x-1][y-1];
    }
}