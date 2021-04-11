import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * Enemy class create an GUI object on the board that will hunt down the player by calculating the squared
 * distance from the player. It will also override a cell or position of reward or punishment if it occupy
 * the same position
 */
public class Enemy extends MovingCharacter{

    /**
     * Create Enemy object on the board at (x,y) position
     *
     * @param x       is contained within the interval [0, 19] as dictated by Board and BoardState
     * @param y       is contained within the interval [0, 9] as dictated by Board and BoardState
     */
    public Enemy(int x, int y) throws IOException
    {
        super(x, y);
        Image image = ImageIO.read(new File("src/ghost.png"));
        this.label = new JLabel(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(25, 25, Image.SCALE_FAST)));

        // set enemy on Board at position (x, y)
        Board board = Board.getInstance();
        board.getCells()[x][y].add(label);
    }

    /**
     * Checking for the best move from every move that the Enemy can take, checking is done by calculating
     * the squared distance from enemy next possible move with player's position using BoardState method
     * calculateSquaredDistanceWithEnemyCheck i.e. will it get enemy to the closest position to player
     * if it goes up if not then check the others like left, right, down. Once everything
     * has been compared, function will return the best move
     *
     * @return String 'w' for west or left, 'e' for east or right, 'n' for north or up, 's' for down or south
     */
    private String getBestMove() {
        BoardState boardState = BoardState.getInstance();
        try {
            Player player = Player.getInstance();
            String[] moves = {"w", "e", "n", "s"};
            int[] squaredDistances = new int[4];

            int playerX = player.getX();
            int playerY = player.getY();
            int enemyX = this.getX();
            int enemyY = this.getY();

            int bestMoveDistance = Integer.MAX_VALUE;
            int bestMoveIndex = -1;

            // squared distances from each cell (adjacent of the enemy) to the player
            squaredDistances[0] = boardState.calculateSquaredDistanceWithEnemyCheck(enemyX - 1, enemyY, playerX, playerY);
            squaredDistances[1] = boardState.calculateSquaredDistanceWithEnemyCheck(enemyX + 1, enemyY, playerX, playerY);
            squaredDistances[2] = boardState.calculateSquaredDistanceWithEnemyCheck(enemyX, enemyY - 1, playerX, playerY);
            squaredDistances[3] = boardState.calculateSquaredDistanceWithEnemyCheck(enemyX, enemyY + 1, playerX, playerY);

            for (int i = 0; i < 4; i++) {
                if (bestMoveDistance > squaredDistances[i]) {
                    bestMoveDistance = squaredDistances[i];
                    bestMoveIndex = i;
                }
            }
            return moves[bestMoveIndex];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "None";
    }

    /**
     * Calculating the current squared distance from Enemy to Player using  BoardState method calculateSquaredDistanceWithEnemyCheck
     *
     * @return int calculated squared distance
     */
    public int getCurrentSquaredDistanceToPlayer() {
        BoardState boardState = BoardState.getInstance();
        Player player = Player.getInstance();
        int playerX = player.getX();
        int playerY = player.getY();
        int enemyX = this.getX();
        int enemyY = this.getY();

        int currentSquaredDistanceToPlayer = boardState.calculateSquaredDistance(enemyX, enemyY, playerX, playerY);

        return currentSquaredDistanceToPlayer;
    }

    /**
     * Moving the Enemy object so it will get close to Player object if the Player is not yet caught, if yes then the
     * game ends. The movement will also check if the path or next position taken will contain any other object like
     * reward or punishment, if yes then it will show on the board that the Enemy object is occupying that position
     * and once it moves the previous object occupying (reward or punishment) it will reappear on the board.
     * Once best Move is calculated by getBestMove() the movement will be directed by the inherited method
     * from MovingCharacter.
     *
     */
    public void move() {
        BoardState boardState = BoardState.getInstance();
        Board board = Board.getInstance();
        Player player = Player.getInstance();
        String bestMove = getBestMove();

        if (this.getCurrentSquaredDistanceToPlayer() <= 1) {
            Board.getInstance().setContinue_game(0);
        }

        boardState.boardStateCells[this.getX()][this.getY()].setContainsEnemy(0);
        if (boardState.boardStateCells[this.getX()][this.getY()].getContainsReward() == 1) {
            leave_reward_R();
        } else if (boardState.boardStateCells[this.getX()][this.getY()].getContainsPunishment() == 1) {
            leave_punishment();
        } else if (boardState.boardStateCells[this.getX()][this.getY()].getContainsBonusReward() == 1) {
            leave_bonus_reward();
        }

        if (bestMove.equals("w")) {
            this.moveWest();
        } else if (bestMove.equals("e")) {
            this.moveEast();
        } else if (bestMove.equals("n")) {
            this.moveNorth();
        } else if (bestMove.equals("s")) {
            this.moveSouth();
        }

        boardState.boardStateCells[this.getX()][this.getY()].setContainsEnemy(1);
        if (boardState.boardStateCells[this.getX()][this.getY()].getContainsReward() == 1) {
            touch_reward_R();
        } else if (boardState.boardStateCells[this.getX()][this.getY()].getContainsPunishment() == 1) {
            touch_punishment();
        } else if (boardState.boardStateCells[this.getX()][this.getY()].getContainsBonusReward() == 1) {
            touch_bonus_reward();
        }
    }

    /**
     * When the Enemy object touch reward or occupy the same position, it will remove the reward label so
     * that only Enemy label will be shown on the board
     *
     */
    public void touch_reward_R(){
        BoardState boardState = BoardState.getInstance();
        Board board = Board.getInstance();
        ArrayList<Rewards> rewards = boardState.getReward_R();
        for (Rewards reward : rewards) {
            int rewardX = reward.getX();
            int rewardY = reward.getY();
            if (rewardX == this.getX() && rewardY == this.getY()) {
                board.getCells()[rewardX][rewardY].remove(reward.getLabel());
            }
        }
    }

    /**
     * When the Enemy object leave position that is previously occupied by regular reward, it will re-add
     * the reward label so that it will be shown on the board
     *
     */
    public void leave_reward_R(){
        BoardState boardState = BoardState.getInstance();
        Board board = Board.getInstance();
        ArrayList<Rewards> rewards = boardState.getReward_R();
        for (Rewards reward : rewards) {
            int rewardX = reward.getX();
            int rewardY = reward.getY();
            if (rewardX == this.getX() && rewardY == this.getY()) {
                board.getCells()[rewardX][rewardY].add(reward.getLabel());
            }
        }
    }

    /**
     * When the Enemy object touch bonus reward or occupy the same position, it will remove the bonus reward label so
     * that only Enemy label will be shown on the board
     *
     */
    public void touch_bonus_reward(){
        BoardState boardState = BoardState.getInstance();
        Board board = Board.getInstance();
        BonusReward BR = boardState.getBonusReward();
        board.getCells()[BR.getX()][BR.getY()].remove(BR.getLabel());
    }

    /**
     * When the Enemy object leave position that is previously occupied by bonus reward, it will re-add
     * the bonus reward label so that it will be shown on the board
     *
     */
    public void leave_bonus_reward(){
        BoardState boardState = BoardState.getInstance();
        Board board = Board.getInstance();
        BonusReward BR = boardState.getBonusReward();
        board.getCells()[BR.getX()][BR.getY()].add(BR.getLabel());
    }

    /**
     * When the Enemy object touch punishment or occupy the same position, it will remove the punishment label so
     * that only Enemy label will be shown on the board
     *
     */
    public void touch_punishment(){
        BoardState boardState = BoardState.getInstance();
        Board board = Board.getInstance();
        ArrayList<Punishment> punishments = boardState.getPunishments();
        for (Punishment punishment : punishments) {
            int punishmentX = punishment.getX();
            int punishmentY = punishment.getY();
            if (punishmentX == this.getX() && punishmentY == this.getY()) {
                board.getCells()[punishmentX][punishmentY].remove(punishment.getLabel());
            }
        }
    }

    /**
     * When the Enemy object leave position that is previously occupied by punishment, it will re-add
     * the punishment label so that it will be shown on the board
     *
     */
    public void leave_punishment(){
        BoardState boardState = BoardState.getInstance();
        Board board = Board.getInstance();
        ArrayList<Punishment> punishments = boardState.getPunishments();
        for (Punishment punishment : punishments) {
            int punishmentX = punishment.getX();
            int punishmentY = punishment.getY();
            if (punishmentX == this.getX() && punishmentY == this.getY()) {
                board.getCells()[punishmentX][punishmentY].add(punishment.getLabel());
            }
        }
    }
}
