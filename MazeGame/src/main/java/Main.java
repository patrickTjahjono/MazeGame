import javax.swing.*;
import java.awt.*;
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

        // create player
        Player player1 = new Player(1, 1, "P");

        // update board with new player
        JPanel[][] cells = board.getCells();
        cells[player1.getX()][player1.getY()].add(player1.getLabel());
        SwingUtilities.updateComponentTreeUI(MazeFrame);


        for (int turn = 0; turn < 10; turn++) {
            player1.move(board, boardState);
            SwingUtilities.updateComponentTreeUI(MazeFrame);
        }


        // https://stackoverflow.com/questions/7628121/how-can-i-refresh-or-reload-the-jframe/7630604
        SwingUtilities.updateComponentTreeUI(MazeFrame);
    }
}
