import javax.swing.*;
import java.awt.*;
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
            Player player1 = Player.getInstance();
            Enemy enemy1 = new Enemy(18, 8);
            MazeFrame.addKeyListener(player1);

            // update board to display the starting position of the player
            SwingUtilities.updateComponentTreeUI(MazeFrame);

            //int turn = 0;
            // checks continue_game value from board
            while(board.getContinue_game() == 1) {
                try {
                    Thread.sleep(500);
                    turnCounter.updateTurn();
                    boardState.spawnBR();
                    boardState.checkBonusRewardExpiration();
                    player1.move();
                    enemy1.move();
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
                    MazeFrame.setVisible(true);
                } else {
                    System.out.println("caught by enemy");
                    LostScreen lose = new LostScreen();
                    MazeFrame.setContentPane(lose);
                    MazeFrame.setVisible(true);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
