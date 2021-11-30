public class Board {
    /*String[][] board = {
            {"O", "O", "O", "S", "O", "O", "O", "O", "O", "O"},
            {"O", "O", "O", "S", "O", "S", "S", "O", "O", "O"},
            {"O", "O", "O", "s", "O", "O", "O", "O", "O", "O"},
    };*/
    String[][] board;


    public Board() {
        initBoard();
    }

    private void initBoard() {
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

    public void displayBoard() {
        System.out.print("\n");
        System.out.println("    A B C D E F G H I J");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " | ");
            for (String val : board[i]) {
                System.out.print(val + " ");
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
        if (horizontal) {
            for (int i = 0; i < size; i++) {
                this.editBoard(x+i, y, "■");
            }
        } else {
            for (int i = 0; i < size; i++) {
                this.editBoard(x, y+i, "■");
            }
        }
    }

    // test
    public String test(int x, int y) {
        //todo: test for what is at a certain coord (ship, empty, etc.)
    }
}
