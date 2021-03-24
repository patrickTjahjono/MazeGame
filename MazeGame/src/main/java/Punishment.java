import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 
 * The Punishment class creates the GUI object for punishment and position the obejct on board. 
 * If player's position collides with punishment's position, punishment JLabel gets removed from board, and scoreBoard gets updated.
 */
public class Punishment extends Position{
    private JLabel label;

    /**
     * 
     * Creates a new Punishment on Board at (x, y) position. 
     * 
     * @param x the x coordinate that the punishment object to be placed on, range from [0, 19].
     * @param y the y coordinate that the punihsment object to be placed on, range from [0, 20].
     * @throws IOException if (x, y) coordinate is out of range.
     */
    public Punishment(int x, int y) throws IOException {
        super(x, y);
        Image image = ImageIO.read(new File("src/punishment.png"));
        this.label = new JLabel(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(25, 25, Image.SCALE_FAST)));

        Board board = Board.getInstance();
        board.getCells()[x][y].add(label);
    }

    /**
     * 
     * @return JLabel of the Punishment object.
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * Gets the intance of BoardState and checks if Puishment is collected by the player.
     * If punishment is collected, the method gets the instance of the ScoreBoard and Board. 
     * Player's score gets updated (-5), collected punishment's JLabel gets removed from cell.
     */
    public void collectedPunishment() {
        BoardState boardState = BoardState.getInstance();
        if(boardState.boardStateCells[this.getX()][this.getY()].getContainsPunishment() == 1) {
            ScoreBoard scoreBoard = ScoreBoard.getInstance();
            scoreBoard.updateScore(-5);
            Board board = Board.getInstance();
            board.getCells()[this.getX()][this.getY()].remove(this.getLabel());
        }
    }
}