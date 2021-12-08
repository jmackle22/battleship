import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("O = empty\n" +
                "X = hit,\n" +
                "▣ = hit ship,\n" +
                "■ = ship,\n" +
                "▯ = miss");

        Board playerOne = new Board();
        Board playerTwo = new Board();

        playerOne.generateShips(5);
        playerOne.displayBoard(false);



        //System.out.println("Choose Square if You Dare???:");
        //Game.takeTurnAgainst(playerOne);
    }
}











































