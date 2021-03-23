import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BoardState {
    private static BoardState instance = null;
    private ArrayList<Punishment> punishments = new ArrayList<>();
    private ArrayList<Rewards> rewards_R = new ArrayList<>();
    private int width, height;
    public Cell[][] boardStateCells;

    public static BoardState getInstance() {
        if (instance == null)
            instance = new BoardState();
        return instance;
    }

    private BoardState() {
        Board board = Board.getInstance();
        this.width = 20;
        this.height = 10;

        Cell[][] boardStateCells = new Cell[width][height];

        // initialize empty cells and boundary walls
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x % (width - 1) == 0 || y % (height - 1) == 0) {
                    boardStateCells[x][y] = new Cell(x, y,1); // set boundary wall
                } else {
                    boardStateCells[x][y] = new Cell(x, y,0); // set empty cell
                }
            }
        }

        // generate semi-random barriers
        Random random = new Random();
        for (int y = 2; y < height - 2; y++) {
            for (int x = 2; x < width - 2; x++) {
                if (y % 2 == 0 && random.nextFloat() < 0.50) {
                    boardStateCells[x][y].setIsSolid(1);
                }
            }
        }

        // generate punishments
        int maxNumPunishments = 5;
        int numPunishments= 0;
        while (numPunishments < 1) {
            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    if (numPunishments < maxNumPunishments &&
                            boardStateCells[x][y].getContainsRewardOrPunishment() == 0 &&
                            boardStateCells[x][y].getIsSolid() == 0 &&
                            random.nextFloat() < 0.05) {
                        try {
                            Punishment p = new Punishment(x, y);
                            punishments.add(p);
                            boardStateCells[x][y].setContainsPunishment(1);
                            numPunishments++;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        // generate rewards
        int maxNumRewards = 10;
        int numRewards = 0;
        while (numRewards < 1) {
            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    if (numRewards < maxNumRewards &&
                            boardStateCells[x][y].getContainsRewardOrPunishment() == 0 &&
                            boardStateCells[x][y].getIsSolid() == 0 &&
                            random.nextFloat() < 0.05) {
                        try {
                            Rewards r = new Rewards(x, y);
                            rewards_R.add(r);
                            boardStateCells[x][y].setContainsReward(1);
                            numRewards++;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        JPanel[][] cells = board.getCells();
        // set start and end cells
        boardStateCells[width - 1][height - 2].setIsSolid(0);
        boardStateCells[0][1].setIsSolid(0);
        cells[0][1].setBackground(Color.GREEN);
        this.boardStateCells = boardStateCells;
        boardStateCells[width - 1][height - 2].setContainEndCell(1);
        cells[width - 1][height - 2].setBackground(Color.ORANGE);

        // shade in solid cells
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (boardStateCells[x][y].getIsSolid() == 1) {
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
            return (this.boardStateCells[x][y].getIsSolid());
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

    public ArrayList<Rewards> getReward_R() { return rewards_R; }
    public ArrayList<Punishment> getPunishments() { return punishments;}
}
