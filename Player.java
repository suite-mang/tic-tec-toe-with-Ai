package tictactoe;

public abstract class Player {
    protected final String symbol;

    public Player(String symbol) {
        this.symbol = symbol;
    }

    public abstract void makeMove(Board board);

    public String getSymbol() {
        return symbol;
    }
}


