import javax.swing.*;
import java.awt.*;

/**
 *
 * TimeCounter class creates the GUI of the TimeCounter component to display the time of the game.
 * The game's time and turn will increment at each update of the time.
 */
public class TimeCounter {
    private static TimeCounter instance;
    private float time;
    private int turn;
    JLabel label;


/**
 * Creates a new TimeCounter used as the GUI display for the game's time. Initial time and turn are both set to 0.
 */
    private TimeCounter() {
        turn = 0;
        time = 0;
        // assigning label 'T' 'U' 'R' 'N' ':' '0' for each panel
        JLabel temp;
        label = new JLabel("T");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[10][0].add(label);
        label = new JLabel("I");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[11][0].add(label);
        label = new JLabel("M");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[12][0].add(label);
        label = new JLabel("E");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[13][0].add(label);
        label = new JLabel(":");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[14][0].add(label);
        label = new JLabel(String.valueOf(time));
        label.setFont(new Font("Arial",1,16));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[15][0].add(label);
    }
    /**
     * @return the existing instance of TimeCounter or a new instance of counter if one does not exist.
     */
    public static TimeCounter getInstance() {
        if (instance == null) {
            instance = new TimeCounter();
        }
        return instance;
    }


    /**
     * Updates the global time value, turn value and TimeCounter GUI.
     */
    public void updateTime() {
        this.time += 0.5;
        turn += 1;
        //remove the 0 panel and then add the updated one
        Board.getInstance().getCells()[15][0].remove(label);
        label = new JLabel(String.valueOf(time));
        label.setFont(new Font("Arial",1,16));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[15][0].add(label);
    }

    /**
     *
     * @return the time stored on TimeCounter.
     */
    public float getTime() {
        return this.time;
    }

    /**
     *
     * @return the turn stored on TimeCounter.
     */
    public int getTurn() {
        return turn;
    }
}
