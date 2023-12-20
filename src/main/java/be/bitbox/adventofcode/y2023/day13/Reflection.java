package be.bitbox.adventofcode.y2023.day13;

import java.util.List;

public class Reflection {
    private final char[][] matrix;
    private final int height;
    private final int width;

    public Reflection(List<String> input) {
        height = input.size();
        width = input.get(0).length();
        matrix = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = input.get(i).charAt(j);
            }
        }
    }

    public int smudge() {
        var oldVerticalReflection = verticalReflection(-1);
        var oldHorizontalReflection = horizontalReflection(-1);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                var oldChar = matrix[i][j];
                var newChar = '.' == oldChar ? '#' : '.';
                matrix[i][j] = newChar;
                var verticalReflection = verticalReflection(oldVerticalReflection);
                var horizontalReflection = horizontalReflection(oldHorizontalReflection);
                if (verticalReflection >= 0 || horizontalReflection >= 0) {
                    return score(horizontalReflection, verticalReflection);
                }
                matrix[i][j] = oldChar;
            }
        }
        throw new IllegalStateException("IEOS");
    }

    public int summarize() {
        return score(horizontalReflection(-1), verticalReflection(-1));
    }

    private int score(int horizontal, int vertical) {
        var sum = 0;
        sum +=  vertical + 1;
        sum += (horizontal + 1) * 100;
        return sum;
    }

    private int horizontalReflection(int ignore) {
        for (int i = 0; i < height; i++) {
            if (i != ignore && horizontalReflectionFor(i)) {
                return i;
            }
        }
        return -1;
    }

    private int verticalReflection(int ignore) {
        for (int i = 0; i < width; i++) {
            if (i != ignore && verticalReflectionFor(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean verticalReflectionFor(int startColumn) {
        boolean passed = false;
        for (int j = 0; j < height; j++) {
            for (int column = startColumn; column >= 0; column--) {
                int reverseColumn = (startColumn - column) + startColumn + 1;
                if (reverseColumn >= width) {
                    break;
                }
                passed = true;
                if (matrix[j][column] != matrix[j][reverseColumn]) {
                    return false;
                }
            }
        }
        return passed;
    }

    private boolean horizontalReflectionFor(int startRow) {
        boolean passed = false;
        for (int i = 0; i < width; i++) {
            for (int row = startRow; row >= 0; row--) {
                int reverseRow = (startRow - row) + startRow + 1;
                if (reverseRow >= height) {
                    break;
                }
                passed = true;
                if (matrix[row][i] != matrix[reverseRow][i]) {
                    return false;
                }
            }
        }
        return passed;
    }
}
