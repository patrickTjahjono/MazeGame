import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
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
        scoreBoard.updateScore(-scoreBoard.getScore());

        punishment = boardState.getPunishments().get(0);
        punishment.collectedPunishment();//collected punishment score-5

        JLabel actualLabel;
        try{
            actualLabel = (JLabel) board.getCells()[punishment.getX()][punishment.getY()].getComponent(0);
        }
        catch (Exception e) {
            actualLabel = null;
        }
        assertNotEquals(actualLabel, punishment.getLabel());
    }

    @Test
    public void TouchPunishmentTest(){
        Punishment punishment;
        Player player = Player.getInstance();
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        ScoreBoard scoreBoard = ScoreBoard.getInstance();
        scoreBoard.updateScore(-scoreBoard.getScore());

        JLabel actualLabel = null;
        try {
            punishment = new Punishment(1,1);

            player.moveEast();
            player.touch_punishment();

            assertEquals(punishment.getX(),player.getX());
            assertEquals(punishment.getY(),player.getY());
            try {
                actualLabel = (JLabel) board.getCells()[player.getX()][player.getY()].getComponent(0);
            }
            catch (Exception e) {
                actualLabel = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Punishment read image fail");
        }

        assertEquals(0,boardState.boardStateCells[player.getX()][player.getY()].getContainsPunishment());
        //assertEquals(player.getLabel(),actualLabel);

    }
}
