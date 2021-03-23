import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class EndScreen extends JPanel{
  
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
        //create jButton to restart game
        //System.exit(0);
        
    }
}
