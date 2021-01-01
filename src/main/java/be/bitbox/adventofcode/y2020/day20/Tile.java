package be.bitbox.adventofcode.y2020.day20;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class Tile {
    private final int id;
    private final Character[][] matrix = new Character[10][10];
    private int nextRowToAdd;
    private Borders borders = null;

    public Tile(int id) {
        this.id = id;
        nextRowToAdd = 0;
    }

    public void addLine(String inputLine) {
        for (int i = 0; i < 10; i++) {
            matrix[nextRowToAdd][i] = inputLine.charAt(i);
        }
        nextRowToAdd++;
    }

    public int getId() {
        return id;
    }

    public String oppositeBorder(String border) {
        return borders.oppositeBorder(border);
    }

    public Set<String> getBorders() {
        if (borders == null) {
            borders = new Borders();
        }
        return borders.borders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return id == tile.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "id=" + id +
                ", matrix=" + Arrays.toString(matrix) +
                '}';
    }

    public class Borders {
        private final String border1;
        private final String oppositeBorder1;
        private final String border2;
        private final String oppositeBorder2;
        private final String border3;
        private final String oppositeBorder3;
        private final String border4;
        private final String oppositeBorder4;
        private final Set<String> borders;

        public Borders() {
            border1 = calculateKeys(0, null);
            oppositeBorder1 = calculateKeys(9, null);
            border2 = new StringBuilder(border1).reverse().toString();
            oppositeBorder2 = new StringBuilder(oppositeBorder1).reverse().toString();
            border3 = calculateKeys(null, 0);
            oppositeBorder3 = calculateKeys(null, 9);
            border4 = new StringBuilder(border3).reverse().toString();
            oppositeBorder4 = new StringBuilder(oppositeBorder3).reverse().toString();
            borders = Set.of(border1, border2, border3, border4, oppositeBorder1, oppositeBorder2, oppositeBorder3, oppositeBorder4);
        }

        private String oppositeBorder(String border) {
            if (border.equals(border1)) {
                return oppositeBorder1;
            }
            if (border.equals(border2)) {
                return oppositeBorder2;
            }
            if (border.equals(border3)) {
                return oppositeBorder3;
            }
            if (border.equals(border4)) {
                return oppositeBorder4;
            }
            if (border.equals(oppositeBorder1)) {
                return border1;
            }
            if (border.equals(oppositeBorder2)) {
                return border2;
            }
            if (border.equals(oppositeBorder3)) {
                return border3;
            }
            if (border.equals(oppositeBorder4)) {
                return border4;
            }
            throw new IllegalArgumentException();
        }

        private String calculateKeys(Integer fixedRow, Integer fixedColumn) {
            var stringBuilder = new StringBuilder();
            if (fixedRow != null) {
                for (int i = 0; i < 10; i++) {
                    stringBuilder.append(matrix[fixedRow][i]);
                }
            } else {
                for (int i = 0; i < 10; i++) {
                    stringBuilder.append(matrix[i][fixedColumn]);
                }
            }
            return stringBuilder.toString();
        }
    }
}
