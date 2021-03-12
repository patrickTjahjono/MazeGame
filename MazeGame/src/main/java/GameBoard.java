public class GameBoard {
    private int width, height;
    public Cell[][] gameBoard;

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;

        Cell[][] board = new Cell[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x % (width - 1) == 0 || y % (height - 1) == 0) {
                    board[x][y] = new Cell(x, y, "#", 1); // set boundary wall
                } else {
                    board[x][y] = new Cell(x, y, ".", 0); // set empty cell
                }
            }
        }
        // set start and end cells
        board[width - 1][height - 2].setDefaultMarker(("S"));
        board[width - 1][height - 2].setIsSolid(0);
        board[0][1].setDefaultMarker("E");
        board[0][1].setIsSolid(0);
        this.gameBoard = board;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCellIsSolid(int x, int y) {
        return(this.gameBoard[x][y].getIsSolid());
    }

    public void updateBoard(Player player) {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.gameBoard[x][y].setMarker(this.gameBoard[x][y].getDefaultMarker());
            }
        }
        this.gameBoard[player.getX()][player.getY()].setMarker("P");
    }

    public void printBoard() {
        System.out.println();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.printf(this.gameBoard[x][y].toString());
            }
            System.out.println();
        }
    }
}
