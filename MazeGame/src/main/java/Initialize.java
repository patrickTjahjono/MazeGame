import javax.swing.*;
import java.io.IOException;

public class Initialize {
    private JFrame MazeFrame;
    private Board board;
    private BoardState boardState;
    private Player player1;
    private Enemy enemy1;
    private Enemy enemy2;

    public Initialize(){

        MazeFrame = new JFrame("MazeGame");
        // create board (GUI)
        board = Board.getInstance();
        MazeFrame.add(board);
        MazeFrame.pack();
        MazeFrame.setLocationRelativeTo(null);
        MazeFrame.setVisible(true);

        // create board states
        boardState = BoardState.getInstance();
        TimeCounter timeCounter = TimeCounter.getInstance();

        try {
            // windows close button with confirmation created
            MazeFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        System.exit(0);
                }
            });
            player1 = Player.getInstance();
            enemy1= new Enemy(18, 9);
            enemy2 = new Enemy(13, 7);
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
                    MazeFrame.setVisible(false);
                    EndScreen win = new EndScreen();
                    MazeFrame.setContentPane(win);
                } else {
                    LostScreen lose = new LostScreen();
                    MazeFrame.setContentPane(lose);
                }
                MazeFrame.setVisible(true);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
