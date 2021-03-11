public class Cell extends Position{
    private String marker;

    public Cell(int x, int y, String marker) {
        super(x, y);
        this.marker = marker;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    @Override
    public String toString() {
        return marker;
    }
}
