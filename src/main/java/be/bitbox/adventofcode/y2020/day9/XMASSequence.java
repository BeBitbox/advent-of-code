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

    public long encryptionWeakness(int base) {
        long numberToBreak = firstNumberToBreak(base);

        long encryptionWeakness = 0L;
        int currentIndex = 0;
        Queue<Long> queue = new ArrayDeque<>();

        while (encryptionWeakness == 0L) {
            if (sumOfQueue(queue) == numberToBreak) {
                encryptionWeakness = minOfQueue(queue) + maxOfQueue(queue);
            }

            while (sumOfQueue(queue) < numberToBreak) {
                var currentNumber = numbers.get(currentIndex);
                queue.add(currentNumber);
                currentIndex++;
                if (currentNumber == numberToBreak || currentIndex == numbers.size()) {
                    encryptionWeakness = -1L;
                }
            }
            while (sumOfQueue(queue) > numberToBreak) {
                queue.poll();
            }
        }
        return encryptionWeakness;
    }

    private long sumOfQueue(Queue<Long> queue) {
        return queue.stream()
                .reduce(Long::sum)
                .orElse(0L);
    }

    private long minOfQueue(Queue<Long> queue) {
        return queue.stream()
                .min(Long::compare)
                .orElse(0L);
    }

    private long maxOfQueue(Queue<Long> queue) {
        return queue.stream()
                .max(Long::compare)
                .orElse(0L);
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
