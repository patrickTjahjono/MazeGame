import javax.swing.*;
import java.awt.*;

public class ScoreBoard {
    private static ScoreBoard instance;
    private int score;
    JLabel label;

    private ScoreBoard() {
        score = 0;
        // assigning label 'S' 'C' 'O' 'R' 'E' ':' '0' for each panel
        JLabel temp;
        label = new JLabel("S");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[0][0].add(label);
        label = new JLabel("C");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[1][0].add(label);
        label = new JLabel("O");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[2][0].add(label);
        label = new JLabel("R");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[3][0].add(label);
        label = new JLabel("E");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[4][0].add(label);
        label = new JLabel(":");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[5][0].add(label);;
        label = new JLabel(String.valueOf(score));
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[6][0].add(label);
    }

    public static ScoreBoard getInstance() {
        if (instance == null) {
            instance = new ScoreBoard();
        }
        return instance;
    }

    public void updateScore(int point) {
        this.score += point;
        //remove the 0 panel and then add the updated one
        Board.getInstance().getCells()[6][0].remove(label);
        label = new JLabel(String.valueOf(score));
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[6][0].add(label);
    }

    public int getScore() {
        return score;
    }
}

