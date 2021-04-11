import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlayerTest {
    @Test
    @Order(1)
    void moveToSolidCell() {
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        Player player = Player.getInstance();

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

    /*
    @Test
    void testJLabel() {
        Board board = Board.getInstance();
        Player player = Player.getInstance();
        JLabel expectedLabel;
        try {
            // should successfully get the Player's JLabel from the JPanel at [0][1]
            expectedLabel = (JLabel)board.getCells()[0][1].getComponent(0);

            // should fail and set expectedLabel as null
            //expectedLabel = (JLabel)board.getCells()[1][1].getComponent(0);
        } catch (Exception e) {
            expectedLabel = null;
        }
        assertEquals(expectedLabel, player.getLabel());
    }
    */
}
