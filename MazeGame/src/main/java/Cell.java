public class Cell extends Position {
    private String defaultMarker;
    private String marker;
    private int isSolid;

    public Cell(int x, int y, String defaultMarker, int isSolid) {
        super(x, y);
        this.defaultMarker = defaultMarker;
        this.marker = defaultMarker;
        this.isSolid = isSolid;
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

    public int getIsSolid() {
        return isSolid;
    }

    public void setIsSolid(int isSolid) {
        this.isSolid = isSolid;
    }

    @Override
    public String toString() {
        return marker;
    }
}
