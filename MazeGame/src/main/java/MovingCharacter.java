abstract class MovingCharacter extends Position{
    protected MovingCharacter(int x, int y) {
        super(x, y);
    }

    protected void moveWest(GameBoard board) {
        int nextX = this.getX() - 1;
        if((nextX >= 0) && (board.getCellIsSolid(nextX, this.getY()) == 0)) {
            this.setX(nextX);
        }
    }

    protected void moveEast(GameBoard board) {
        int nextX = this.getX() + 1;
        if((nextX < board.getWidth()) && (board.getCellIsSolid(nextX, this.getY()) == 0)) {
            this.setX(nextX);
        }
    }

    protected void moveNorth(GameBoard board) {
        int nextY = this.getY() - 1;
        if((nextY >= 0) && (board.getCellIsSolid(this.getX(), nextY) == 0)) {
            this.setY(nextY);
        }
    }

    protected void moveSouth(GameBoard board) {
        int nextY = this.getY() + 1;
        if((nextY < board.getHeight()) && (board.getCellIsSolid(this.getX(), nextY) == 0)) {
            this.setY(nextY);
        }
    }
}
