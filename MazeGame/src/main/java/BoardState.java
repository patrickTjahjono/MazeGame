import javax.swing.*;
import java.awt.*;

public class BoardState {
    private int width, height;
    public Cell[][] boardState;

    public BoardState(Board board, int width, int height) {
        this.width = width;
        this.height = height;

        Cell[][] boardState = new Cell[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x % (width - 1) == 0 || y % (height - 1) == 0) {
                    boardState[x][y] = new Cell(x, y, "#", 1); // set boundary wall
                } else {
                    boardState[x][y] = new Cell(x, y, ".", 0); // set empty cell
                }
            }
        }
        // set start and end cells
        boardState[width - 1][height - 2].setDefaultMarker(("S"));
        boardState[width - 1][height - 2].setIsSolid(0);
        boardState[0][1].setDefaultMarker("E");
        boardState[0][1].setIsSolid(0);
        this.boardState = boardState;

        JPanel[][] cells = board.getCells();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (boardState[x][y].getIsSolid() == 1) {
                    cells[x][y].setBackground(Color.BLACK);
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCellIsSolid(int x, int y) {
        return(this.boardState[x][y].getIsSolid());
    }

    public int calculateSquaredDistance(int x1, int y1, int x2, int y2) {
        int squaredDistance;
        int cell1IsSolid = getCellIsSolid(x1, y1);
        int cell2IsSolid = getCellIsSolid(x2, y2);
        if ((cell1IsSolid == 1) || (cell2IsSolid == 1)) {
            squaredDistance = Integer.MAX_VALUE;
        } else {
            int differenceX = (x2 - x1);
            int differenceY = (y2 - y1);
            squaredDistance = differenceX * differenceX + differenceY * differenceY;
        }
        return squaredDistance;
    }

    public void updateBoard(Player player) {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.boardState[x][y].setMarker(this.boardState[x][y].getDefaultMarker());
            }
        }
        this.boardState[player.getX()][player.getY()].setMarker("P");
    }

    public void printBoard() {
        System.out.println();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.printf(this.boardState[x][y].toString());
            }
            System.out.println();
        }
    }
}
