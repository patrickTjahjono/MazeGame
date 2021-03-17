import java.util.Scanner;

public class Enemy extends MovingCharacter{
    public Enemy(int x, int y, String label)
    {
        super(x, y, label);
    }


    private String getBestMove(Player player, BoardState boardState) {
        String[] moves = {"w", "e", "n", "s"};
        int[] squaredDistances = new int[4];

        int playerX = player.getX();
        int playerY = player.getY();
        int enemyX = this.getX();
        int enemyY = this.getY();

        int bestMoveDistance = Integer.MAX_VALUE;
        int bestMoveIndex = -1;
        // Squared Distance from west and east cell to player
        squaredDistances[0] = boardState.calculateSquaredDistance(enemyX - 1, enemyY, playerX, playerY);
        squaredDistances[1] = boardState.calculateSquaredDistance(enemyX + 1, enemyY, playerX, playerY);

        // Squared Distance from north cell and east cell to player
        squaredDistances[2] = boardState.calculateSquaredDistance(enemyX, enemyY - 1, playerX, playerY);
        squaredDistances[3] = boardState.calculateSquaredDistance(enemyX, enemyY + 1, playerX, playerY);

        for (int i = 0; i < 4; i++) {
            if (bestMoveDistance > squaredDistances[i]) {
                bestMoveDistance = squaredDistances[i];
                bestMoveIndex = i;
            }
        }
        System.out.printf(
                "west cell to player:" + squaredDistances[0] + "\n" +
                "east cell to player" + squaredDistances[1] + "\n" +
                "north cell to player" + squaredDistances[2] + "\n" +
                "south cell to player" + squaredDistances[3] + "\n" +
                "best move:" + moves[bestMoveIndex] + "\n"
        );
       return moves[bestMoveIndex];
    }

    public void move(Board board, BoardState boardState, Player player) {
        String bestMove = getBestMove(player, boardState);

        if (bestMove.equals("w")) {
            this.moveWest(board, boardState);
        } else if (bestMove.equals("e")) {
            this.moveEast(board, boardState);
        } else if (bestMove.equals("n")) {
            this.moveNorth(board, boardState);
        } else if (bestMove.equals("s")) {
            this.moveSouth(board, boardState);
        }
    }
}
