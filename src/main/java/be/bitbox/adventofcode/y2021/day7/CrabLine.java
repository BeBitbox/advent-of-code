package be.bitbox.adventofcode.y2021.day7;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CrabLine {

    private final Map<Integer, Long> crabDistribution;

    public CrabLine(String input) {
        crabDistribution = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public long calculateLeastFuel(boolean part1) {
        var positions = new TreeSet<>(crabDistribution.keySet());

        long leastFuel = Long.MAX_VALUE;
        for (int i = positions.first(); i <= positions.last(); i++) {
            var fuel = part1 ? calculateFuelPart1(i) : calculateFuelPart2(i);
            if (fuel < leastFuel) {
                leastFuel = fuel;
            }
        }
        return leastFuel;
    }

    private long calculateFuelPart1(int position) {
        long fuel = 0;
        for (Map.Entry<Integer, Long> crabPosition : crabDistribution.entrySet()) {
            fuel += Math.abs(crabPosition.getKey() - position) * crabPosition.getValue();
        }
        return fuel;
    }

    private long calculateFuelPart2(int position) {
        long fuel = 0;
        for (Map.Entry<Integer, Long> crabPosition : crabDistribution.entrySet()) {
            long x = Math.abs(crabPosition.getKey() - position);

            fuel += (x * x + x) / 2 * crabPosition.getValue();
        }
        return fuel;
    }
}
