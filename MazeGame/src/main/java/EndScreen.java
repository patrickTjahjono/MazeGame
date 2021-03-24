import javax.swing.JPanel;
import java.awt.*;

/**
 * 
 * The EndScreen is used as a GUI for displaying the Winning Screen.  
 */
public class EndScreen extends JPanel{
  
    /**
     * Paints Graphics object to the panel with the specified properties of color, size, font, and string.
     * Final score and used time of player is also painted included in the Graphics object.
     */
    public void paintComponent(Graphics g) {
        // if player wins
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, 650, 650);
        g2d.setColor(Color.ORANGE);
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        g2d.drawString("You've Won", 175, 150);
        g2d.setColor(Color.blue.darker());
        g2d.setFont(new Font("Arial", Font.BOLD, 36));
        g2d.drawString("Score: " + String.valueOf(ScoreBoard.getInstance().getScore()),220, 190);
        g2d.drawString("Time: " + String.valueOf(TimeCounter.getInstance().getTime()),220, 220);       
    }
}
