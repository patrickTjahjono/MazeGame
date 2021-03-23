import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Enemy extends MovingCharacter{
    public Enemy(int x, int y) throws IOException
    {
        super(x, y);
        Image image = ImageIO.read(new File("src/ghost.png"));
        this.label = new JLabel(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(25, 25, Image.SCALE_FAST)));

        // set enemy on Board at position (x, y)
        Board board = Board.getInstance();
        board.getCells()[x][y].add(label);
    }

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
            squaredDistances[0] = boardState.calculateSquaredDistance(enemyX - 1, enemyY, playerX, playerY);
            squaredDistances[1] = boardState.calculateSquaredDistance(enemyX + 1, enemyY, playerX, playerY);
            squaredDistances[2] = boardState.calculateSquaredDistance(enemyX, enemyY - 1, playerX, playerY);
            squaredDistances[3] = boardState.calculateSquaredDistance(enemyX, enemyY + 1, playerX, playerY);

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

    public void move() {
        BoardState boardState = BoardState.getInstance();
        Board board = Board.getInstance();
        Player player = Player.getInstance();
        String bestMove = getBestMove();

        if (boardState.boardStateCells[this.getX()][this.getY()].getContainsReward() == 1) {
            leave_reward_R();
        } else if (boardState.boardStateCells[this.getX()][this.getY()].getContainsPunishment() == 1) {
            leave_punishment();
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

        if (boardState.boardStateCells[this.getX()][this.getY()].getContainsReward() == 1) {
            touch_reward_R();
        } else if (boardState.boardStateCells[this.getX()][this.getY()].getContainsPunishment() == 1) {
            touch_punishment();
        }
    }

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
