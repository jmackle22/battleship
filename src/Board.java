import java.util.Random;

public class Board {
    /*String[][] board = {
            {"O", "O", "O", "S", "O", "O", "O", "O", "O", "O"},
            {"O", "O", "O", "S", "O", "S", "S", "O", "O", "O"},
            {"O", "O", "O", "s", "O", "O", "O", "O", "O", "O"},
    };*/
    private String[][] board;
    private int totalShipUnits; // todo: total ship units (determine winner when units reach 0)
    private static Random rand = new Random();

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
        // convert nulls into Os
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {board[i][j] = "O";}
            }
        }
    }

    // display board (2 types: secretive (only show hits), non-secretive (show all))
    public void displayBoard(boolean secret) {
        System.out.print("\n");
        System.out.println("    A B C D E F G H I J");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " | ");
            for (String val : board[i]) {
                if (secret) {
                    if (!val.equals("■")) {
                        System.out.print(val + " ");
                    } else System.out.print("O ");
                } else System.out.print(val + " ");
            }
            System.out.print("|\n");
        }
    }

    public void editBoard(int x, int y, String edit) {
        if ((x >= 0 && x < 10) && (y >= 0 && y < 10)) {
            board[y][x] = edit;
        } else {
            System.out.println("Edit board error: index is out of bound");
        }
    }

    // ship building
    public void placeShip(int size, boolean horizontal, int x, int y) {
        for (int i = 0; i < size; i++) {
            if (horizontal) {this.editBoard(x+i, y, "■");} else {this.editBoard(x, y+i, "■");}
        }
    }

    public void generateShips(int amount) {
        while (amount > 0) {
            // generate rand num 0 to 9 for x and y
            int size = 3; //todo: different size ships
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            boolean horizontal = true;
            if (rand.nextInt(2) == 0) {horizontal = false;}
            System.out.println(x + "\n" + y + "\n" + horizontal);

            // check if a ship can be placed at x, y
            boolean canBePlaced = true; //todo: analyze this
            for (int i = 0; i < size; i++) {
                String[] s;
                if (horizontal) {s = sTest(x+i, y);} else {s = sTest(x, y+i);}

                for (String n : s) {
                    if (n != null && n.equals("■")) {
                        canBePlaced = false;
                        break;
                    }
                }
            }
            System.out.println("can be placed: " + canBePlaced);

            // place ship if it is possible
            if (canBePlaced) {
                placeShip(3, horizontal, x, y);
                amount--;
            }
        }

    }

    // Checking Ship Surroundings STEST
    public String[] sTest(int x, int y) {
       String[] s = {"O","O","O","O"};
        s[0] = nTest(x,y-1);
        s[1] = nTest(x+1,y);
        s[2] = nTest(x,y+1);
        s[3] = nTest(x-1,y);
       return s;
    }

    // nTest = number test (integers), cTest = char test (A1, b4)
    public String nTest(int x, int y) {
        if (x >= 0 && x <= 9 && y >= 0 && y <= 9) {
            return board[y][x];
        }
        return null;
    }
    //christie.loud;
    public String cTest(char c, int num) {
        if ((num >= 0 && num <= 9) && (((int)Character.toLowerCase(c) - 97) <= 9)) {
            return board[num][((int)Character.toLowerCase(c) - 97)];
        }
        return null;
    }
}
































































