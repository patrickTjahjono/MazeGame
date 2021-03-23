import javax.swing.*;
import java.awt.*;

public class TimeCounter {
    private static TimeCounter instance;
    private float time;
    JLabel label;

    private TimeCounter() {
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

    public static TimeCounter getInstance() {
        if (instance == null) {
            instance = new TimeCounter();
        }
        return instance;
    }

    public void updateTime() {
        this.time += 0.5;
        //remove the 0 panel and then add the updated one
        Board.getInstance().getCells()[15][0].remove(label);
        label = new JLabel(String.valueOf(time));
        label.setFont(new Font("Arial",1,16));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[15][0].add(label);
    }

    public float getTime() {
        return this.time;
    }
}
