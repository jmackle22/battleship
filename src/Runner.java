import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("- = empty\n" +
                "● = hit,\n" +
                "◆ = hit ship,\n" +
                "■ = ship,\n" +
                "□ = miss");

        Game game = new Game();
        game.play();

        //System.out.println("Choose Square if You Dare???:");
        //Game.attack(playerOne);
    }
}















































// nice