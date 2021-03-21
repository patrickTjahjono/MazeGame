import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JFrame MazeFrame = new JFrame("MazeGame");
        // create board (GUI)
        Board board = new Board();
        MazeFrame.add(board);
        MazeFrame.pack();
        MazeFrame.setLocationRelativeTo(null);
        MazeFrame.setVisible(true);

        // create board states
        BoardState boardState = new BoardState(board,20, 10);

        try {
            Player player1 = new Player(0, 1);
            Enemy enemy1 = new Enemy(18, 8);
            MazeFrame.addKeyListener(player1);

            // update board to display the starting position of the player
            JPanel[][] cells = board.getCells();
            cells[player1.getX()][player1.getY()].add(player1.getLabel());
            cells[enemy1.getX()][enemy1.getY()].add(enemy1.getLabel());
            cells[player1.getX()][player1.getY()].add(player1.getLabel());
            SwingUtilities.updateComponentTreeUI(MazeFrame);

            //int turn = 0;
            int continue_game = 1;
            while(continue_game == 1) {
                try {
                    Thread.sleep(500);
                    player1.move(board, boardState);

                    if (enemy1.getCurrentSquaredDistanceToPlayer(player1, boardState) != 1) {
                        enemy1.move(board, boardState, player1);
                        SwingUtilities.updateComponentTreeUI(MazeFrame);
                    } else { // enemy has caught up to player
                        continue_game = 0;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        // https://stackoverflow.com/questions/7628121/how-can-i-refresh-or-reload-the-jframe/7630604
        SwingUtilities.updateComponentTreeUI(MazeFrame);
    }
}
