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
        
        for (int a = 1; a <= 10; a++) {
            if (a < 10) {
                System.out.print(a + "  ");
            } else {
                System.out.print(a + " ");
            }

            for (int b = 0; b < 10; b++) {
                System.out.print(indicators[a-1][b] + " ");
            }

            System.out.println("");
        }
    }

    public void setIndicator (int x, int y, String ind) {
        indicators[x-1][y-1] = ind;
    }
}