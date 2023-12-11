package be.bitbox.adventofcode.y2023.day11;

import be.bitbox.adventofcode.Tuple;

import java.util.ArrayList;
import java.util.List;

public class CosmicExpansion {

    private final char[][] matrix;
    private final int height;
    private final int width;

    public CosmicExpansion(List<String> inputList) {
        height = inputList.size();
        width = inputList.get(0).length();
        matrix = new char[height][width];
        for (int i = 0; i < height; i++) {
            var line = inputList.get(i);
            for (int j = 0; j < width; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }
    }

    private ArrayList<Integer> getEmptyColumns() {
        var emptyColumns = new ArrayList<Integer>();
        for (int j = 0; j < width; j++) {
            boolean allEmpty = true;
            for (int i = 0; i < height; i++) {
                if (matrix[i][j] != '.') {
                    allEmpty = false;
                    break;
                }
            }
            if (allEmpty) {
                emptyColumns.add(j);
            }
        }
        return emptyColumns;
    }

    private ArrayList<Integer> getEmptyRows() {
        var emptyRows = new ArrayList<Integer>();
        for (int i = 0; i < height; i++) {
            boolean allEmpty = true;
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] != '.') {
                    allEmpty = false;
                    break;
                }
            }
            if (allEmpty) {
                emptyRows.add(i);
            }
        }
        return emptyRows;
    }

    public long olderGalaxies(int expansionRate) {
        var emptyRows = getEmptyRows();
        var emptyColumns = getEmptyColumns();
        var galaxies = getGalaxies();

        long sum = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                var left = galaxies.get(i);
                var right = galaxies.get(j);

                sum += Math.abs(left.x - right.x) + Math.abs(left.y - right.y);

                var extraRows = emptyRows.stream()
                        .filter(row -> (left.x < row && right.x > row) || (left.x > row && right.x < row))
                        .count() * (expansionRate - 1);

                var extraColumns = emptyColumns.stream()
                        .filter(column -> (left.y < column && right.y > column) || (left.y > column && right.y < column))
                        .count() * (expansionRate - 1);
                sum += extraRows + extraColumns;
            }
        }

        return sum;
    }

    private List<Tuple<Integer, Integer>> getGalaxies() {
        List<Tuple<Integer, Integer>> galaxies = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == '#') {
                    galaxies.add(new Tuple<>(i, j));
                }
            }
        }
        return galaxies;
    }


}
