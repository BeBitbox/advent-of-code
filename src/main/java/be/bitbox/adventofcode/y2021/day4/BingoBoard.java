package be.bitbox.adventofcode.y2021.day4;

import be.bitbox.adventofcode.Util;

import java.util.List;

public class BingoBoard {

    private final int[][] board;
    private final boolean[][] marked;

    public BingoBoard(List<String> input) {
        this.board = new int[5][5];
        for (int i = 0; i < 5; i++) {
            var line = input.get(i).trim();
            var numbers = line.split("\\s+");
            for (int j = 0; j < 5; j++) {
                this.board[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        this.marked = new boolean[5][5];
    }

    public void playNumber(int number) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.board[i][j] == number) {
                    marked[i][j] = true;
                }
            }
        }
    }

    public boolean hasWon() {
        for (int i = 0; i < 5; i++) {
            if (marked[i][0] && marked[i][1] && marked[i][2] && marked[i][3] && marked[i][4]) {
                return true;
            }
            if (marked[0][i] && marked[1][i] && marked[2][i] && marked[3][i] && marked[4][i]) {
                return true;
            }
        }
        return false;
    }

    public int sumOfAllUnmarkeds() {
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!marked[i][j]) {
                    counter += this.board[i][j];
                }
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        return "BingoBoard{" +
                "board=" + Util.printMatrix(board) +
                ", marked=" + Util.printMatrix(marked) +
                '}';
    }
}
