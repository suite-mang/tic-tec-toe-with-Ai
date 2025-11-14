package tictactoe;

import java.util.Scanner;

public class HumanPlayer extends Player {

    private final Scanner scanner;
    public HumanPlayer(String symbol, Scanner scanner) {
        super(symbol);
        this.scanner = scanner;
    }

    @Override
    public void makeMove(Board board) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("You should enter numbers!");
                continue;
            }

            String[] parts = input.split("\\s+");

            if (parts.length != 2) {
                System.out.println("You should enter numbers!");
                continue;
            }

            try {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                if (row < 1 || row > 3 || col < 1 || col > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                // Convert to 0-indexed
                row--;
                col--;

                if (!board.isCellEmpty(row, col)) {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

                board.placeSymbol(row, col, symbol);
                break;

            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }
}