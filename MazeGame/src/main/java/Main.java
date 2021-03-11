public class Main {
    public static void main(String []args) {

        GameBoard board = new GameBoard(20, 10);
        // board.printBoard();

        Player player1 = new Player(1, 1);
        board.updateBoard(player1);
        board.printBoard();

        for (int i = 0; i < 10; i++) {
            player1.move();
            board.updateBoard(player1);
            board.printBoard();
        }
        //board.printBoard();
        /*
        Cell[][] map = new Cell[20][10];

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 20; x++) {
                if (x % 19 == 0 || y % 9 == 0) {
                    map[x][y] = new Cell(x, y, "#"); // set boundary wall
                } else {
                    map[x][y] = new Cell(x, y, "o"); // set empty cell
                }
                System.out.printf(map[x][y].toString());
            }
            System.out.println();
        }

        Cell test = new Cell(100, 100, "!");
        test.setX(99);
        System.out.println(test.getX());
        */
    }

}
