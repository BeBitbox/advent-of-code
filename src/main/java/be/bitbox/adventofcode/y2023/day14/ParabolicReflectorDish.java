package be.bitbox.adventofcode.y2023.day14;

import be.bitbox.adventofcode.Util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParabolicReflectorDish {

    private char[][] matrix;
    private final int size;
    private final Map<String, Integer> times = new HashMap<>();

    public ParabolicReflectorDish(List<String> input) {
        size = input.size();
        matrix = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = input.get(i).charAt(j);
            }
        }
    }

    public int totalLoadPart2() {
        for (int i = 0; i < 1_000; i++) {
            cycle();
        }

        var fixedRate = times.entrySet().stream()
                .filter(stringIntegerEntry -> stringIntegerEntry.getValue() > 10)
                .count();
        System.out.println("Fixed rate is " + fixedRate);

        var timesToScip = (int) Math.floor((double) 999_999_000 / fixedRate);

        for (int i = (int) (1000 + (timesToScip * fixedRate)); i < 1_000_000_000; i++) {
            cycle();
        }

        return totalLoad();
    }

    public void cycle() {
        tiltNorth();
        cache();
        spinClockwise();
        tiltNorth();
        cache();
        spinClockwise();
        tiltNorth();
        cache();
        spinClockwise();
        tiltNorth();
        cache();
        spinClockwise();
    }

    private void cache() {
        String s = Util.printMatrix(matrix);
        if (times.containsKey(s)) {
            times.put(s, times.get(s) + 1);
        } else {
            times.put(s, 1);
        }
    }

    public void spinClockwise() {
        var newMatrix = new char [size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newMatrix[j][size - i - 1] = matrix[i][j];
            }
        }
        matrix = newMatrix;
    }

    public void tiltNorth() {
        var lastOpenPositions = new LinkedList<Integer>();
        for (int i = 0; i < size; i++) {
            lastOpenPositions.clear();

            for (int j = 0; j < size; j++) {
                if (matrix[j][i] == '.') {
                    lastOpenPositions.add(j);
                } else if (matrix[j][i] == '#') {
                    lastOpenPositions.clear();
                } else if (matrix[j][i] == 'O') {
                    if (!lastOpenPositions.isEmpty()) {
                        var newIndex = lastOpenPositions.pollFirst();
                        matrix[j][i] = '.';
                        lastOpenPositions.add(j);
                        matrix[newIndex][i] = 'O';
                    }
                } else {
                    throw new IllegalArgumentException("Oww No: " + matrix[j][i]);
                }
            }
        }
    }

    public int totalLoad() {
        int totalLoad = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 'O') {
                    totalLoad += size - i;
                }
            }
        }
        return totalLoad;
    }
}
