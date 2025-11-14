package tictactoe;
//Winning move: If the AI has two in a row and can win with one more move, it takes that move.
//Blocking move: If the opponent has two in a row and can win with one more move, the AI blocks it.
//Fallback move: If neither of the above applies, the AI makes a random move.
import java.util.Random;

public class MediumPlayer extends Player{
    private final Random random;

    public MediumPlayer(String symbol){
        super(symbol);
        this.random = new Random();

    }
    @Override
    public void makeMove(Board board) {
        //1. Try to win
        System.out.println("Making move level \"medium\"");
        int[] winMove = findWinningMove(board, symbol);
        if (winMove!= null) {
            board.placeSymbol(winMove[0], winMove[1], symbol);
            return;
        }
        // 2. Block opponent's winning move
        String opponentSymbol = symbol.equals("X") ? "O" : "X";
        int[] blockMove = findWinningMove(board, opponentSymbol);
        if (blockMove != null) {
            board.placeSymbol(blockMove[0], blockMove[1], symbol);
            return;
        }

        //make a random move
        int row, col;
        do {
            row = random.nextInt(board.getSize());
            col = random.nextInt(board.getSize());
        } while (!board.isCellEmpty(row, col));

        board.placeSymbol(row, col, symbol);
    }
    private int[] findWinningMove(Board board, String symbol){
        for (int row = 0; row < board.getSize(); row++) {
            for (int column = 0; column < board.getSize(); column++) {
                if (board.isCellEmpty(row,column)){
                    board.placeSymbol(row,column, symbol);
                    boolean isWin =board.hasWon(symbol);
                    board.clearCell(row, column);
                    if (isWin){
                        return new int[]{row, column};
                    }
                }

            }

        }
        return null;

    }

}
