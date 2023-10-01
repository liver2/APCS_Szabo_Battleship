import java.util.Scanner;

public class Battleship {
    static Board pBoard = new Board(); // Player Board
    static Board cBoard = new Board(); // Computer Board

    public static void main(String[] args) {
        System.out.println("--- Battleship ---");

        System.out.println("Welcome to Battleship!");
        System.out.println("First, place your ships.");
        System.out.println("Please see a map of the board below:\n");

        pBoard.printBoard();

        System.out.println("As you may know, you will have to place five ships.");
        System.out.println("These five ships have lengths 2, 3, 3, 4, and 5.");
        System.out.println("Let's begin placing ships.\n");

        promptShipPlacement(2);
        promptShipPlacement(3);
        promptShipPlacement(3);
        promptShipPlacement(4);
        promptShipPlacement(5);
    }
}