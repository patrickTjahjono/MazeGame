import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Board extends JPanel {
    private static Board instance = null;
    private JPanel[][] cells;
    private int continue_game;   // check status of player
    private boolean pause_game;

    public static Board getInstance() {
        if (instance == null)
            instance = new Board();
        return instance;
    }

    private Board() {
        pause_game = false;
        continue_game = 1;
        JPanel[][] cells = new JPanel[20][10]; // 20 cell wide, 10 cell tall board
        setLayout(new GridLayout(10, 20));
        Dimension cellDimensions = new Dimension(32, 32); // 32px by 32px cell size
        Border blackBorder = BorderFactory.createLineBorder(Color.black);
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 20; x++) {
                JPanel cell = new JPanel();
                cell.setPreferredSize(cellDimensions);
                cell.setBorder(blackBorder);
                add(cell);
                cells[x][y] = cell;
            }
        }
        this.cells = cells;
    }

    public JPanel[][] getCells() {
        return cells;
    }

    public int getContinue_game() {
        return continue_game;
    }

    public void setContinue_game(int i) {
        this.continue_game = i;
    }

    public boolean getPause_game(){
        return pause_game;
    }

    public void setPause_game(boolean bool) {
        this.pause_game = bool;
    }
}
