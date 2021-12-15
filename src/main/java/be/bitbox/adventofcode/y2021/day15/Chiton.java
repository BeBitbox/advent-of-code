package be.bitbox.adventofcode.y2021.day15;

import java.util.List;
import java.util.stream.Stream;

public class Chiton {
    private final int[][] cavern;
    private final int size;

    public Chiton(List<String> input) {
        cavern = new int[input.size()][input.size()];
        size = input.size();
        for (int i = 0; i < size; i++) {
            var line = input.get(i);
            for (int j = 0; j < size; j++) {
                cavern[i][j] = line.charAt(j) - '0';
            }
        }
    }

    public int lowestRiskPart1() {
        int[][] lowestRisks = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i > 0 || j > 0) {
                    int riskA = i > 0 ? lowestRisks[i - 1][j] : Integer.MAX_VALUE;
                    int riskB = j > 0 ? lowestRisks[i][j - 1] : Integer.MAX_VALUE;
                    lowestRisks[i][j] = Math.min(riskA, riskB) + cavern[i][j];
                }
            }
        }
        return lowestRisks[size - 1][size - 1];
    }

    public int lowestRiskPart2() {
        int newSize = size * 5;
        int[][] lowestRisks = new int[newSize][newSize];

        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                if (i > 0 || j > 0) {
                    int riskA = i > 0 ? lowestRisks[i - 1][j] : Integer.MAX_VALUE;
                    int riskB = j > 0 ? lowestRisks[i][j - 1] : Integer.MAX_VALUE;

                    int diff = getScore(i, j);
                    lowestRisks[i][j] = Math.min(riskA, riskB) + diff;
                }
            }
        }

        boolean anomalyFound = true;
        while (anomalyFound) {
            anomalyFound = false;
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    if (i > 0 || j > 0) {
                        int neighbourMin = evaluateNeighbours(lowestRisks, i, j);
                        if (neighbourMin < lowestRisks[i][j]) {
                            lowestRisks[i][j] = neighbourMin;
                            anomalyFound = true;
                        }
                    }
                }
            }
        }

        return lowestRisks[newSize - 1][newSize - 1];
    }

    private int evaluateNeighbours(int[][] lowestRisks, int i, int j) {
        int riskA = i > 0 ? lowestRisks[i - 1][j] : Integer.MAX_VALUE;
        int riskB = j > 0 ? lowestRisks[i][j - 1] : Integer.MAX_VALUE;
        int riskC = j < lowestRisks.length - 1 ? lowestRisks[i][j + 1] : Integer.MAX_VALUE;
        int riskD = i < lowestRisks.length - 1 ? lowestRisks[i + 1][j] : Integer.MAX_VALUE;

        int diff = getScore(i, j);
        return Stream.of(riskA, riskB, riskC, riskD).min(Integer::compareTo).get() + diff;
    }

    private int getScore(int i, int j) {
        int diff = i / size + j / size + cavern[i % size][j % size];
        if (diff > 9) {
            diff = diff - 9;
        }
        return diff;
    }
}
