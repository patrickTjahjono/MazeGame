import javax.swing.JPanel;
import java.awt.*;

/**
 * 
 * The LostScreen is used as a GUI for displaying the Lost Screen.  
 */
public class LostScreen extends JPanel{
    
    /**
     * Paints Graphics object to the panel with the specified properties of color, size, font, and string.
     */
    public void paint(Graphics g) {
        // if game ends
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, 650, 650);
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        g2d.drawString("You've Lost", 175, 175);
        
    }
}
