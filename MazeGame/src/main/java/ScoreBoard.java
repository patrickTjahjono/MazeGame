import javax.swing.*;
import java.awt.*;

/**
 * 
 * ScoreBoard class creates the GUI of the ScoreBoard component to display the score of player. 
 * The player's score either increments/decrements at each update of the ScoreBoard.
 */
public class ScoreBoard {
    private static ScoreBoard instance;
    private int score;
    JLabel label;

    /**
     * Creates a new ScoreBoard used as the GUI display for Player's Score. Initial score is set to 0.
     */
    private ScoreBoard() {
        score = 0;
        // assigning label 'S' 'C' 'O' 'R' 'E' ':' '0' for each panel
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

    /**
     * @return the existing instance of ScoreBoard or a new instance of ScoreBoard if one does not exist.
     */
    public static ScoreBoard getInstance() {
        if (instance == null) {
            instance = new ScoreBoard();
        }
        return instance;
    }

    /**
     * 
     * Updates the global score value and scoreboard GUI.
     * 
     * @param point the point that player gets from either punishment or rewards
     */
    public void updateScore(int point) {
        this.score += point;
        //remove the 0 panel and then add the updated one
        Board.getInstance().getCells()[6][0].remove(label);
        label = new JLabel(String.valueOf(score));
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[6][0].add(label);
    }

    /**
     * 
     * @return the score stored on scoreboard.
     */
    public int getScore() {
        return score;
    }
}

