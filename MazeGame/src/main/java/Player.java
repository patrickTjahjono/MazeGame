import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class Player extends MovingCharacter implements KeyListener {
    private static Player instance = null;
    private String nextMove = "None";
    public boolean isAtEnd = false;

    public static Player getInstance() {
        if (instance == null) {
            try {
                instance = new Player(0, 1);
                return instance;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private Player(int x, int y) throws IOException
    {
        super(x, y);
        Image image = ImageIO.read(new File("src/playertest.png"));
        this.label = new JLabel(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(25, 25, Image.SCALE_FAST)));

        // set player on Board at position (x, y)
        Board board = Board.getInstance();
        board.getCells()[x][y].add(label);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE) // pause using esc
        {
            if(!Board.getInstance().getPause_game())
                Board.getInstance().setPause_game(true);
            else{
                Board.getInstance().setPause_game(false);
            }
        }
        else if(Board.getInstance().getPause_game()) // will skip movement when its paused
            return;

        if(key == KeyEvent.VK_UP) { // move up
            this.nextMove = "n";
        } else if (key == KeyEvent.VK_DOWN) { // move down
            this.nextMove = "s";
        } else if (key == KeyEvent.VK_LEFT) { // move left
            this.nextMove = "w";
        } else if (key == KeyEvent.VK_RIGHT) { //move right
            this.nextMove = "e";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public void move() {
        BoardState boardState = BoardState.getInstance();
        if (this.nextMove.equals("w")) {
            this.moveWest();
        } else if (this.nextMove.equals("e")) {
            this.moveEast();
        } else if (this.nextMove.equals("n")) {
            this.moveNorth();
        } else if (this.nextMove.equals("s")) {
            this.moveSouth();
        }
        this.nextMove = "None";

        if (boardState.boardStateCells[this.getX()][this.getY()].getContainsReward() == 1) {
            touch_reward_R();
        }

        if (boardState.boardStateCells[this.getX()][this.getY()].getContainsPunishment() == 1) {
            touch_punishment();
        }

        if (boardState.boardStateCells[this.getX()][this.getY()].getContainsBonusReward() == 1) {
            touch_bonus_reward();
        }

        // When player reaches end cell sets continue_game to zero
        if (boardState.boardStateCells[this.getX()][this.getY()].getContainEndCell() == 1) {
            if (this.allRewardsCollected() == 1) {
                Board.getInstance().setContinue_game(0);
                this.isAtEnd = true;
            }
        }
    }

    public void touch_reward_R(){
        BoardState boardState = BoardState.getInstance();
        ArrayList<Rewards> rewards = boardState.getReward_R();
        for (Rewards reward : rewards) {
            int rewardX = reward.getX();
            int rewardY = reward.getY();
            if (rewardX == this.getX() && rewardY == this.getY()) {
                reward.collectedRR();
                boardState.boardStateCells[rewardX][rewardY].setContainsReward(0);
            }
        }
    }

    public void touch_bonus_reward(){
        BoardState boardState = BoardState.getInstance();
        BonusReward BR = boardState.getBonusReward();
        int bonusRewardX = BR.getX();
        int bonusRewardY = BR.getY();
        if (bonusRewardX == this.getX() && bonusRewardY == this.getY()) {
            BR.collectedBR();
            boardState.boardStateCells[bonusRewardX][bonusRewardY].setContainsBonusReward(0);
        }

    }

    public void touch_punishment(){
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        BoardState boardState = BoardState.getInstance();
        Board board = Board.getInstance();
        ArrayList<Punishment> punishments = boardState.getPunishments();
        for (Punishment punishment : punishments) {
            int punishmentX = punishment.getX();
            int punishmentY = punishment.getY();
            if (punishmentX == this.getX() && punishmentY == this.getY()) {
                punishment.collectedPunishment();
                boardState.boardStateCells[punishmentX][punishmentY].setContainsPunishment(0);
            }
        }

        // end game if score is negative
        if (scoreBoard.getScore() < 0) {
            Board.getInstance().setContinue_game(0);
        }
    }

    public int allRewardsCollected() {
        BoardState boardState = BoardState.getInstance();
        ArrayList<Rewards> rewards = boardState.getReward_R();
        int numCollectedRewards = 0;
        for (Rewards reward : rewards) {
            int rewardX = reward.getX();
            int rewardY = reward.getY();
            if (boardState.boardStateCells[rewardX][rewardY].getContainsReward() == 0) {
                numCollectedRewards++;
            }
        }
        if (numCollectedRewards == rewards.size()) {
            return 1;
        } else {
            return 0;
        }
    }
}
