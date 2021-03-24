import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 
 * The abstract MovingCharacter controls the movement of the player and/or enemy by altering it's cell position on board. 
 * Player and enemies are restricted from moving onto cells that is solid, and no object is created from MovingCharacter.
 */
abstract class MovingCharacter extends Position{
    protected JLabel label;

    /**
     * 
     * @param x the x coordinate of the player in the range of [0, 19] inherits from Position.
     * @param y the y coordinate of the player in the range of [0, 9] inherits from Position.
     * @throws IOException if (x, y) coordinates not within range
     */
    protected MovingCharacter(int x, int y) throws IOException {
        super(x, y);
    }

    /**
     * 
     * @return JLablel of player 
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * The method moveWest gets the instance of Board and BoardState and 
     * moves the position of the object to its left by 1. 
     */
    protected void moveWest() {
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        int currentX = this.getX();
        int currentY = this.getY();
        int nextX = currentX - 1;

        if((nextX >= 0) && (boardState.getCellIsSolid(nextX, currentY) == 0)) {
            JPanel[][] cells = board.getCells();
            cells[currentX][currentY].remove(this.label);
            cells[nextX][currentY].add(this.label); // update JFrame
            this.setX(nextX); // update x position
        }
    }

    /**
     * The method moveEast gets the instance of Board and BoardState and 
     * moves the position of the obbject to its right by 1. 
     */
    protected void moveEast() {
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        int currentX = this.getX();
        int currentY = this.getY();
        int nextX = currentX + 1;
        if((nextX < boardState.getWidth()) && (boardState.getCellIsSolid(nextX, currentY) == 0)) {
            JPanel[][] cells = board.getCells();
            cells[currentX][currentY].remove(this.label);
            cells[nextX][currentY].add(this.label);
            this.setX(nextX);
        }
    }

    /**
     * The method moveNorth gets the instance of Board and BoardState and 
     * moves the position of the object down by 1. 
     */
    protected void moveNorth() {
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        int currentX = this.getX();
        int currentY = this.getY();
        int nextY = currentY - 1;
        if((nextY >= 0) && (boardState.getCellIsSolid(currentX, nextY) == 0)) {
            JPanel[][] cells = board.getCells();
            cells[currentX][currentY].remove(this.label);
            cells[currentX][nextY].add(this.label);
            this.setY(nextY);
        }
    }

    /**
     * The method moveWest gets the instance of Board and BoardState and 
     * moves the position of the object up by 1. 
     */
    protected void moveSouth() {
        Board board = Board.getInstance();
        BoardState boardState = BoardState.getInstance();
        int currentX = this.getX();
        int currentY = this.getY();
        int nextY = currentY + 1;
        if((nextY < boardState.getHeight()) && (boardState.getCellIsSolid(currentX, nextY) == 0)) {
            JPanel[][] cells = board.getCells();
            cells[currentX][currentY].remove(this.label);
            cells[currentX][nextY].add(this.label);
            this.setY(nextY);
        }
    }
}
