package be.bitbox.adventofcode.y2023.day3;

import be.bitbox.adventofcode.CharUtils;
import be.bitbox.adventofcode.Tuple;
import be.bitbox.adventofcode.Util;

import java.util.ArrayList;
import java.util.List;

public class GearRotations {

    private final char[][] matrix;
    private final int width;
    private final int height;
    private final List<Gear> gears;

    public GearRotations(List<String> input) {
        width = input.get(0).length();
        height = input.size();
        matrix = new char[height][width];
        gears = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = input.get(i).charAt(j);
                if (matrix[i][j] == '*') {
                    gears.add(new Gear(new Tuple<>(i, j), new ArrayList<>()));
                }
            }
        }
    }

    public void print() {
        System.out.println(Util.printMatrix(matrix));
    }

    public int calculate() {
        int sum = 0;

        for (int i = 0; i < height; i++) {

            boolean lastOneNumber = false;
            StringBuilder currentNumber = new StringBuilder();

            for (int j = 0; j < width; j++) {
                if (CharUtils.isDigit(matrix[i][j])) {
                    currentNumber.append(matrix[i][j]);
                }
                if (lastOneNumber && (!CharUtils.isDigit(matrix[i][j]) || j == width - 1)) {
                    if (checkSymbolIsClose(j - currentNumber.length(), i, currentNumber)) {
                        sum += Integer.parseInt(currentNumber.toString());
                    }
                    currentNumber = new StringBuilder();
                }
                lastOneNumber = CharUtils.isDigit(matrix[i][j]);
            }
        }
        return sum;
    }

    public boolean checkSymbolIsClose(int x, int y, StringBuilder currentNumber) {
        boolean found = false;
        for (int i = y - 1; i < y + 2; i++) {
            for (int j = x - 1; j < x + currentNumber.length() + 1; j++) {
                if (i >= 0 && i < height && j >= 0 && j < width) {
                    if (!CharUtils.isDigit(matrix[i][j]) && matrix[i][j] != '.') {
                        found = true;
                    }
                    if (matrix[i][j] == '*') {
                        int finalI = i;
                        int finalJ = j;
                        gears.stream()
                                .filter(gear -> gear.coordinate.x == finalI && gear.coordinate.y == finalJ)
                                .findFirst()
                                .orElseThrow()
                                .adjacentNumbers.add(currentNumber.toString());
                    }
                }
            }
        }
        return found;
    }

    public Long getGearRatios() {
        return gears.stream()
                .filter(gear -> gear.adjacentNumbers.size() == 2)
                .map(gear -> Long.parseLong(gear.adjacentNumbers.get(0)) * Long.parseLong(gear.adjacentNumbers.get(1)))
                .reduce(Long::sum)
                .orElseThrow();
    }

    private record Gear(Tuple<Integer, Integer> coordinate, List<String> adjacentNumbers) { }
}