import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private String[][] board;
    private int totalShipUnits; // updates whenever getShipUnits is called
    // style configuration
    public static String empty = "~";
    public static String ship = "■";
    public static String hitShip = "◆";
    public static String newHit = "●";
    public static String miss = "□";
    public static String newMiss = "◎";//\uD83D\uDC04";

    // instantiates the board itself and configures it
    public Board() {
        this.board = new String[][]{
                new String[10],
                new String[10],
                new String[10],
                new String[10],
                new String[10],
                new String[10],
                new String[10],
                new String[10],
                new String[10],
                new String[10],
        };
        // convert nulls into emptys
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {board[i][j] = empty;}
            }
        }
    }

    // display board (2 types: secretive (only show hits), non-secretive (show all))
    public void displayBoard(boolean secret, String name) {
        System.out.println("    A B C D E F G H I J");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " | ");
            for (String val : board[i]) {
                if (secret) {
                    if (!val.equals(ship)) {
                        System.out.print(val + " ");
                    } else System.out.print(empty + " ");
                } else System.out.print(val + " ");
            }
            // display name pretty
            if (!name.isEmpty()) {
                if      (i == 3) System.out.print("|         ┏╾╼╾╼╾╼╾╼╾╼╾╼\n");
                else if (i == 4) System.out.print("|         ╿  "+ name +"\n");
                else if (i == 5) System.out.print("|         ╽  Board     \n");
                else if (i == 6) System.out.print("|         ┗╾╼╾╼╾╼╾╼╾╼╾╼\n");
                else System.out.print("|\n");
            } else System.out.print("|\n");
        }
    }

    // edit board
    public void editBoard(int x, int y, String edit) {
        if ((x >= 0 && x < 10) && (y >= 0 && y < 10)) {
            board[y][x] = edit;
        } else {
            System.out.println("Edit board error: index out of bound (x: " + x + ", y: " + y + ", edit: " + edit + ")");
        }
    }

    // ship building methods
    public void placeShip(int size, boolean horizontal, int x, int y) {
        for (int i = 0; i < size; i++) {
            if (horizontal) {this.editBoard(x+i, y, ship);} else {this.editBoard(x, y+i, ship);}
        }
    }

    // places a ship of defined size at a random spot on the board. accounts for boundaries and ship overlap
    public void randomShip(int size) {
        // generate rand num 0 to 9 for x and y
        int x = ThreadLocalRandom.current().nextInt(0, 10);
        int y = ThreadLocalRandom.current().nextInt(0, 10);
        // catch if the x or y in accordance with the ship size is out of bounds (accounts for horizontal and vertical)
        while (x+size-1 > 9 || y+size-1 > 9) {
            x = ThreadLocalRandom.current().nextInt(0, 10);
            y = ThreadLocalRandom.current().nextInt(0, 10);
        }
        // 50% chance of horizontal/vertical
        boolean horizontal = true;
        if ((int)(Math.random() * 2) == 0) {horizontal = false;}

        // check if a ship can be placed at x, y
        boolean canBePlaced = true;
        for (int i = 0; i < size; i++) {
            boolean shipFound = false;
            // check each coord of the ship to see if another shipUnit is surrounding it
            if (horizontal) {shipFound = checkForShip(x+i, y);} else {shipFound = checkForShip(x, y+i);}
            if (shipFound) canBePlaced = false;
        }

        // place ship if it is possible, otherwise repeat loop until a ship can be placed
        if (canBePlaced) {
            placeShip(size, horizontal, x, y);
        } else randomShip(size);
    }

    // generate many ships
    public void generateShips(int[] sizes) {for (int i : sizes) randomShip(i);}

    // check if a ship is anywhere around x, y
    public boolean checkForShip(int x, int y) {
        boolean shipFound = false;
        String[] s = sTest(x, y);
        for (String n : s) { // if a ship block is located around the coordinate, set shipFound to true
            if (n != null && n.equals(ship)) {
                shipFound = true;
                break;
            }
        }
        return shipFound;
    }

    // Checking Ship Surroundings STEST
    public String[] sTest(int x, int y) {
       String[] s = {empty,empty,empty,empty};
       s[0] = nTest(x,y-1); // N
       s[1] = nTest(x+1,y); // E
       s[2] = nTest(x,y+1); // S
       s[3] = nTest(x-1,y); // W
       return s;
    }

    // nTest = number test (integers)
    public String nTest(int x, int y) {
        if (x >= 0 && x <= 9 && y >= 0 && y <= 9) return board[y][x];
        return null;
    }

    // cTest = char test (ex: A1, b4)
    public String cTest(char c, int num) {
        if ((num >= 0 && num <= 9) && (((int)Character.toLowerCase(c) - 97) <= 9)) return board[num][((int)Character.toLowerCase(c) - 97)];
        return null;
    }

    // update & return total ship units
    public int getShipUnits() {
        int total = 0;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (nTest(x, y) == ship) {
                    total++;
                }
            }
        }
        this.totalShipUnits = total;
        return this.totalShipUnits;
    }
}

// code written by Devan Gonzalez, William Christie, Daniel Acebal, and Joseph Mackle