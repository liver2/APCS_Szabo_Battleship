import java.util.Scanner;

public class Battleship {
    static Scanner scanNum = new Scanner(System.in);
    static Scanner scanString = new Scanner(System.in);
    public static void main(String[] args) {
        String s1;

        System.out.println("--- Battleship ---");
        System.out.println("Welcome to Battleship!");
        System.out.println("For this version of Battleship, you can choose 2 modes: Fast and Regular.");
        System.out.println("Let me explain the rules.\n"); // Places new line
        System.out.println("In Regular mode, you and a friend will each place 5 ships on a 10x10 board.");
        System.out.println("The ships will be lengths 2, 3, 3, 4, and 5.")
        System.out.println("Ships cannot be placed diagonally; only horizontally and vertically. They cannot extend off the board.");
        System.out.println("Then, you and your friend will take turns guessing the positions of each others' ships.");
        System.out.println("You will know when you hit a ship, and you will know when a complete ship has been sunk.");
        System.out.println("If you can sink your friend's 5 ships, you win! Vice versa; whoever gets all five ships sunk first loses.\n");
        System.out.println("In Fast mode, a computer will randomize the placement of 3 ships on an 8x8 board.");
        System.out.println("The ships will be lengths 2, 3, and 4.");
        System.out.println("You will guess where the computer's ships are."); 
        System.out.println("You will know when you hit a ship, and you will know when a complete ship has been sunk.");
        System.out.println("When you sink all the ships, you will learn how many turns it took you; lower is better.\n");
        do {
            System.out.println("So, tell me; what mode would you like to play today? Fast (input f, then enter) or Normal (input n, then enter)?");
            s1 = scanString.nextLine();
            if (!(s1.equals("n") || s1.equals("f"))) {
                System.out.println("Please input n or f.");
            }
        } while (!s1.equals("n") && !s1.equals("f"));

        if (s1.equals("n")) {
            /* Implementation not done yet: normal game */
        } else if (s1.equals("f")) {
            /* Implementation not done yet: fast game */
        }
    }
}