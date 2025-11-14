package tictactoe;
//put Symbol in empty cell
public class EasyAIPlayer extends Player {

    public EasyAIPlayer(String symbol) {
        super(symbol);
    }

    @Override
    public void makeMove(Board board) {
        System.out.println("Making move level \"easy\"");

        int row, col;
        do {
            row = (int) (Math.random() * board.getSize());
            col =(int) (Math.random() * board.getSize());
        } while (!board.isCellEmpty(row, col));


        board.placeSymbol(row, col, symbol);
    }
}
