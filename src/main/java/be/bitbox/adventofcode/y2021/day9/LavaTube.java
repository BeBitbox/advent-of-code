package be.bitbox.adventofcode.y2021.day9;

import be.bitbox.adventofcode.Tuple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class LavaTube {
    private final int[][] floor;
    private final int width;
    private final int height;

    public LavaTube(List<String> input) {
        height = input.size();
        width = input.get(0).length();
        floor = new int[height][width];
        for (int i = 0; i < height; i++) {
            var line = input.get(i);
            for (int j = 0; j < width; j++) {
                floor[i][j] = line.charAt(j) - '0';
            }
        }
    }

    public int scorePart1() {
        return getMinPoints().stream()
                .map(tuple -> floor[tuple.x][tuple.y] + 1)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    public int scorePart2() {
        return getMinPoints().stream()
                .map(tuple -> {
                    var positions = new HashSet<Tuple<Integer, Integer>>();
                    findTheBasin(positions, tuple.x, tuple.y);
                    return positions.size();
                })
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce((integer, integer2) -> integer * integer2)
                .orElseThrow();
    }

    public List<Tuple<Integer, Integer>> getMinPoints() {
        List<Tuple<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                var point = floor[i][j];

                var minFromNeighbours = Stream.of(
                                toLeft().apply(i, j),
                                toRight().apply(i, j),
                                goUp().apply(i, j),
                                goDown().apply(i, j))
                        .min(Integer::compareTo)
                        .orElseThrow();
                if (minFromNeighbours > point) {
                    list.add(new Tuple<>(i, j));
                }
            }
        }
        return list;
    }

    public void findTheBasin(Set<Tuple<Integer, Integer>> positions, int x, int y) {
        if (x < 0 || x >= height || y < 0 || y >= width) {
            return;
        }
        if (floor[x][y] == 9) {
            return;
        }
        var tuple = new Tuple<>(x, y);
        if (positions.contains(tuple)) {
            return;
        }
        positions.add(tuple);
        findTheBasin(positions, x - 1, y);
        findTheBasin(positions, x + 1, y);
        findTheBasin(positions, x, y - 1);
        findTheBasin(positions, x, y + 1);
    }

    private BiFunction<Integer, Integer, Integer> toRight() {
        return (x, y) -> x < height - 1 ? floor[x + 1][y] : Integer.MAX_VALUE;
    }

    private BiFunction<Integer, Integer, Integer> toLeft() {
        return (x, y) -> x > 0 ? floor[x - 1][y] : Integer.MAX_VALUE;
    }

    private BiFunction<Integer, Integer, Integer> goUp() {
        return (x, y) -> y > 0 ? floor[x][y - 1] : Integer.MAX_VALUE;
    }

    private BiFunction<Integer, Integer, Integer> goDown() {
        return (x, y) -> y < width - 1 ? floor[x][y + 1] : Integer.MAX_VALUE;
    }
}
