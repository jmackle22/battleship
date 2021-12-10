import java.util.Scanner;

public class Game {
    private static Scanner sc = new Scanner(System.in);
    private static String turnPlayer;
    private String playerOne;
    private String playerTwo;
    private Board boardOne;
    private Board boardTwo;
    private int turn;

    // init game
    public Game() {
        System.out.println("Welcome to Battleship!");

        // get players names
        System.out.println("Please Enter Your Name (player 1):");
        this.playerOne = sc.nextLine();
        System.out.println("Please Enter Your Name (player 2):");
        this.playerTwo = sc.nextLine();

        // randomize turn and create boards (player 1 and 2)
        this.turn = (int) (Math.random() * 2 + 1);
        this.boardOne = new Board();
        this.boardTwo = new Board();

        // generate ships on each board and update ship units
        this.boardOne.generateShips();
        this.boardTwo.generateShips();
        this.boardOne.getShipUnits();
        this.boardTwo.getShipUnits();
    }

    // game loop
    public void play() {
        // todo: while loop (until either player's totalShipUnits == 0)
        while (boardOne.getShipUnits() > 0 && boardTwo.getShipUnits() > 0) {
            if (this.turn == 1) {
                this.turnPlayer = playerOne;
                takeTurnAgainst(boardTwo);
            } else if (this.turn == 2) {
                this.turnPlayer = playerTwo;
                takeTurnAgainst(boardOne);
            }
            if (this.turn == 1) {this.turn = 2;} else if (this.turn == 2) {this.turn = 1;}
        }

        System.out.println("Game is over" +
                "\n  " + playerOne + "'s total ship units: " + boardOne.getShipUnits() +
                "\n  " + playerTwo + "'s total ship units: " + boardTwo.getShipUnits());

        if (boardOne.getShipUnits() == 0) declareWinner(playerOne);
        if (boardTwo.getShipUnits() == 0) declareWinner(playerTwo);
    }

    // function
    private static void takeTurnAgainst(Board attackOn) {
        // keep in mind that 'attackOn' is the board that is being attacked
        System.out.println("\nIt is " + turnPlayer + "'s turn.");

        // take input and get vars
        System.out.println("Enter Trajectory Of Missile!:");
        String line = sc.nextLine();
        char c = line.charAt(0);
        int n = Character.getNumericValue(line.charAt(1));

        // shoot missile
        if ((attackOn.cTest(c, n) == null)) {
            System.out.println("ERROR: Invalid Trajectory");
            takeTurnAgainst(attackOn);
        } else if (attackOn.cTest(c, n).equals("■")) { // HIT
            attackOn.editBoard(((int)Character.toLowerCase(c) - 97), n, "\uD83D\uDCA5");
            attackOn.displayBoard(true);
            attackOn.editBoard(((int)Character.toLowerCase(c) - 97), n, "▣");
            System.out.println("X : Hit ship!!!! at " + Character.toUpperCase(c) + n + "\n  Take Another Turn...");
            takeTurnAgainst(attackOn);
        } else if (attackOn.cTest(c, n).equals("O")) { // MISS
            attackOn.editBoard(((int)Character.toLowerCase(c) - 97), n, "\uD83D\uDC04");
            attackOn.displayBoard(true);
            attackOn.editBoard(((int)Character.toLowerCase(c) - 97), n, "O");
            //nice
            System.out.println("O: Miss.... At " + Character.toUpperCase(c) + n);
            attackOn.getShipUnits();
        } else {
            // i think this is when the trajectory is already a hit ship, sounds good bro thats awesome dude sounds awesome bro thats incredible hahahahahaha lol awesome bro
            System.out.println("ERROR: Invalid Trajectory (Ship has already been hit!)");
            takeTurnAgainst(attackOn);
        }
    }

    private static void declareWinner(String winner) {
        // pretty winner statement (useless but fun string manipulation stuff)
        String winnerStatement = "\uD83C\uDFC6 The winner is " + winner + "! \uD83C\uDFC6";
        String line = new String(new char[winnerStatement.length()]).replace('\0', '≈');
        System.out.println("\n" + line);
        System.out.println(winnerStatement);
        System.out.println(line + "\n");
    }
}




