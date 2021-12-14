import java.util.Scanner;
import java.io.IOException;

public class Game {
    private static Scanner sc = new Scanner(System.in);
    private static int[] ships = {2, 3, 3, 4, 5}; // CONFIG: sizes of randomly generated ships for each player
    private String turnPlayer;
    private String playerOne;
    private String playerTwo;
    private Board boardOne;
    private Board boardTwo;
    private int turn;
    private int turnCounter;
    private boolean gameOver;
    private boolean streak;
    private int plrOneMisses;
    private int plrTwoMisses;
    private int plrOneHits;
    private int plrTwoHits;
    private int plrOneStreaks;
    private int plrTwoStreaks;

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
        this.streak = false;
        this.boardOne = new Board();
        this.boardTwo = new Board();
        this.gameOver = false;

        // generate ships on each board and update ship units
        this.boardOne.generateShips(ships);
        this.boardTwo.generateShips(ships);
        this.boardOne.getShipUnits();
        this.boardTwo.getShipUnits();

        // reset stats
        this.plrOneMisses = 0;
        this.plrTwoMisses = 0;
        this.plrOneHits = 0;
        this.plrTwoHits = 0;
        this.plrOneStreaks = 0;
        this.plrTwoStreaks = 0;
    }

    // game loop
    public void play() {
        while (boardOne.getShipUnits() > 0 && boardTwo.getShipUnits() > 0) {
            if (this.turn == 1) {
                this.turnPlayer = playerOne;
                takeTurn(boardOne, boardTwo, playerTwo);
                boardOne.getShipUnits(); // update ship units
            } else if (this.turn == 2) {
                this.turnPlayer = playerTwo;
                takeTurn(boardTwo, boardOne, playerOne);
                boardOne.getShipUnits(); // update ship units
            }
            // increment turnCounter
            this.turnCounter++;
            // switch turn
            if (this.turn == 1) {this.turn = 2;} else if (this.turn == 2) {this.turn = 1;}
        }

        clearScreen();

        System.out.println("Game is over in " + turnCounter +  " turns. Statistics:" +
                "\n  " + playerOne + ":" +
                "\n    ship units: " + boardOne.getShipUnits() +
                "\n          hits: " + plrOneHits +
                "\n        misses: " + plrOneMisses +
                "\n       streaks: " + plrOneStreaks +
                "\n  " + playerTwo + ":" +
                "\n    ship units: " + boardTwo.getShipUnits() +
                "\n          hits: " + plrTwoHits +
                "\n        misses: " + plrTwoMisses +
                "\n       streaks: " + plrTwoStreaks);

        if (boardOne.getShipUnits() == 0) declareWinner(playerTwo);
        if (boardTwo.getShipUnits() == 0) declareWinner(playerOne);
    }

    // take turns
    private void takeTurn(Board playerBoard, Board opposingBoard, String opponentName) {
        // turn loop (looped through in the play() method)
        System.out.println("It is " + this.turnPlayer + "'s turn. You have " + playerBoard.getShipUnits() + " ship units left. \n  Press enter to see your own board.");
        waitForEnter();
        playerBoard.displayBoard(false, "Your");
        System.out.println("Press enter...");
        waitForEnter();
        System.out.println("Attack " + opponentName + "'s board:");
        opposingBoard.displayBoard(true, opponentName + "'s");
        attack(opposingBoard, opponentName);
        if (this.gameOver) return; // exit method if game is over
        System.out.println("Press enter, then give the computer to " + opponentName);
        waitForEnter();
        clearScreen();
    }

    // main attack method
    private void attack(Board attackOn, String opponentName) {
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
            attack(attackOn, opponentName);
        } else if (attackOn.cTest(c, n).equals(Board.hitShip)) { // ERR
            System.out.println("ERROR: Invalid trajectory (Ship has already been hit!)");
            attack(attackOn, opponentName);
        } else if (attackOn.cTest(c, n).equals(Board.miss)) { // ERR
            System.out.println("ERROR: Invalid trajectory (You already tried this spot)");
            attack(attackOn, opponentName);
        } else if (attackOn.cTest(c, n).equals(Board.ship)) { // HIT
            // update hit stat
            if (this.turn == 1) this.plrOneHits++; else if (this.turn == 2) this.plrTwoHits++;

            // if streak = true, up the value
            if (this.streak) {
                if (this.turn == 1) this.plrOneStreaks++; else if (this.turn == 2) this.plrTwoStreaks++;
            }
            this.streak = true; // set streak to true in case the player makes another hit

            // edit board, differentiate hit marker from previously hit ship
            attackOn.editBoard(((int) Character.toLowerCase(c) - 97), n, Board.newHit);
            attackOn.displayBoard(true, opponentName + "'s");
            attackOn.editBoard(((int) Character.toLowerCase(c) - 97), n, Board.hitShip);

            // determine whether a ship was only hit or if it was sunk
            String hitOrSunk = "";
            if (attackOn.checkForShip(((int) Character.toLowerCase(c) - 97), n)) {hitOrSunk = "Hit a";} else {hitOrSunk = "SUNK the";}

            // determine if game is over and exit method if so
            if (attackOn.getShipUnits() == 0) {
                System.out.println("You sunk their last ship!\n");
                this.gameOver = true;
                return;
            }
            // message
            System.out.println(Board.newHit + ": " + hitOrSunk + " ship!!!! at " + Character.toUpperCase(c) + n + ".  Take another turn, " + this.turnPlayer);
            attack(attackOn, opponentName);

        } else if (attackOn.cTest(c, n).equals(Board.empty)) { // MISS
            // update miss stat
            if (this.turn == 1) this.plrOneMisses++; else if (this.turn == 2) this.plrTwoMisses++;

            // reset streak
            this.streak = false;

            attackOn.editBoard(((int) Character.toLowerCase(c) - 97), n, Board.newMiss);
            attackOn.displayBoard(true, opponentName + "'s");
            attackOn.editBoard(((int) Character.toLowerCase(c) - 97), n, Board.miss);
            System.out.println(Board.newMiss + ": Miss.... At " + Character.toUpperCase(c) + n);
            attackOn.getShipUnits();
        }
    }

    // waits for the user to press enter
    private void waitForEnter() {try {System.in.read();} catch (IOException e) {e.printStackTrace();}}

    // fancy print statement that declares winner
    public static void declareWinner(String winner) {
        // pretty winner statement (useless but fun string manipulation stuff)
        String winnerStatement = "\uD83C\uDFC6 The winner is " + winner + "! \uD83C\uDFC6";
        String line = new String(new char[winnerStatement.length()]).replace('\0', '≈');
        System.out.println("\n" + line);
        System.out.println(winnerStatement);
        System.out.println(line + "\n");
    }

    // prints a bunch of lines. done to avoid seeing your opponent's board
    public static void clearScreen() {for (int i = 0; i < 30; i++) {System.out.print("\n");}}
}

// code written by Devan Gonzalez, William Christie, Daniel Acebal, and Joseph Mackle