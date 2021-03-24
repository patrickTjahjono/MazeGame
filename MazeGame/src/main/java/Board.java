import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * The Board is used as the GUI. It is used to display the score, time, player, enemies, rewards,
 * punishments, walls, along with the start and end cells. Board also keeps track of whether the
 * game should continue and whether the game is paused.
 */
public class Board extends JPanel {
    private static Board instance = null;
    private JPanel[][] cells;
    private int continue_game;
    private boolean pause_game;

    /**
     * @return the existing instance of Board or a new instance of Board if one does not exist
     */
    public static Board getInstance() {
        if (instance == null)
            instance = new Board();
        return instance;
    }

    /**
     * Create a new Board to be used as the GUI. The Board is a 2D array of JPanels that is
     * 20 cells wide and 10 cells tall. Cells are 32 by 32 pixels and are initially white
     * with black borders.
     */
    private Board() {
        pause_game = false;
        continue_game = 1;
        JPanel[][] cells = new JPanel[20][10];
        setLayout(new GridLayout(10, 20));
        Dimension cellDimensions = new Dimension(32, 32);
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

    /**
     * @return the 2D array of JPanels
     */
    public JPanel[][] getCells() {
        return cells;
    }

    /**
     * @return whether the game should continue (1) or terminate (0)
     */
    public int getContinue_game() {
        return continue_game;
    }

    /**
     * @param i whether the game should continue (1) or terminate (0)
     */
    public void setContinue_game(int i) {
        this.continue_game = i;
    }

    /**
     * @return whether the game is paused (true) or not paused (false)
     */
    public boolean getPause_game(){
        return pause_game;
    }

    /**
     * @param bool whether the game is paused (true) or not paused (false)
     */
    public void setPause_game(boolean bool) {
        this.pause_game = bool;
    }
}
