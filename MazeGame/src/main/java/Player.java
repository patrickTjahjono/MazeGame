import java.util.Scanner;

public class Player extends MovingCharacter{
    public Player(int x, int y, String label)
    {
        super(x, y, label);
    }

    public void move(Board board, BoardState boardState) {
        Scanner playerInput = new Scanner(System.in);
        System.out.println("Make your move.");
        String playerMove = playerInput.nextLine();

        if (playerMove.equals("w")) {
            this.moveWest(board, boardState);
        } else if (playerMove.equals("e")) {
            this.moveEast(board, boardState);
        } else if (playerMove.equals("n")) {
            this.moveNorth(board, boardState);
        } else if (playerMove.equals("s")) {
            this.moveSouth(board, boardState);
        }
    }
}
