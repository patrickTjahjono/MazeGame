import javax.swing.*;
import java.awt.*;

abstract class MovingCharacter extends Position{
    private JLabel label;

    protected MovingCharacter(int x, int y, String label) {
        super(x, y);
        this.label = new JLabel(label);
        this.label.setFont(new Font("Arial",1,20));
    }

    public JLabel getLabel() {
        return label;
    }

    protected void moveWest(Board board, BoardState boardState) {
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

    protected void moveEast(Board board, BoardState boardState) {
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

    protected void moveNorth(Board board, BoardState boardState) {
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

    protected void moveSouth(Board board, BoardState boardState) {
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
