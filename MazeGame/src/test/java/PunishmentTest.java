import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PunishmentTest {

    @Test
    public void createPunishmentTest(){
        Punishment punishment;
        try {
            punishment = new Punishment(3, 5);
            assertEquals(3,punishment.getX());
            assertEquals(5,punishment.getY());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Punishment read image fail");
        }
    }

    @Test
    public void collectPunishmentTest(){
        Punishment punishment;
        Board board = Board.getInstance();
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        BoardState boardState = BoardState.getInstance();

        punishment = boardState.getPunishments().get(0);
        scoreBoard.updateScore(10);//set score to 10
        punishment.collectedPunishment();//collected punishment score-5

        assertEquals(5,scoreBoard.getScore());// 10-5 = 5
    }
}
