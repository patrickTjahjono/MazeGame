import javax.swing.*;
import java.awt.*;

public class TurnCounter {
    private static TurnCounter instance;
    private int turn;
    JLabel label;

    private TurnCounter() {
        turn = 0;
        // assigning label 'T' 'U' 'R' 'N' ':' '0' for each panel
        JLabel temp;
        label = new JLabel("T");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[10][0].add(label);
        label = new JLabel("U");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[11][0].add(label);
        label = new JLabel("R");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[12][0].add(label);
        label = new JLabel("N");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[13][0].add(label);
        label = new JLabel(":");
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[14][0].add(label);
        label = new JLabel(String.valueOf(turn));
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[15][0].add(label);
    }

    public static TurnCounter getInstance() {
        if (instance == null) {
            instance = new TurnCounter();
        }
        return instance;
    }

    public void updateTurn() {
        this.turn += 1;
        //remove the 0 panel and then add the updated one
        Board.getInstance().getCells()[15][0].remove(label);
        label = new JLabel(String.valueOf(turn));
        label.setFont(new Font("Arial",1,20));
        label.setForeground(Color.WHITE);
        Board.getInstance().getCells()[15][0].add(label);
    }

    public int getTurn() {
        return this.turn;
    }
}
