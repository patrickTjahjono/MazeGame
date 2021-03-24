import javax.swing.*;
import java.io.IOException;

/**
 * Initialize creates Board, BoardState, ScoreBoard, TimeCounter, the player,
 * moving enemies and runs the maze game.
 */
public class Initialize {
    private JFrame MazeFrame;
    private Board board;
    private BoardState boardState;
    private ScoreBoard scoreBoard;
    private Player player1;
    private Enemy enemy1;
    private Enemy enemy2;

    /**
     * Initialize and run the maze game. Create enemies at positions (18, 9) and (13, 7),
     * update the GUI every tick (500ms) and display a win screen or loss screen upon
     * completion of the game.
     */
    public Initialize(){
        // create board (GUI)
        MazeFrame = new JFrame("MazeGame");
        board = Board.getInstance();
        scoreBoard = ScoreBoard.getInstance();
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

            // checks continue_game value from board
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

            // If continue_game is 0 output WinScreen or LoseScreen
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
