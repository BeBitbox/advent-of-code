package be.bitbox.adventofcode.y2021.day25;

import be.bitbox.adventofcode.Util;

import java.util.List;

public class SeaCucumberField {

    private final char[][] field;
    private final int height;
    private final int width;
    private int steps = 0;

    public SeaCucumberField(List<String> input) {
        height = input.size();
        width = input.get(0).length();
        field = new char[height][width];

        for (int i = 0; i < height; i++) {
            var line = input.get(i);
            for (int j = 0; j < width; j++) {
                field[i][j] = line.charAt(j);
            }
        }
    }

    public int moveUntillAllQuiet() {
        while (move() != 0) {
        }

        return steps;
    }

    private int move() {
        int sum = moveRight();
        sum += moveDown();

        steps++;
        return sum;
    }

    private int moveRight() {
        int movements = 0;
        for (int i = 0; i < height; i++) {
            char[] row = new char[width];
            for (int j = 0; j < width - 1; j++) {
                if (field[i][j] == '>' && field[i][j + 1] == '.') {
                    row[j] = '.';
                    row[++j] = '>';
                    movements++;
                } else {
                    row[j] = field[i][j];
                }
            }
            if (field[i][width - 1] == '>' && field[i][0] == '.') {
                row[width - 1] = '.';
                row[0] = '>';
                movements++;
            } else if (row[width - 1] == 0) {
                row[width - 1] = field[i][width - 1];
            }

            field[i] = row;
        }
        return movements;
    }

    private int moveDown() {
        int movements = 0;
        for (int j = 0; j < width; j++) {
            char[] column = new char[height];
            for (int i = 0; i < height - 1; i++) {
                if (field[i][j] == 'v' && field[i + 1][j] == '.') {
                    column[i] = '.';
                    column[++i] = 'v';
                    movements++;
                } else {
                    column[i] = field[i][j];
                }
            }
            if (field[height - 1][j] == 'v' && field[0][j] == '.') {
                column[height - 1] = '.';
                column[0] = 'v';
                movements++;
            } else if (column[height - 1] == 0) {
                column[height - 1] = field[height - 1][j];
            }

            for (int i = 0; i < height; i++) {
                field[i][j] = column[i];
            }
        }
        return movements;
    }

    @Override
    public String toString() {
        return Util.printMatrix(field);
    }
}
