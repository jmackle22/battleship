public class Runner {
    public static void main(String[] args) {
        /*int[] pick = {7, 9, 6};
        String[] temp = {"what", "hey", "no", "lombard", "ball", "red", "wilgrum", "mons", "is"};
        for (int i = 0; i < pick.length; i++) {
            System.out.print(temp[pick[i]-1] + " ");
        }

        for (String val : temp) {
            System.out.print(val + " ");
        }*/

        System.out.println("O = empty\n" +
                "X = hit,\n" +
                "▣ = hit ship,\n" +
                "■ = ship,\n" +
                "▯ = miss");

        Board playerOne = new Board();
        Board playerTwo = new Board();

        playerOne.displayBoard();

        playerOne.placeShip(4, false, 7,3);
        playerOne.placeShip(3, true, 0,1);

        playerOne.displayBoard();

        playerOne.test(0, 0);
    }
}
