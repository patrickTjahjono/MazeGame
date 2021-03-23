import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class BonusReward extends Position {
    // turn after which the bonus reward is removed
    private int expiresAfter;
    private JLabel label;

    public BonusReward(int x, int y) throws IOException {
        super(x, y);
        TurnCounter turnCounter = TurnCounter.getInstance();
        Random random = new Random();
        this.expiresAfter = turnCounter.getTurn() + random.nextInt(100);

        Image image = ImageIO.read(new File("src/bonusreward.png"));
        this.label = new JLabel(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(25, 25, Image.SCALE_FAST)));

        // set bonus reward on Board at position (x, y)
        Board board = Board.getInstance();
        board.getCells()[x][y].add(label);
    }

    public int getExpiresAfter() {
        return expiresAfter;
    }

    public JLabel getLabel() {
        return label;
    }

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
