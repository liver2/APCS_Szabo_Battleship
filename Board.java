public class Board { // the Board class is a computer representation of a board upon which ships are placed.
    String[][] indicators;
    int s;
    
    public Board(int side) { // constructs the board and specifies the side length
        s = side; // sets s variable to the side

        indicators = new String[s][s]; // creates indicators' size

        for (int c = 0; c < s; c++) {
            for (int d = 0; d < s; d++) {
                indicators[c][d] = "."; // sets all indicators to "." which is the default indicator
            }
        }
    }

    public int getSideLength() { // easy getter that returns side length
        return s;
    }

    public void printBoard () { // prints the board out--this one is used a lot!
        System.out.print("   "); // space in front to line everything up

        for (int i = 1; i <= s; i++) {
            System.out.print(i + " "); // number guidelines according to side
        }

        System.out.println("");
        
        for (int b = 1; b <= s; b++) {
            if (b < 10) {
                System.out.print(b + "  "); 
            } else {
                System.out.print(b + " "); // lining stuff up won't work for 100...
            }

            for (int a = 0; a < s; a++) {
                System.out.print(indicators[a][b-1] + " ");
            }

            System.out.println("");
        }

        System.out.println("");
    }

    public void setIndicator (int x, int y, String ind) { // sets indicator according to arguments
        indicators[x-1][y-1] = ind;
    }

    public String getIndicator (int x, int y) { // returns indicator according to arguments
        return indicators[x-1][y-1];
    }
}