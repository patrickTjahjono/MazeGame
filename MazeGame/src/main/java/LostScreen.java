import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class LostScreen extends JPanel{
    
    public void paint(Graphics g) {
        // if game ends
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, 650, 650);
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        g2d.drawString("You've Lost", 175, 175);
        //System.exit(0);
        
    }
}
