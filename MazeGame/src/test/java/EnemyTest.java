import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {
    @Test
    public void getCurrentSquaredDistanceToPlayerTest() {
        //create enemy in random empty cell
        boolean enemyCreated = false;
        Player player = Player.getInstance();
        Enemy enemy;
        Random random = new Random();
        BoardState boardState = BoardState.getInstance();
        int differenceX;
        int differenceY;
        for (int y = 1; y < boardState.getHeight() - 1; y++) {
            for (int x = 1; x < boardState.getWidth() - 1; x++) {
                if (boardState.boardStateCells[x][y].getContainsRewardOrPunishment() == 0 &&
                        boardState.boardStateCells[x][y].getIsSolid() == 0 &&
                        random.nextFloat() < 0.05) {
                    try {
                        enemy = new Enemy(x, y);
                        enemyCreated = true;
                        enemy.getCurrentSquaredDistanceToPlayer();

                        differenceX = (enemy.getX() - player.getX());
                        differenceY = (enemy.getY() - player.getY());

                        assertEquals(differenceX * differenceX + differenceY * differenceY, enemy.getCurrentSquaredDistanceToPlayer());
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Enemy read image fail");
                    }
                }
                if (enemyCreated)
                    break;
            }
            if (enemyCreated)
                break;
        }
    }

    @Test
    public void MoveTest(){
        Player player = Player.getInstance();
        Enemy enemy_Xaxis;
        Enemy enemy_Yaxis;
        BoardState boardState = BoardState.getInstance();
        try {
            enemy_Xaxis = new Enemy(19, 1);
            enemy_Yaxis = new Enemy(1, 9);
            enemy_Xaxis.move();
            enemy_Yaxis.move();

            assertEquals(1,enemy_Xaxis.getY());
            assertEquals(18,enemy_Xaxis.getX());
            assertEquals(1,enemy_Yaxis.getX());
            assertEquals(8,enemy_Yaxis.getY());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Enemy read image fail");
        }
    }

    @Test
    public void EnemyTouchRegularRewardTest() {
        Enemy enemy;
        Board board = Board.getInstance();
        Rewards reward;
        JLabel actualLabel = null;
        int Ex, Ey;
        int Rx, Ry;
        BoardState boardState = BoardState.getInstance();
        ArrayList<Rewards> rewards = boardState.getReward_R();
        for (int i = 0; i < rewards.size(); i++) {
            Rx = rewards.get(i).getX();
            Ry = rewards.get(i).getY();
            if (boardState.boardStateCells[Rx - 1][Ry].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Rx - 1][Ry].getIsSolid() == 0) {
                Ex = Rx - 1;
                Ey = Ry;
            } else if (boardState.boardStateCells[Rx + 1][Ry].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Rx + 1][Ry].getIsSolid() == 0) {
                Ex = Rx + 1;
                Ey = Ry;
            } else if (boardState.boardStateCells[Rx][Ry + 1].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Rx][Ry + 1].getIsSolid() == 0) {
                Ex = Rx;
                Ey = Ry + 1;
            } else if (boardState.boardStateCells[Rx][Ry - 1].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Rx][Ry - 1].getIsSolid() == 0) {
                Ex = Rx;
                Ey = Ry - 1;
            } else
                continue;
            reward = rewards.get(i);
            try {
                enemy = new Enemy(Ex, Ey);
                if (Ex == Rx - 1 && Ey == Ry)
                    enemy.moveEast();
                else if (Ex == Rx + 1 && Ey == Ry)
                    enemy.moveWest();
                else if (Ex == Rx && Ey == Ry + 1)
                    enemy.moveNorth();
                else if (Ex == Rx && Ey == Ry - 1)
                    enemy.moveSouth();
                enemy.touch_reward_R();
                try {
                    actualLabel = (JLabel) board.getCells()[reward.getX()][reward.getY()].getComponent(0);
                } catch (Exception e) {
                    actualLabel = null;
                }
                assertEquals(enemy.getX(), reward.getX());
                assertEquals(enemy.getY(), reward.getY());
                assertEquals(enemy.getLabel(), actualLabel);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Enemy read image fail");
            }
        }
    }

    @Test
    public void EnemyLeaveRegularRewardTest() {
        Enemy enemy;
        Board board = Board.getInstance();
        Rewards reward;
        JLabel actualLabel0 = null,actualLabel1 = null;
        int Rx, Ry;
        BoardState boardState = BoardState.getInstance();
        ArrayList<Rewards> rewards = boardState.getReward_R();
        try {
            reward = rewards.get(0);
            Rx = reward.getX();
            Ry = reward.getY();
            assertTrue(Rx<=18 && Rx >=1 && Ry >= 1 && Ry <= 8); // make sure punishment is on board

            enemy = new Enemy(Rx, Ry);
            board.getCells()[Rx][Ry].remove(reward.getLabel());
            enemy.leave_reward_R();
            if (boardState.boardStateCells[Rx - 1][Ry].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Rx - 1][Ry].getIsSolid() == 0) {
                enemy.moveWest();
            } else if (boardState.boardStateCells[Rx + 1][Ry].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Rx + 1][Ry].getIsSolid() == 0) {
                enemy.moveEast();
            } else if (boardState.boardStateCells[Rx][Ry + 1].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Rx][Ry + 1].getIsSolid() == 0) {
                enemy.moveSouth();
            } else if (boardState.boardStateCells[Rx][Ry - 1].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Rx][Ry - 1].getIsSolid() == 0) {
                enemy.moveNorth();
            }

            try {
                actualLabel0 = (JLabel) board.getCells()[reward.getX()][reward.getY()].getComponent(0);
                actualLabel1 = (JLabel) board.getCells()[reward.getX()][reward.getY()].getComponent(1);
            } catch (Exception e) {
                actualLabel1 = null;
            }
            assertTrue(enemy.getX() == reward.getX() || enemy.getY() == reward.getY());
            assertFalse(enemy.getX() == reward.getX() && enemy.getY() == reward.getY());
            assertEquals(null, actualLabel1);
            assertEquals(reward.getLabel(), actualLabel0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Enemy read image fail");
        }
    }

    @Test
    public void EnemyTouchPunishmentTest() {
        Enemy enemy;
        Board board = Board.getInstance();
        Punishment punishment;
        JLabel actualLabel = null;
        int Ex, Ey;
        int Px, Py;
        BoardState boardState = BoardState.getInstance();
        ArrayList<Punishment> punishments = boardState.getPunishments();
        for (int i = 0; i < punishments.size(); i++) {
            Px = punishments.get(i).getX();
            Py = punishments.get(i).getY();
            if (boardState.boardStateCells[Px - 1][Py].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Px - 1][Py].getIsSolid() == 0) {
                Ex = Px - 1;
                Ey = Py;
            } else if (boardState.boardStateCells[Px + 1][Py].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Px + 1][Py].getIsSolid() == 0) {
                Ex = Px + 1;
                Ey = Py;
            } else if (boardState.boardStateCells[Px][Py + 1].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Px][Py + 1].getIsSolid() == 0) {
                Ex = Px;
                Ey = Py + 1;
            } else if (boardState.boardStateCells[Px][Py - 1].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Px][Py - 1].getIsSolid() == 0) {
                Ex = Px;
                Ey = Py - 1;
            } else
                continue;
            punishment = punishments.get(i);
            try {
                enemy = new Enemy(Ex, Ey);
                if (Ex == Px - 1 && Ey == Py)
                    enemy.moveEast();
                else if (Ex == Px + 1 && Ey == Py)
                    enemy.moveWest();
                else if (Ex == Px && Ey == Py + 1)
                    enemy.moveNorth();
                else if (Ex == Px && Ey == Py - 1)
                    enemy.moveSouth();
                enemy.touch_punishment();
                try {
                    actualLabel = (JLabel) board.getCells()[punishment.getX()][punishment.getY()].getComponent(0);
                } catch (Exception e) {
                    actualLabel = null;
                }
                assertEquals(enemy.getX(), punishment.getX());
                assertEquals(enemy.getY(), punishment.getY());
                assertEquals(enemy.getLabel(), actualLabel);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Enemy read image fail");
            }
        }
    }

    @Test
    public void EnemyLeavePunishmentTest() {
        Enemy enemy;
        Board board = Board.getInstance();
        Punishment punishment;
        JLabel actualLabel0 = null,actualLabel1 = null;
        int Px, Py;
        BoardState boardState = BoardState.getInstance();
        ArrayList<Punishment> punishments = boardState.getPunishments();
        try {
            punishment = punishments.get(0);
            Px = punishment.getX();
            Py = punishment.getY();
            assertTrue(Px<=18 && Px >=1 && Py >= 1 && Py <= 8); // make sure punishment is on board

            enemy = new Enemy(Px, Py);
            board.getCells()[Px][Py].remove(punishment.getLabel());
            enemy.leave_punishment();
            if (boardState.boardStateCells[Px - 1][Py].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Px - 1][Py].getIsSolid() == 0) {
                enemy.moveWest();
            } else if (boardState.boardStateCells[Px + 1][Py].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Px + 1][Py].getIsSolid() == 0) {
                enemy.moveEast();
            } else if (boardState.boardStateCells[Px][Py + 1].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Px][Py + 1].getIsSolid() == 0) {
                enemy.moveSouth();
            } else if (boardState.boardStateCells[Px][Py - 1].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Px][Py - 1].getIsSolid() == 0) {
                enemy.moveNorth();
            }

            try {
                actualLabel0 = (JLabel) board.getCells()[punishment.getX()][punishment.getY()].getComponent(0);
                actualLabel1 = (JLabel) board.getCells()[punishment.getX()][punishment.getY()].getComponent(1);
            } catch (Exception e) {
                actualLabel1 = null;
            }
            assertTrue(enemy.getX() == punishment.getX() || enemy.getY() == punishment.getY());
            assertFalse(enemy.getX() == punishment.getX() && enemy.getY() == punishment.getY());
            assertEquals(null, actualLabel1);
            assertEquals(punishment.getLabel(), actualLabel0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Enemy read image fail");
        }
    }

    @Test
    public void EnemyTouchBonusRewardTest() {
        Enemy enemy;
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        BonusReward bonusReward;
        JLabel actualLabel = null;
        int Ex = 0, Ey = 0;
        int Bx, By;
        try {
            bonusReward = new BonusReward(1, 1);
            Bx = 1;
            By = 1;
            boardState.setBonusReward(bonusReward);
            if (boardState.boardStateCells[Bx + 1][By].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Bx + 1][By].getIsSolid() == 0) {
                Ex = Bx + 1;
                Ey = By;
            } else if (boardState.boardStateCells[Bx][By + 1].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Bx][By + 1].getIsSolid() == 0) {
                Ex = Bx;
                Ey = By + 1;
            }
            enemy = new Enemy(Ex, Ey);
            if (Ex == Bx + 1 && Ey == By)
                enemy.moveWest();
            else if (Ex == Bx && Ey == By + 1)
                enemy.moveNorth();
            enemy.touch_bonus_reward();
            try {
                actualLabel = (JLabel) board.getCells()[Bx][By].getComponent(0);
            } catch (Exception e) {
                actualLabel = null;
            }
            assertEquals(enemy.getX(), Bx);
            assertEquals(enemy.getY(), By);
            assertEquals(enemy.getLabel(), actualLabel);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("BonusReward read image fail");
        }
    }

    @Test
    public void EnemyLeaveBonusRewardTest() {
        Enemy enemy;
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        BonusReward bonusReward;
        JLabel actualLabel0 = null,actualLabel1 = null;
        int Bx, By;
        try {
            bonusReward = new BonusReward(1, 1);
            Bx = 1;
            By = 1;
            boardState.setBonusReward(bonusReward);

            enemy = new Enemy(Bx, By);
            board.getCells()[Bx][By].remove(bonusReward.getLabel());
            enemy.leave_bonus_reward();
            if (boardState.boardStateCells[Bx + 1][By].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Bx + 1][By].getIsSolid() == 0) {
                enemy.moveEast();
            } else if (boardState.boardStateCells[Bx][By + 1].getContainsRewardOrPunishment() == 0 &&
                    boardState.boardStateCells[Bx][By + 1].getIsSolid() == 0) {
                enemy.moveSouth();
            }

            try {
                actualLabel0 = (JLabel) board.getCells()[bonusReward.getX()][bonusReward.getY()].getComponent(0);
                actualLabel1 = (JLabel) board.getCells()[bonusReward.getX()][bonusReward.getY()].getComponent(1);
            } catch (Exception e) {
                actualLabel1 = null;
            }
            assertTrue(enemy.getX() == bonusReward.getX() || enemy.getY() == bonusReward.getY());
            assertFalse(enemy.getX() == bonusReward.getX() && enemy.getY() == bonusReward.getY());
            assertEquals(null, actualLabel1);
            assertEquals(bonusReward.getLabel(), actualLabel0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Enemy read image fail");
        }
    }
}

