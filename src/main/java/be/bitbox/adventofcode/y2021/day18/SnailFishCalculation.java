package be.bitbox.adventofcode.y2021.day18;

import java.util.List;
import java.util.stream.Collectors;

public class SnailFishCalculation {
    private final List<SnailNumber> snailNumbers;

    public SnailFishCalculation(List<String> input) {
        snailNumbers = input.stream()
                .map(String::trim)
                .map(SnailNumber::new)
                .collect(Collectors.toList());
    }

    public SnailNumber addition() {
        return snailNumbers.stream().reduce(SnailNumber::add).orElse(null);
    }

    public long biggestMagnitude() {
        long maxMagnitude = 0L;
        for (int i = 0; i < snailNumbers.size(); i++) {
            for (int j = 0; j < snailNumbers.size(); j++) {
                if (i != j) {
                    var sum = snailNumbers.get(i).add(snailNumbers.get(j));
                    var magnitude = sum.getPair().magnitude();
                    if (magnitude > maxMagnitude) {
                        maxMagnitude = magnitude;
                    }
                }
            }
        }
        return maxMagnitude;
    }
}
