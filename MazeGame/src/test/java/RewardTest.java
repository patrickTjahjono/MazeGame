import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
//import static org.assertj.core.api.Assertions.*;


public class RewardTest {
    
    @Test
    public void createReward() {
        try{
            Rewards reward = new Rewards(7,5);
            assertEquals(7, reward.getX());
            assertEquals(5, reward.getY());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed to create reward");
        }
    }

    @Test
    public void collectedRewardTest() {
        Rewards reward;
        Board board = Board.getInstance();
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        BoardState boardState = BoardState.getInstance();

        reward = boardState.getReward_R().get(0);
        reward.collectedRR();
        JLabel RR;
        try{
            RR = (JLabel) board.getCells()[reward.getX()][reward.getY()].getComponent(0);
            assertNotNull(RR);
            assertEquals(5, scoreBoard.getScore());
        } catch (Exception e) {
            RR = null;
            //e.printStackTrace();

        }
        assertNull(RR);

    }

    @Test
    public void TouchRewardTest() {
        Rewards reward;
        Player player = Player.getInstance();
        //Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        //JLabel RR;
        try {
            reward = new Rewards(1, 1);
            //RR = (JLabel) board.getCells()[1][1].getComponent(0);
            player.setX(1);
            player.setY(1);
            player.touch_reward_R();
            assertEquals(reward.getX(), player.getX());
            assertEquals(reward.getY(), player.getY());
            //assertNull(reward.getLabel());

            assertEquals(0, boardState.boardStateCells[player.getX()][player.getY()].getContainsReward());
                        

        } catch (IOException e) {
            System.out.print("failed");
        }
    }
}
