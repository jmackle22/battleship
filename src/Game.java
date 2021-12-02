import java.util.Scanner;

public class Game {
    private static Scanner sc = new Scanner(System.in);

    // game loop, functions

    public static void takeTurn(Board attackOn) {
        // keep in mind that 'attackOn' is the board that is being attacked

        // take input and get vars
        System.out.println("Enter trajectory of missile:");
        String line = sc.nextLine();
        char c = line.charAt(0);
        int n = Character.getNumericValue(line.charAt(1));

        if (attackOn.cTest(c, n).equals("■")) {
            attackOn.editBoard(((int)Character.toLowerCase(c) - 97), n, "\uD83D\uDCA5");
            attackOn.displayBoard(true);
            attackOn.editBoard(((int)Character.toLowerCase(c) - 97), n, "▣");
            System.out.println("X : Hit ship!!!! at " + Character.toUpperCase(c) + n + "\n  take another turn");
            takeTurn(attackOn);
        } else if (attackOn.cTest(c, n).equals("O")) {
            attackOn.editBoard(((int)Character.toLowerCase(c) - 97), n, "\uD83D\uDC04");
            attackOn.displayBoard(true);
            attackOn.editBoard(((int)Character.toLowerCase(c) - 97), n, "O");
            System.out.println("O: miss.... at " + Character.toUpperCase(c) + n);

        } else {
            takeTurn(attackOn);
        }
    }
}
