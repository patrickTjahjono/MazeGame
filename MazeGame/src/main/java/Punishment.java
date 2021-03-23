import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Punishment extends Position{
    JLabel label;

    public Punishment(int x, int y) throws IOException {
        super(x, y);
        Image image = ImageIO.read(new File("src/punishment.png"));
        this.label = new JLabel(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(25, 25, Image.SCALE_FAST)));

        Board board = Board.getInstance();
        board.getCells()[x][y].add(label);
    }

    public JLabel getLabel() {
        return label;
    }

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