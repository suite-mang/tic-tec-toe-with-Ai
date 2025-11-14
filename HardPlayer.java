package tictactoe;

public class HardPlayer extends Player {

    public HardPlayer(String symbol) {
        super(symbol);
    }

    @Override
    public void makeMove(Board board) {
        System.out.println("Making move level \"hard\"");

        int[] bestMove = findBestMove(board);
        board.placeSymbol(bestMove[0], bestMove[1], symbol);
    }

    /**
     * Finds the best move using the minimax algorithm
     * @param board The current game board
     * @return An array [row, col] representing the best move
     */
    private int[] findBestMove(Board board) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        // Try all empty cells
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.isCellEmpty(row, col)) {
                    // Make the move
                    board.placeSymbol(row, col, symbol);

                    // Calculate the score for this move
                    int score = minimax(board, 0, false);

                    // Undo the move
                    board.clearCell(row, col);

                    // Update best move if this is better
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = row;
                        bestMove[1] = col;
                    }
                }
            }
        }

        return bestMove;
    }

    /**
     * Minimax algorithm implementation
     * @param board The current game board
     * @param depth Current depth in the game tree
     * @param isMaximizing True if it's the maximizing player's turn
     * @return The score of the board position
     */
    private int minimax(Board board, int depth, boolean isMaximizing) {
        GameState state = board.getGameState();

        // Base cases: check if game is over
        if (state == GameState.X_WINS) {
            return symbol.equals("X") ? 10 - depth : depth - 10;
        }
        if (state == GameState.O_WINS) {
            return symbol.equals("O") ? 10 - depth : depth - 10;
        }
        if (state == GameState.DRAW) {
            return 0;
        }

        if (isMaximizing) {
            // Maximizer's turn (AI)
            int bestScore = Integer.MIN_VALUE;

            for (int row = 0; row < board.getSize(); row++) {
                for (int col = 0; col < board.getSize(); col++) {
                    if (board.isCellEmpty(row, col)) {
                        // Make the move
                        board.placeSymbol(row, col, symbol);

                        // Recursively get the score
                        int score = minimax(board, depth + 1, false);

                        // Undo the move
                        board.clearCell(row, col);

                        // Update best score
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
            return bestScore;

        } else {
            // Minimizer's turn (Opponent)
            int bestScore = Integer.MAX_VALUE;
            String opponentSymbol = symbol.equals("X") ? "O" : "X";

            for (int row = 0; row < board.getSize(); row++) {
                for (int col = 0; col < board.getSize(); col++) {
                    if (board.isCellEmpty(row, col)) {
                        // Make the move
                        board.placeSymbol(row, col, opponentSymbol);

                        // Recursively get the score
                        int score = minimax(board, depth + 1, true);

                        // Undo the move
                        board.clearCell(row, col);

                        // Update best score
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
            return bestScore;
        }
    }
}