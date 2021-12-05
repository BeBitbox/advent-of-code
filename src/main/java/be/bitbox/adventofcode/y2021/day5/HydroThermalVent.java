package be.bitbox.adventofcode.y2021.day5;

import be.bitbox.adventofcode.Tuple;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class HydroThermalVent {
    private final int x1, y1, x2, y2;

    public HydroThermalVent(String line) {
        var parts = line.split(" ");

        var numbers1 = parts[0].split(",");
        x1 = Integer.parseInt(numbers1[0]);
        y1 = Integer.parseInt(numbers1[1]);
        var numbers2 = parts[2].split(",");
        x2 = Integer.parseInt(numbers2[0]);
        y2 = Integer.parseInt(numbers2[1]);
    }

    public boolean isNotDiagonal() {
        return x1 == x2 || y1 == y2;
    }

    public Set<Tuple<Integer, Integer>> getTuples() {
        Set<Tuple<Integer, Integer>> set = new HashSet<>();

        Function<Integer, Integer> horizontalMove = Function.identity();
        Function<Integer, Integer> verticalMove = Function.identity();

        if (x1 < x2) {
            horizontalMove = integer -> integer + 1;
        } else if (x1 > x2) {
            horizontalMove = integer -> integer - 1;
        }
        if (y1 < y2) {
            verticalMove = integer -> integer + 1;
        } else if (y1 > y2) {
            verticalMove = integer -> integer - 1;
        }

        int i = x1;
        int j = y1;

        set.add(new Tuple<>(i, j));
        while (i != x2 || j != y2) {
            i = horizontalMove.apply(i);
            j = verticalMove.apply(j);
            set.add(new Tuple<>(i, j));
        }

        return set;
    }
}
