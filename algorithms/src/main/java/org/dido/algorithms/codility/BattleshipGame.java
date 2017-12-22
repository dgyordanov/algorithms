package org.dido.algorithms.codility;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BattleshipGame {

    public static void main(String[] args) {
        BattleshipGame game = new BattleshipGame(4, 6);
        game.addShip(3, 4);
        game.addShip(1, 2);
        game.addShip(2, 4);

        Scanner in = new Scanner(System.in);
        while (game.hasMoreShips()) {
            System.out.println("Please enter the horizontal");
            int horizontal = in.nextInt();
            System.out.println("Please enter the vertical");
            int vertical = in.nextInt();

            game.shoot(horizontal, vertical);

            System.out.println(game);
        }
        System.out.println("Gave over");
    }

    private Marker[][] board;

    public BattleshipGame(int horizontal, int vertical) {
        board = new Marker[horizontal][vertical];
        IntStream.range(0, horizontal).forEach(i -> Arrays.fill(board[i], Marker.EMPTY));
    }

    public void shoot(int horizontal, int vertical) {
        if (horizontal < 1 || horizontal > board.length){
            System.out.println("Invalid horizontal value");
            return;
        }
        if (vertical < 1 || vertical > board[0].length){
            System.out.println("Invalid vertical value");
            return;
        }
        horizontal--;
        vertical--;
        switch (board[horizontal][vertical]) {
            case SHOOTED:
            case SHIP:
                board[horizontal][vertical] = Marker.SHOOTED;
                break;
            default:
                board[horizontal][vertical] = Marker.MISSED;
        }
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder("  ");
        IntStream.range(1, board.length + 1).forEach(i -> result.append(i) . append(" "));
        result.append("\n");
        for (int v = 0; v < board[0].length; v++) {
            result.append(v + 1).append(" ");
            for (int h = 0; h < board.length; h++) {
                result.append(board[h][v].getSymbol()).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public void addShip(int wight, int height) {
        // TODO: validate input
        board[wight - 1][height - 1] = Marker.SHIP;
    }

    private boolean hasMoreShips() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Marker.SHIP) {
                    return true;
                }
            }
        }
        return false;
    }

    private enum Marker {
        EMPTY("_"), SHIP("_"), SHOOTED("X"), MISSED("0");

        private final String symbol;

        Marker(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

    }

}
