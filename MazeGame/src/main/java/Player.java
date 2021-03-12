import java.util.Scanner;

public class Player extends MovingCharacter{
    public Player(int x, int y) {
        super(x, y);
    }

    public void move(GameBoard board) {
        Scanner playerInput = new Scanner(System.in);
        System.out.println("Make your move.");
        String playerMove = playerInput.nextLine();

        if (playerMove.equals("w")) {
            this.moveWest(board);
        } else if (playerMove.equals("e")) {
            this.moveEast(board);
        } else if (playerMove.equals("n")) {
            this.moveNorth(board);
        } else if (playerMove.equals("s")) {
            this.moveSouth(board);
        }
    }
}
