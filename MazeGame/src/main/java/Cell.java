public class Cell extends Position{
    private String defaultMarker;
    private String marker;

    public Cell(int x, int y, String defaultMarker) {
        super(x, y);
        this.defaultMarker = defaultMarker;
        this.marker = defaultMarker;
    }

    public String getDefaultMarker() {
        return defaultMarker;
    }

    public void setDefaultMarker(String defaultMarker) {
        this.defaultMarker = defaultMarker;
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
