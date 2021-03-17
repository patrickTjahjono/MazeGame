public class Cell extends Position {
    private int isSolid;

    public Cell(int x, int y, int isSolid) {
        super(x, y);
        this.isSolid = isSolid;
    }

    public int getIsSolid() {
        return isSolid;
    }

    public void setIsSolid(int isSolid) {
        this.isSolid = isSolid;
    }
}
