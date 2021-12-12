import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Runner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("O = empty\n" +
                "X = hit,\n" +
                "◆ = hit ship,\n" +
                "■ = ship,\n" +
                "□ = miss");

        Game game = new Game();
        game.play();

        //System.out.println("Choose Square if You Dare???:");
        //Game.takeTurnAgainst(playerOne);
    }
}















































// nice