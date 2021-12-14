import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(Board.empty + " = empty         " + Board.ship + " = ship\n" +
                Board.hitShip + " = hit ship      " + Board.newHit + " = new hit\n" +
                Board.miss + " = miss          " + Board.newMiss + " = new miss\n");

        while (true) {
            // create a new game and play
            Game battleship = new Game();
            battleship.play();

            // ask user if they want to play again
            System.out.println("Would you like to play again? (y/n)\n");
            if (scan.nextLine().contains("n")) break;
        }

        String date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
        System.out.println("　　　　　　。　.　　　　　ﾟ　　　。　　ﾟ　.　ﾟ。,　☆　　　。ﾟ.　　．\n　　　　．。　　　　o　　　．.　。　ﾟ　 ﾟ　,　。.　o　。*　。　.　o．　。　．　.\n　　　　　　　　。　　　.　　　。　　．　.ﾟo　。　*．　。　．.　☆　.　＋.　.　　.\n　。　.　　．　.　　　.　　　．　　。　ﾟ,　☆　ﾟ.　＋　。　ﾟ　,。　.　。　　,　.。\n　　　　ﾟ　　。　　　ﾟ　　.　　+。　ﾟ　*　。.　,　。ﾟ　+.　。*。　ﾟ.\n　。　　.　　　.　。　。ﾟ.　。*　。,　　´。.　　☆。。.　ﾟ。+　。　.。　　.　　｡　　　.\n　　.　　　。　　ﾟ　ﾟ。　。,　.。o　☆　+　,ﾟ。　*。.　。　。　.　　　　。　　　　.\n　ﾟ　.ﾟ　ﾟ　　。ﾟ　+　。.　+。　*　。ﾟ。ﾟ.,　,+　。ﾟ.　。　.　.　　　,\nﾟ。ﾟ+ﾟ`,　o。。.ﾟ*。ﾟ　。.ﾟ　。　☆＋。。ﾟ.　°　。　.　　　,　　　　　　ﾟ\n　。,　.ﾟ。　+　☆。,ﾟ.　o。 。+　。ﾟ.,　　.　ﾟ　　　,　　　。　　　　　。\n　ﾟ.　ｏ　*　。　ﾟ。　ﾟ.。　　ﾟ。　+ﾟ　　。　　　ﾟ。　　　ﾟ\nﾟ`　.ﾟ　.　　.　ﾟ.　.　ﾟ　　.　　ﾟ　　.　　　,　　.　　　　　　.\n　.　　.　o　　。　　　.　,　　　　　。　　　　　　　.\n　　　　　　。　　　　　　　　　　　　　　　　　ﾟ　　　.\n　,　.　　　　　　　　.　　　　　,　　　　　　　.\n\n　　　　　　　　　　　　○　　○>\n　　　　　　　　　　　ノ(しへ (しへ\n　　‐''\"´'''\"\"\"゛''\"｀''\"″\"｀\"\"\"'゛''\"´'''\"″\"''\"｀''\"｀'\"｀\"\"\"''''｀`'‐");
        System.out.println("\"Thanks for playing Battleship my guy! Goodbye\" -Wilgumn Red, " + date);
    }
}

// code written by Devan Gonzalez, William Christie, Daniel Acebal, and Joseph Mackle