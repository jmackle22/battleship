import java.util.Scanner;
import java.io.IOException;

public class Game {
    private static Scanner sc = new Scanner(System.in);
    private static String turnPlayer;
    private String playerOne;
    private String playerTwo;
    private Board boardOne;
    private Board boardTwo;
    private int turn;
    private static int turnCounter;

    // init game
    public Game() {
        System.out.println("Welcome to Battleship!");

        // get players names
        System.out.println("Please enter your name (player 1):");
        this.playerOne = sc.nextLine();
        System.out.println("Please enter your name (player 2):");
        this.playerTwo = sc.nextLine();

        // randomize turn and create boards (player 1 and 2)
        this.turn = (int) (Math.random() * 2 + 1);
        if (this.turn == 1) {this.turnPlayer = playerOne;} else if (this.turn == 2) {this.turnPlayer = playerTwo;}
        this.turnCounter = 0;
        this.boardOne = new Board();
        this.boardTwo = new Board();

        // generate ships on each board and update ship units
        this.boardOne.generateShips(new int[]{2, 3, 3, 4, 5});
        this.boardTwo.generateShips(new int[]{2, 3, 3, 4, 5});
        this.boardOne.getShipUnits();
        this.boardTwo.getShipUnits();

        // show players their boards
        //seeBoards();
    }

    // game loop
    public void play() {
        // todo: all that is left is ending the game correctly (display winner and total turns)
        while (boardOne.getShipUnits() > 0 || boardTwo.getShipUnits() > 0) {
            if (this.turn == 1) {
                this.turnPlayer = playerOne;
                takeTurn(boardOne, boardTwo, playerTwo);
            } else if (this.turn == 2) {
                this.turnPlayer = playerTwo;
                takeTurn(boardTwo, boardOne, playerOne);
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
    private void takeTurn(Board playerBoard, Board opposingBoard, String opponentName) {
        System.out.println("It is " + turnPlayer + "'s turn.\n  Press enter to see your own board.");
        waitForEnter();
        playerBoard.displayBoard(false);
        System.out.println("Press enter...");
        waitForEnter();
        System.out.println("Attack " + opponentName + "'s board:");
        opposingBoard.displayBoard(true);
        attack(opposingBoard);
        System.out.println("Press enter, then give the computer to " + opponentName);
        waitForEnter();
        clearScreen();
    }

    private static void attack(Board attackOn) {
        // keep in mind that 'attackOn' is the board that is being attacked

        // take input and get vars
        boolean invalidTrajectory = true;
        char c = '0';
        int n = -1;
        while (invalidTrajectory) {
            System.out.println("Enter trajectory of missile:");
            String line = sc.nextLine();
            if (!(line.length() > 1)) continue;
            c = line.charAt(0);
            n = Character.getNumericValue(line.charAt(1));

            // check character is in the range of a through j
            if (((int) Character.toLowerCase(c) < 97) || ((int) Character.toLowerCase(c) > 106)) {
                System.out.println("ERROR: Invalid trajectory (char is not in the range of A through J)");
            } else {
                invalidTrajectory = false;
            }
        }

        // shoot missile
        if ((attackOn.cTest(c, n) == null)) { // ERR
            System.out.println("ERROR: Invalid trajectory");
            attack(attackOn);
        } else if (attackOn.cTest(c, n).equals("◆")) { // ERR
            System.out.println("ERROR: Invalid trajectory (Ship has already been hit!)");
            attack(attackOn);
        } else if (attackOn.cTest(c, n).equals("□")) { // ERR
            System.out.println("ERROR: Invalid trajectory (You already tried this spot)");
            attack(attackOn);
        } else if (attackOn.cTest(c, n).equals("■")) { // HIT
            // edit board, differentiate hitmarker from previously hit ship
            attackOn.editBoard(((int) Character.toLowerCase(c) - 97), n, "●");
            attackOn.displayBoard(true);
            attackOn.editBoard(((int) Character.toLowerCase(c) - 97), n, "◆");

            // determine whether a ship was only hit or if it was sunk
            String hitOrSunk = "";
            if (attackOn.checkForShip(((int) Character.toLowerCase(c) - 97), n)) hitOrSunk = "Hit a"; else hitOrSunk = "SUNK the";

            // message
            System.out.println("●: " + hitOrSunk + " ship!!!! at " + Character.toUpperCase(c) + n + ".  Take another turn, " + turnPlayer);
            attack(attackOn);
        } else if (attackOn.cTest(c, n).equals("-")) { // MISS
            attackOn.editBoard(((int) Character.toLowerCase(c) - 97), n, "\uD83D\uDC04");
            attackOn.displayBoard(true);
            attackOn.editBoard(((int) Character.toLowerCase(c) - 97), n, "□");
            System.out.println("□: Miss.... At " + Character.toUpperCase(c) + n);
            attackOn.getShipUnits();
        }

        // increment turnCounter
        turnCounter++;
    }

    private void seeBoards() {
        clearScreen();
        System.out.println("Press enter to see your board, " + playerOne + ".");
        waitForEnter();
        boardOne.displayBoard(false);
        System.out.println("Press enter when ready");
        waitForEnter();

        clearScreen();
        System.out.println("Press enter to see your board, " + playerTwo + ".");
        waitForEnter();
        boardTwo.displayBoard(false);
        System.out.println("Press enter when ready");
        waitForEnter();
        clearScreen();
    }

    private void waitForEnter() {
        try {System.in.read();} catch (IOException e) {e.printStackTrace();}
    }

    public static void declareWinner(String winner) {
        // pretty winner statement (useless but fun string manipulation stuff)
        String winnerStatement = "\uD83C\uDFC6 The winner is " + winner + "! \uD83C\uDFC6";
        String line = new String(new char[winnerStatement.length()]).replace('\0', '≈');
        System.out.println("\n" + line);
        System.out.println(winnerStatement);
        System.out.println(line + "\n");
    }

    public static void clearScreen() {
        for (int i = 0; i < 30; i++) {
            System.out.print("\n");
        }
    }
}




