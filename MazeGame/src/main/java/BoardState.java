import java.awt.*;
import javax.swing.*;
import java.util.Random;

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
                    boardState[x][y] = new Cell(x, y,1); // set boundary wall
                } else {
                    boardState[x][y] = new Cell(x, y,0); // set empty cell
                }
            }
        }

        Random random = new Random();
        for (int y = 2; y < height - 2; y++) {
            for (int x = 2; x < width - 2; x++) {
                if (y % 2 == 0 && random.nextFloat() < 0.50) {
                    boardState[x][y].setIsSolid(1);
                }
            }
        }
        // set start and end cells
        boardState[width - 1][height - 2].setIsSolid(0);
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
        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            return (this.boardState[x][y].getIsSolid());
        } else {
            return -1;
        }
    }

    public int calculateSquaredDistance(int x1, int y1, int x2, int y2) {
        int squaredDistance;
        // check bounds

        int cell1IsSolid = getCellIsSolid(x1, y1);
        int cell2IsSolid = getCellIsSolid(x2, y2);

        // Set squaredDistance to 2147483647 if a cell is solid or out of bounds
        if ((cell1IsSolid != 0) || (cell2IsSolid != 0)) {
            squaredDistance = Integer.MAX_VALUE;
        } else {
            int differenceX = (x2 - x1);
            int differenceY = (y2 - y1);
            squaredDistance = differenceX * differenceX + differenceY * differenceY;
        }
        return squaredDistance;
    }
}
