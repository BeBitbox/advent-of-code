package be.bitbox.adventofcode.y2020.day9;

import java.util.*;
import java.util.stream.Collectors;

public class XMASSequence {
    private final List<Long> numbers;

    public XMASSequence(List<Long> numbers) {
        this.numbers = numbers;
    }

    public long firstNumberToBreak(int base) {
        Queue<Long> queue = new ArrayDeque<>();

        var currentIndex = 0;

        while (currentIndex < base) {
            queue.add(numbers.get(currentIndex));
            currentIndex++;
        }

        while (currentIndex < numbers.size()) {
            long current = numbers.get(currentIndex);
            if (valid(queue, current)) {
                queue.poll();
                queue.add(current);
                currentIndex++;
            } else {
                return current;
            }
        }
        return 0L;
    }

    private boolean valid(Queue<Long> queue, Long number) {
        var diffs = queue.stream()
                .filter(l -> l * 2 != number)
                .map(l -> number - l)
                .collect(Collectors.toSet());
        return queue.stream()
                .anyMatch(diffs::contains);
    }
}
