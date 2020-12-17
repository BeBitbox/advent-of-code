package be.bitbox.adventofcode.y2020.day10;

import java.util.*;
import java.util.stream.Collectors;

public class JoltCalculator {
    private final SortedSet<Integer> availableJolts;
    private final int deviceAdapter;
    private final Map<LinkedList<Integer>, Long> cache;

    public JoltCalculator(List<Integer> inputNumbers) {
        availableJolts = new TreeSet<>(inputNumbers);
        deviceAdapter = availableJolts.last() + 3;
        availableJolts.add(deviceAdapter);
        cache = new HashMap<>();
    }

    public Result connectAllJoltAdapters() {
        var result = new Result();
        int previousJolt = 0;

        for (Integer availableJolt : availableJolts) {
            switch (availableJolt - previousJolt) {
                case 1:
                    result.increaseOneJoltDiffs();
                    break;
                case 2:
                    result.increaseTwoJoltDiffs();
                    break;
                case 3:
                    result.increaseThreeJoltDiffs();
                    break;
            }
            previousJolt = availableJolt;
        }
        return result;
    }

    /**
     * DON'T SAVE THAT, YOU IDIOT! HEAPSPACE ERRORS
     *
     * @return
     */
    @Deprecated
    public long possibleWaysCRAZY() {
        List<List<Integer>> listOfLists = new ArrayList<>();
        listOfLists.add(List.of());

        for (Integer availableJolt : availableJolts) {
            System.out.println(availableJolt);
            var validCurrentList = listOfLists.stream()
                    .filter(list -> isNotExceeding(list, availableJolt))
                    .collect(Collectors.toList());
            if (availableJolt == deviceAdapter) {
                listOfLists = new ArrayList<>();
            } else {
                listOfLists = validCurrentList.stream()
                        .map(list -> {
                            List<Integer> clone = new ArrayList<>(list);
                            clone.add(availableJolt);
                            return clone;
                        })
                        .filter(JoltCalculator::isValid)
                        .collect(Collectors.toList());
            }
            listOfLists.addAll(validCurrentList);
        }

        return listOfLists.size();
    }

    public long possibleWays() {
        return possibleWays(0, new LinkedList<>(availableJolts));
    }

    private long possibleWays(int previous, LinkedList<Integer> adapters) {
        if (adapters.isEmpty()) {
            return 1L;
        }

        var count = 0L;
        while (!adapters.isEmpty() && adapters.peek() - previous < 4) {
            var current = adapters.remove();
            if (current - previous < 4) {
                var cachedCount = getFromCache(adapters);
                if (cachedCount == null) {
                    cachedCount = possibleWays(current, new LinkedList<>(adapters));
                    putInCache(adapters, cachedCount);
                }
                count += cachedCount;
            }
        }
        return count;
    }

    // use the cache for huge speed improvements :)
    public void putInCache(LinkedList<Integer> elements, Long count) {
        cache.put(elements, count);
    }

    public Long getFromCache(LinkedList<Integer> elements) {
        return cache.get(elements);
    }

    private boolean isNotExceeding(List<Integer> list, Integer availableJolt) {
        int lastListElement = list.size() == 0 ? 0 : list.get(list.size() - 1);
        return availableJolt - lastListElement < 4;
    }

    private static boolean isValid(List<Integer> list) {
        int previous = 0;
        for (Integer current : list) {
            int diff = current - previous;
            if (diff < 1 || diff > 3) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static class Result {
        private int oneJoltDiffs;
        private int twoJoltDiffs;
        private int threeJoltDiffs;

        public Result() {
            this.oneJoltDiffs = 0;
            this.twoJoltDiffs = 0;
            this.threeJoltDiffs = 0;
        }

        private void increaseOneJoltDiffs() {
            oneJoltDiffs++;
        }

        private void increaseTwoJoltDiffs() {
            twoJoltDiffs++;
        }

        private void increaseThreeJoltDiffs() {
            threeJoltDiffs++;
        }

        public int getOneJoltDiffs() {
            return oneJoltDiffs;
        }

        public int getTwoJoltDiffs() {
            return twoJoltDiffs;
        }

        public int getThreeJoltDiffs() {
            return threeJoltDiffs;
        }

        public int outcome() {
            return oneJoltDiffs * threeJoltDiffs;
        }
    }
}
