import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class Player extends MovingCharacter implements KeyListener {
    private String nextMove = "None";
    public Player(int x, int y) throws IOException
    {
        super(x, y);
        Image image = ImageIO.read(new File("src/playertest.png"));
        this.label = new JLabel(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(25, 25, Image.SCALE_FAST)));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_UP) { // move up
            this.nextMove = "n";
        } else if (key == KeyEvent.VK_DOWN) { // move down
            this.nextMove = "s";
        } else if (key == KeyEvent.VK_LEFT) { // move left
            this.nextMove = "w";
        } else if (key == KeyEvent.VK_RIGHT) { //move right
            this.nextMove = "e";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public void move(Board board, BoardState boardState) {
        if (this.nextMove.equals("w")) {
            this.moveWest(board, boardState);
        } else if (this.nextMove.equals("e")) {
            this.moveEast(board, boardState);
        } else if (this.nextMove.equals("n")) {
            this.moveNorth(board, boardState);
        } else if (this.nextMove.equals("s")) {
            this.moveSouth(board, boardState);
        }
        this.nextMove = "None";
    }

}
