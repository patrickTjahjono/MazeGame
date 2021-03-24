/**
 * an Abstract class that is inherited by objects so that it able to store a position in x and y coordinate
 */
abstract class Position {
    private int x, y;

    /**
     * Assign a Position from the information given (x, y) position
     *
     * @param x       is contained within the interval [0, 19] as dictated by Board and BoardState
     * @param y       is contained within the interval [0, 9] as dictated by Board and BoardState
     */
    protected Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the position in x axis/coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @param x assign new x coordinate to the member variable x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the position in y axis/coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * @param y assign new y coordinate to the member variable y
     */
    public void setY(int y) {
        this.y = y;
    }
}
