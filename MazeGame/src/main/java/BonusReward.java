import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 *
 * BonusRewards class creates the GUI object for bonus rewards and position on board.
 * If Rewards object is collected by the player, it will be removed from board, and scoreBoard gets updated.
 */
public class BonusReward extends Position {
    // turn after which the bonus reward is removed
    private int expiresAfter;
    private JLabel label;

    /**
     *
     * Creates a new BonusReward on Board at (x, y) position. After some turn generated randomly the Bonus Reward will
     * relocate its position on the board.
     *
     * @param x the x coordinate that the reward object to be placed on, range from [0, 19].
     * @param y the y coordinate that the reward object to be placed on, range from [0, 20].
     * @throws IOException if (x, y) coordinate is out of range.
     */
    public BonusReward(int x, int y) throws IOException {
        super(x, y);
        TimeCounter turnCounter = TimeCounter.getInstance();
        Random random = new Random();
        this.expiresAfter = turnCounter.getTurn() + random.nextInt(100);

        Image image = ImageIO.read(new File("src/bonusreward.png"));
        this.label = new JLabel(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(25, 25, Image.SCALE_FAST)));

        // set bonus reward on Board at position (x, y)
        Board board = Board.getInstance();
        board.getCells()[x][y].add(label);
    }

    /**
     *
     * @return int number of turns before it expire or relocate
     */
    public int getExpiresAfter() {
        return expiresAfter;
    }

    /**
     *
     * @return the JLabel of the reward object.
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * Gets the instance of BoardState and checks if bonus reward is collected by the player.
     * If bonus reward is collected, the method gets the instance of the ScoreBoard and Board.
     * Player's score gets updated (+25), collected bonus reward's JLabel gets removed from cell.
     * Set bonus reward on the boardState to be 0 so that it can respawn.
     */
    public void collectedBR() {
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        BoardState boardState = BoardState.getInstance();
        Board board = Board.getInstance();
        int bonusRewardX = this.getX();
        int bonusRewardY = this.getY();
        if(boardState.boardStateCells[bonusRewardX][bonusRewardY].getContainsBonusReward() == 1) {
            scoreBoard.updateScore(25);
            board.getCells()[this.getX()][this.getY()].remove(this.getLabel());
            boardState.boardStateCells[bonusRewardX][bonusRewardY].setContainsBonusReward(0);
            boardState.setHasBonusReward(0);
        }
    }
}
