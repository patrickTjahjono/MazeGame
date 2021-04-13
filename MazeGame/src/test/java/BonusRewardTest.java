import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*1. Bonus reward attempts to spawn when
        there is no existing bonus reward (should spawn a new one)
        a bonus reward already exists (should not spawn a new one)

  2. Bonus reward expires (check if the bonus reward has been removed)
*/

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class BonusRewardTest {

    @Test
    void createBR(){
        BonusReward BR;
        BoardState boardState = BoardState.getInstance();
        if (boardState.getHasBonusReward() == 0) {
            try {
                BR = new BonusReward(7, 8);
                boardState.setBonusReward(BR);
                boardState.setHasBonusReward(1);
                assertEquals(7, boardState.getBonusReward().getX());
                assertEquals(8, boardState.getBonusReward().getY());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Bonus Reward read image fail");
            }
        }
    }

    @Test
    void createBRWithExistingBR(){
        BonusReward BR;
        BoardState boardState = BoardState.getInstance();
        try {
            // set up a bonus reward at (7, 8)
            BR = new BonusReward(7, 8);
            boardState.setBonusReward(BR);
            boardState.setHasBonusReward(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Bonus Reward read image fail");
        }

        if (boardState.getHasBonusReward() == 1) {
            // set up another bonus reward
            boardState.spawnBR();
            // test if the bonus reward is still at (7, 8)
            assertEquals(7, boardState.getBonusReward().getX());
            assertEquals(8, boardState.getBonusReward().getY());
        }
    }
    @Test
    void checkExpire() {
        BonusReward BR;
        BoardState boardState = BoardState.getInstance();
        try {
            // set up a bonus reward at (7, 8)
            BR = new BonusReward(7, 8);
            boardState.setBonusReward(BR);
            boardState.setHasBonusReward(1);

            TimeCounter timeCounter = TimeCounter.getInstance();
            for (int i = 0; i <= BR.getExpiresAfter(); i++)
                timeCounter.updateTime();
            boardState.checkBonusRewardExpiration();

            assertEquals(0, boardState.getHasBonusReward());
            // assertEquals(null, boardState.getBonusRewards());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Bonus Reward read image fail");
        }
    }

    @Test
    void CollectBRTest(){
        BonusReward BR;
        BoardState boardState = BoardState.getInstance();
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        Player player = Player.getInstance();

        try{
            BR = new BonusReward(7,8);
            boardState.setBonusReward(BR);
            boardState.setHasBonusReward(1);
            player.setX(7);
            player.setY(8);
            player.touch_bonus_reward();
            assertEquals(BR.getX(),player.getX());
            assertEquals(BR.getY(),player.getY());

            assertEquals(0, boardState.boardStateCells[player.getX()][player.getY()].getContainsBonusReward());
        }
        catch (IOException e){
            System.out.print("failed");
        }

    }

}

