import java.util.Arrays;

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
                    board[x][y] = new Cell(x, y, "#"); // set boundary wall
                } else {
                    board[x][y] = new Cell(x, y, "."); // set empty cell
                }
            }
        }
        this.gameBoard = board;
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
