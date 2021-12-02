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

        playerOne.placeShip(4, false, 7,3);
        playerOne.placeShip(3, true, 0,1);

        playerOne.displayBoard(true);

        System.out.println("Choose Square if You Dare???:");

        Game.takeTurnAgainst(playerOne);
    }
}
