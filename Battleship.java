public class Battleship {
    public static void main(String[] args) {
        Board pBoard = new Board(); // Player Board
        Board cBoard = new Board(); // Computer Board

        System.out.println("--- Battleship ---");

        System.out.println("Welcome to Battleship!");
        System.out.println("First, place your ships.");
        System.out.println("Please see a map of the board below:\n");

        pBoard.printBoard();

        System.out.println("hi");
    }
}