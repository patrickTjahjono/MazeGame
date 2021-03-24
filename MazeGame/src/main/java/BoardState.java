import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * BoardState stores information about the states of each (x, y) position
 * on the Board. It also creates and keeps track of the start cell, end cell,
 * walls, punishments and regular rewards.
 */
public class BoardState {
    private static BoardState instance = null;
    private ArrayList<Punishment> punishments = new ArrayList<>();
    private ArrayList<Rewards> rewards_R = new ArrayList<>();
    private BonusReward bonusReward = null;
    private int hasBonusReward = 0;
    private int width, height;
    public Cell[][] boardStateCells;

    /**
     * @return the existing instance of BoardState or a new instance of BoardState if one does not exist.
     */
    public static BoardState getInstance() {
        if (instance == null)
            instance = new BoardState();
        return instance;
    }

    /**
     * Create a new BoardState to keep track of each (x, y) position's state. BoardState consists of
     * ArrayLists of Punishment and Rewards, a BonusReward along with a 2D array of Cells that store
     * information about each (x, y) position on the Board. It also records the width (20) and
     * height (10) of the Board and keeps track of whether there is a BonusReward in existence.
     */
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
        int minNumPunishments = 2;
        int numPunishments = 0;
        while (numPunishments < minNumPunishments) {
            for (int y = 2; y < height - 1; y++) {
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
        int maxNumRewards = 5;
        int minNumRewards = 2;
        int numRewards = 0;
        while (numRewards < minNumRewards) {
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

        // set start and end cells
        JPanel[][] cells = board.getCells();
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

    /**
     * @return width of the Board in Cells
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return height of the Board in Cells
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param x the x coordinate of the Cell to be checked - is limited to the interval [0, 19]
     * @param y the y coordinate of the Cell to be checked - is limited to the interval [0, 9]
     * @return whether the Cell is solid (1) or not solid (0) or does not exist/is out of range (-1)
     */
    public int getCellIsSolid(int x, int y) {
        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            return (this.boardStateCells[x][y].getIsSolid());
        } else {
            return -1;
        }
    }

    /**
     * @param x the x coordinate of the Cell to be checked - is limited to the interval [0, 19]
     * @param y the y coordinate of the Cell to be checked - is limited to the interval [0, 9]
     * @return whether the Cell contains an Enemy (1), does not contain an Enemy (0)
     *         or does not exist/is out of range (-1)
     */
    public int getCellContainsEnemy(int x, int y) {
        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            return (this.boardStateCells[x][y].getContainsEnemy());
        } else {
            return -1;
        }
    }

    /**
     * @param bonusReward newly spawned BonusReward
     */
    public void setBonusReward(BonusReward bonusReward) {
        this.bonusReward = bonusReward;
    }

    /**
     * @return whether there is a BonusReward in existence
     */
    public int getHasBonusReward() {
        return hasBonusReward;
    }

    /**
     * @param hasBonusReward whether there is a BonusReward in existence
     */
    public void setHasBonusReward(int hasBonusReward) {
        this.hasBonusReward = hasBonusReward;
    }

    public void spawnBR() {
        BoardState boardState = BoardState.getInstance();
        Board board = Board.getInstance();
        Random random = new Random();
        if (boardState.getHasBonusReward() == 0) {
            if (random.nextFloat() < 0.05) {
                int bonusRewardX = random.nextInt(boardState.getWidth());
                int bonusRewardY = random.nextInt(boardState.getHeight());
                if (boardState.getCellIsSolid(bonusRewardX, bonusRewardY) == 0 &&
                    boardState.boardStateCells[bonusRewardX][bonusRewardY].getContainsRewardOrPunishment() == 0) {
                    try {
                        BonusReward BR = new BonusReward(bonusRewardX, bonusRewardY);
                        boardState.boardStateCells[bonusRewardX][bonusRewardY].setContainsBonusReward(1);
                        boardState.setBonusReward(BR);
                        boardState.setHasBonusReward(1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Check whether the BonusReward has expired. Do nothing if a BonusReward does not exist
     * or has not expired.
     */
    public void checkBonusRewardExpiration() {
        TimeCounter turnCounter = TimeCounter.getInstance();
        BoardState boardState = BoardState.getInstance();
        Board board = Board.getInstance();
        BonusReward BR = boardState.getBonusReward();
        int currentTurn = turnCounter.getTurn();
        if (BR != null && currentTurn > BR.getExpiresAfter()) {
            int bonusRewardX = BR.getX();
            int bonusRewardY = BR.getY();
            board.getCells()[bonusRewardX][bonusRewardY].remove(BR.getLabel());
            boardState.boardStateCells[bonusRewardX][bonusRewardY].setContainsBonusReward(0);
            boardState.setHasBonusReward(0);
        }
    }

    /**
     * Used to check whether an enemy has caught up the the player (if it is the
     * enemy's turn and the squared distance between the player and the enemy is 1)
     *
     * @param x1 the x coordinate of an enemy - is limited to the interval [0, 19]
     * @param y1 the y coordinate of an enemy - is limited to the interval [0, 9]
     * @param x2 the x coordinate of the player - is limited to the interval [0, 19]
     * @param y2 the y coordinate of the player - is limited to the interval [0, 9]
     * @return
     */
    public int calculateSquaredDistance(int x1, int y1, int x2, int y2) {
        int squaredDistance;

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

    /**
     * Used in the enemy's path finding algorithm. This method is identical to
     * calculateSquaredDistance with the exception of an additional check which
     * ensures that enemies will not move to Cells that contain another enemy.
     *
     * @param x1 the x coordinate of an enemy - is limited to the interval [0, 19]
     * @param y1 the y coordinate of an enemy - is limited to the interval [0, 9]
     * @param x2 the x coordinate of the player - is limited to the interval [0, 19]
     * @param y2 the y coordinate of the player - is limited to the interval [0, 9]
     * @return
     */
    public int calculateSquaredDistanceWithEnemyCheck(int x1, int y1, int x2, int y2) {
        int squaredDistance;

        int cell1IsSolid = getCellIsSolid(x1, y1);
        int cell2IsSolid = getCellIsSolid(x2, y2);
        int cell1ContainsEnemy = getCellContainsEnemy(x1, y1);

        // Set squaredDistance to 2147483647 if a cell is solid, out of bounds or contains an enemy
        if ((cell1IsSolid != 0) || (cell1ContainsEnemy == 1) || (cell2IsSolid != 0)) {
            squaredDistance = Integer.MAX_VALUE;
        } else {
            int differenceX = (x2 - x1);
            int differenceY = (y2 - y1);
            squaredDistance = differenceX * differenceX + differenceY * differenceY;
        }
        return squaredDistance;
    }

    /**
     * @return all generated Rewards
     */
    public ArrayList<Rewards> getReward_R() { return rewards_R; }


    /**
     * @return all generated Punishments
     */
    public ArrayList<Punishment> getPunishments() { return punishments; }

    /**
     * @return the generated BonusReward
     */
    public BonusReward getBonusReward() { return this.bonusReward; }
}
