public class Main {
    public static void main(String []args) {

        // create 15 by 7 cell game board
        GameBoard board = new GameBoard(15, 7);


        // create player and update game board
        Player player1 = new Player(board.getWidth() - 1, board.getHeight() - 2);
        board.updateBoard(player1);
        board.printBoard();

        // test player movement for 20 turns
        for (int i = 0; i < 20; i++) {
            player1.move(board);
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
