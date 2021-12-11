package be.bitbox.adventofcode.y2021.day11;

import be.bitbox.adventofcode.Tuple;
import be.bitbox.adventofcode.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class OctopusField {

    private final short[][] field;
    private int ticksOccured = 0;

    public OctopusField(List<String> inputs) {
        field = new short[10][10];
        for (short i = 0; i < 10; i++) {
            var line = inputs.get(i);
            for (short j = 0; j < 10; j++) {
                field[i][j] = (short) (line.charAt(j) - '0');
            }
        }
    }

    public int tick(int times) {
        return IntStream.rangeClosed(1, times)
                .map(i -> singleTick())
                .reduce(Integer::sum)
                .orElse(0);
    }

    public int tickUntillSynced() {
        while (!allInSync()) {
            singleTick();
        }
        System.out.println(Util.printMatrix(field));
        return ticksOccured;
    }

    private int singleTick() {
        // step1
        for (short i = 0; i < 10; i++) {
            for (short j = 0; j < 10; j++) {
                field[i][j] += 1;
            }
        }

        var biggerThanNines = getBiggerThanNines();
        Set<Tuple<Short, Short>> flashes = new HashSet<>();
        while (biggerThanNines.size() > flashes.size()) {
            biggerThanNines.stream()
                    .filter(tuple -> !flashes.contains(tuple))
                    .forEach(this::increaseNeighbours);
            flashes.addAll(biggerThanNines);
            biggerThanNines = getBiggerThanNines();
        }

        flashes.forEach(this::reset);
        ticksOccured++;
        return flashes.size();
    }

    private void increaseNeighbours(Tuple<Short, Short> niner) {
        var minX = Math.max(niner.x - 1, 0);
        var minY = Math.max(niner.y - 1, 0);
        for (int i = minX; i < 10 && i <= niner.x + 1; i++) {
            for (int j = minY; j < 10 && j <= niner.y + 1; j++) {
                field[i][j] += 1;
            }
        }

    }

    private void reset(Tuple<Short, Short> tuple) {
        field[tuple.x][tuple.y] = 0;
    }

    public List<Tuple<Short, Short>> getBiggerThanNines() {
        List<Tuple<Short, Short>> tuples = new ArrayList<>();

        for (short i = 0; i < 10; i++) {
            for (short j = 0; j < 10; j++) {
                if (field[i][j] > 9) {
                    tuples.add(new Tuple<>(i, j));
                }
            }
        }
        return tuples;
    }

    public boolean allInSync() {
        short syncedValue = field[0][0];

        for (short i = 0; i < 10; i++) {
            for (short j = 0; j < 10; j++) {
                if (field[i][j] != syncedValue) {
                    return false;
                }
            }
        }
        return true;
    }
}
