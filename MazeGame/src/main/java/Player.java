import java.util.Scanner;

public class Player extends Position{

    public Player(int x, int y) {
        super(x, y);
    }

    public void moveWest() {
        this.setX(this.getX() - 1);
    }

    public void moveEast() {
        this.setX(this.getX() + 1);
    }

    public void moveNorth() {
        this.setY(this.getY() - 1);
    }

    public void moveSouth() {
        this.setY(this.getY() + 1);
    }

    public void move() {
        Scanner playerInput = new Scanner(System.in);
        System.out.println("Make your move.");
        String playerMove = playerInput.nextLine();

        if (playerMove.equals("w")) {
            this.moveWest();
        } else if (playerMove.equals("e")) {
            this.moveEast();
        } else if (playerMove.equals("n")) {
            this.moveNorth();
        } else if (playerMove.equals("s")) {
            this.moveSouth();
        }
    }
}
