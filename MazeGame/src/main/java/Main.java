import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JFrame MazeFrame = new JFrame("MazeGame");
        // create board (GUI)
        Board board = Board.getInstance();
        MazeFrame.add(board);
        MazeFrame.pack();
        MazeFrame.setLocationRelativeTo(null);
        MazeFrame.setVisible(true);

        // create board states
        BoardState boardState = BoardState.getInstance();
        ScoreBoard scoreboard = ScoreBoard.getInstance();
        TimeCounter timeCounter = TimeCounter.getInstance();

        try {
            // windows close button with confirmation created
            MazeFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    if (JOptionPane.showConfirmDialog(MazeFrame,
                            "Are you sure you want to close this window?", "Close Window?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                        System.exit(0);
                    }
                }
            });
            Player player1 = Player.getInstance();
            Enemy enemy1 = new Enemy(18, 9);
            Enemy enemy2 = new Enemy(13, 7);
            MazeFrame.addKeyListener(player1);

            // update board to display the starting position of the player
            SwingUtilities.updateComponentTreeUI(MazeFrame);

            //int turn = 0;
            // checks continue_game value from  board
            while(board.getContinue_game() == 1) {
                //pause condition
                player1.move();
                if(board.getPause_game())
                    continue;

                try {
                    Thread.sleep(500);
                    timeCounter.updateTime();
                    boardState.spawnBR();
                    boardState.checkBonusRewardExpiration();
                    enemy1.move();
                    enemy2.move();
                    SwingUtilities.updateComponentTreeUI(MazeFrame);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            SwingUtilities.updateComponentTreeUI(MazeFrame);
            
            // If continue_game is 0 ouput WinScreen or LoseScreen
            // haven't set condition for scores yet
            if(board.getContinue_game() == 0) {
                if(player1.isAtEnd == true) {
                    System.out.println("reached end cell");
                    MazeFrame.setVisible(false);
                    EndScreen win = new EndScreen();
                    MazeFrame.setContentPane(win);
                } else {
                    System.out.println("caught by enemy");
                    LostScreen lose = new LostScreen();
                    MazeFrame.setContentPane(lose);
                }
                /*//create jButton to restart game
                //JPanel rPanel = new JPanel();
                JButton restart = new JButton("Restart");
                //rPanel.add(restart);
                restart.setBounds(150, 250,130,30);
                MazeFrame.add(restart,BorderLayout.SOUTH);
                MazeFrame.setLayout(null);
                MazeFrame.setVisible(true);*/
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
