import org.junit.jupiter.api.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerTest {

    @Test
    @Order(1)
    void moveToSolidCell() {
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        Player player = Player.getInstance();

        // reset position
        player.setX(0);
        player.setY(1);

        // move to the solid cell above the starting cell
        player.moveNorth();
        assertEquals(0, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    @Order(2)
    void moveToEmptyCell() {
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        Player player = Player.getInstance();

        // reset position
        player.setX(0);
        player.setY(1);

        // move to the empty cell to the right of the starting cell
        player.moveEast();
        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    @Order(3)
    void reachEndSomeRegularRewardsRemaining() {
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        Player player = Player.getInstance();

        player.setX(boardState.getWidth() - 2);
        player.setY(boardState.getHeight() - 2);
        player.moveEast();
        player.move();
        assertEquals(false, player.isAtEnd);
        assertEquals(1, board.getContinue_game());
    }

    @Test
    @Order(4)
    void reachEndAllRegularRewardsCollected() {
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        Player player = Player.getInstance();
        ArrayList<Rewards> rewards = boardState.getReward_R();
        for (Rewards reward : rewards) {
            int rewardX = reward.getX();
            int rewardY = reward.getY();
            reward.collectedRR();
            boardState.boardStateCells[rewardX][rewardY].setContainsReward(0);
        }

        player.setX(boardState.getWidth() - 2);
        player.setY(boardState.getHeight() - 2);
        player.moveEast();
        player.move();

        assertEquals(1, boardState.boardStateCells[player.getX()][player.getY()].getContainEndCell());
        assertEquals(true, player.isAtEnd);
        assertEquals(0, board.getContinue_game());
    }

    @Test
    @Order(5)
    void touchReward() {
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        Player player = Player.getInstance();

        // reset position
        player.setX(0);
        player.setY(1);

        JLabel actualLabel;
        try {
            ArrayList<Rewards> rewards = boardState.getReward_R();
            Rewards reward = new Rewards(1, 1);
            boardState.boardStateCells[1][1].setContainsReward(1);
            rewards.add(reward);
            player.moveEast();
            player.move();

            try {
                actualLabel = (JLabel) board.getCells()[1][1].getComponent(0);
            }
            catch (Exception e) {
                actualLabel = null;
            }
            assertEquals(player.getX(), reward.getX());
            assertEquals(player.getY(), reward.getY());
            assertEquals(0, boardState.boardStateCells[1][1].getContainsReward());
            //assertEquals(player.getLabel(), actualLabel);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to read reward image.");
        }
    }

    @Test
    @Order(6)
    void leaveReward() {
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        Player player = Player.getInstance();

        // reset position
        player.setX(0);
        player.setY(1);

        JLabel actualLabel;
        try {
            ArrayList<Rewards> rewards = boardState.getReward_R();
            Rewards reward = new Rewards(2, 1);
            boardState.boardStateCells[2][1].setContainsReward(1);
            rewards.add(reward);
            player.moveEast();
            player.move();
            player.moveEast();
            player.move();
            player.moveEast();
            player.move();

            try {
                actualLabel = (JLabel) board.getCells()[2][1].getComponent(0);
            }
            catch (Exception e) {
                actualLabel = null;
            }
            assertEquals(0, boardState.boardStateCells[2][1].getContainsReward());
            //assertEquals(null, actualLabel);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to read reward image.");
        }
    }

    @Test
    @Order(7)
    void touchPunishmentNonNegativeScore() {
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        Player player = Player.getInstance();
        board.setContinue_game(1);

        // reset position
        player.setX(0);
        player.setY(1);

        JLabel actualLabel;
        try {
            ArrayList<Punishment> punishments = boardState.getPunishments();
            Punishment punishment = new Punishment(3, 1);
            boardState.boardStateCells[3][1].setContainsPunishment(1);
            punishments.add(punishment);
            player.moveEast();
            player.move();
            player.moveEast();
            player.move();
            player.moveEast();
            player.move();

            try {
                actualLabel = (JLabel) board.getCells()[3][1].getComponent(0);
            }
            catch (Exception e) {
                actualLabel = null;
            }
            assertEquals(player.getX(), punishment.getX());
            assertEquals(player.getY(), punishment.getY());
            assertEquals(0, boardState.boardStateCells[3][1].getContainsPunishment());
            //assertEquals(player.getLabel(), actualLabel);
            assertEquals(1, board.getContinue_game());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to read Punishment image.");
        }
    }

    @Test
    @Order(8)
    void touchPunishmentNegativeScore() {
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        Player player = Player.getInstance();
        board.setContinue_game(1);
        scoreBoard.updateScore(-scoreBoard.getScore());

        // reset position
        player.setX(0);
        player.setY(1);

        JLabel actualLabel;
        try {
            ArrayList<Punishment> punishments = boardState.getPunishments();
            Punishment punishment = new Punishment(4, 1);
            boardState.boardStateCells[4][1].setContainsPunishment(1);
            punishments.add(punishment);
            player.moveEast();
            player.move();
            player.moveEast();
            player.move();
            player.moveEast();
            player.move();
            player.moveEast();
            player.move();

            try {
                actualLabel = (JLabel) board.getCells()[4][1].getComponent(0);
            }
            catch (Exception e) {
                actualLabel = null;
            }
            assertEquals(player.getX(), punishment.getX());
            assertEquals(player.getY(), punishment.getY());
            assertEquals(0, boardState.boardStateCells[4][1].getContainsPunishment());
            assertEquals(player.getLabel(), actualLabel);
            assertEquals(0, board.getContinue_game());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to read Punishment image.");
        }
    }

    @Test
    @Order(9)
    void touchEnemy() {
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        Player player = Player.getInstance();
        board.setContinue_game(1);

        // reset position
        player.setX(0);
        player.setY(1);

        try {
            Enemy enemy = new Enemy(1, 1);
            enemy.move();

            assertEquals(0, board.getContinue_game());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to read Punishment image.");
        }
    }
}
