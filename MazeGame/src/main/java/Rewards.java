import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 
 * The Rewards class creates the GUI object for regular rewards and position obejcts on board. 
 * If Rewards object is collected by the player, it will be removed from board, and scoreBoard gets updated.
 */
public class Rewards extends Position{
    private JLabel label;

    /**
     * 
     * @param x the x coordinate that the reward object to be placed on, range from [0, 19].
     * @param y the y coordinate that the reward object to be placed on, range from [0, 20].
     * @throws IOException if (x, y) coordinate is out of range.
     */
    public Rewards(int x, int y) throws IOException {
        super(x, y);
        Image image = ImageIO.read(new File("src/reward.png"));
        this.label = new JLabel(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(25, 25, Image.SCALE_FAST)));

        Board board = Board.getInstance();
        board.getCells()[x][y].add(label);
    }

    /**
     * 
     * @return the JLabel of the reward object.
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * Gets the intance of BoardState and checks if regular reward is collected by the player.
     * If reward is collected, the method gets the instance of the ScoreBoard and Board. 
     * Player's score gets updated, collected reward's JLabel gets removed from cell.
     */
    public void collectedRR() {
        BoardState boardState = BoardState.getInstance();
        if(boardState.boardStateCells[this.getX()][this.getY()].getContainsReward() == 1) {
            ScoreBoard scoreBoard = ScoreBoard.getInstance();
            scoreBoard.updateScore(5);
            Board board = Board.getInstance();
            board.getCells()[this.getX()][this.getY()].remove(this.getLabel());
        }
    }
}

