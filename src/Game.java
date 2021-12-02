import java.util.Scanner;

public class Game {
    private static Scanner sc = new Scanner(System.in);

    // game loop, functions
    public Game() {
        // todo: init game things (look at nim for inspiration)
    }

    public void gameLoop(Board playerOne, Board playerTwo) {
        // todo: while loop (until either player's totalShipUnits == 0)
    }

    public static void takeTurnAgainst(Board attackOn) {
        // keep in mind that 'attackOn' is the board that is being attacked

        // take input and get vars
        System.out.println("Enter trajectory of missile:");
        String line = sc.nextLine();
        char c = line.charAt(0);
        int n = Character.getNumericValue(line.charAt(1));

        // shoot missile
        if ((attackOn.cTest(c, n) == null)) {
            System.out.println("ERROR: invalid trajectory");
            takeTurnAgainst(attackOn);
        } else if (attackOn.cTest(c, n).equals("■")) {
            attackOn.editBoard(((int)Character.toLowerCase(c) - 97), n, "\uD83D\uDCA5");
            attackOn.displayBoard(true);
            attackOn.editBoard(((int)Character.toLowerCase(c) - 97), n, "▣");
            System.out.println("X : Hit ship!!!! at " + Character.toUpperCase(c) + n + "\n  take another turn");
            takeTurnAgainst(attackOn);
        } else if (attackOn.cTest(c, n).equals("O")) {
            attackOn.editBoard(((int)Character.toLowerCase(c) - 97), n, "\uD83D\uDC04");
            attackOn.displayBoard(true);
            attackOn.editBoard(((int)Character.toLowerCase(c) - 97), n, "O");
            System.out.println("O: miss.... at " + Character.toUpperCase(c) + n);
        } else {
            // i think this is when the trajectory is already a hit ship
            takeTurnAgainst(attackOn);
        }
    }
}
