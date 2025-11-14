package tictactoe;

public class Board {
    private static final int SIZE = 3;
    private final String[][] grid;

    public Board() {
        grid = new String[SIZE][SIZE];
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = " ";
            }
        }
    }

    public void display() {
        System.out.println("---------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j]);
                if (j < SIZE - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println(" |");
        }
        System.out.println("---------");
    }

    public boolean isCellEmpty(int row, int col) {
        return isValidPosition(row, col) && grid[row][col].equals(" ");
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    public void placeSymbol(int row, int col, String symbol) {
        if (isCellEmpty(row, col)) {
            grid[row][col] = symbol;
        }
    }

    public void clearCell(int row, int col) {
         grid[row][col] = " ";
    }

    public int getSize() {
        return SIZE;
    }

    public GameState getGameState() {
        // Check for X winner
        if (hasWon("X")) {
            return GameState.X_WINS;
        }
        // Check for O winner
        if (hasWon("O")) {
            return GameState.O_WINS;
        }

        // Check for draw
        if (isFull()) {
            return GameState.DRAW;
        }

        return GameState.IN_PROGRESS;
    }

    protected boolean hasWon(String symbol) {
        // Check rows
        for (int i = 0; i < SIZE; i++) {
            if (checkLine(symbol, grid[i][0], grid[i][1], grid[i][2])) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < SIZE; i++) {
            if (checkLine(symbol, grid[0][i], grid[1][i], grid[2][i])) {
                return true;
            }
        }

        // Check diagonals
        if (checkLine(symbol, grid[0][0], grid[1][1], grid[2][2])) {
            return true;
        }
        return checkLine(symbol, grid[0][2], grid[1][1], grid[2][0]);
    }

    private boolean checkLine(String symbol, String... cells) {
        for (String cell : cells) {
            if (!cell.equals(symbol)) {
                return false;
            }
        }
        return true;
    }

    private boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }


}

