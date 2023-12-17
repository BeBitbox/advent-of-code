package be.bitbox.adventofcode.y2023.day12;

import java.util.*;

public class HotSpring {

    private final char[] sequence;
    private final List<Integer> positions;
    private final Map<String, Long> cache = new HashMap<>();


    public HotSpring(String line) {
        var splittedOnSpace = line.split(" ");

        sequence = splittedOnSpace[0].toCharArray();
        positions = Arrays.stream(splittedOnSpace[1].split(","))
                .map(Integer::parseInt)
                .toList();
    }

    public HotSpring(String line, int crazy) {
        var splittedOnSpace = line.split(" ");
        var temp = splittedOnSpace[0].chars()
                .mapToObj(i -> (char) i)
                .toList();
        var tempList = new ArrayList<>();
        for (int i = 0; i < crazy; i++) {
            tempList.addAll(temp);
            if (i < crazy - 1) {
                tempList.add('?');
            }
        }

        sequence = new char[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            sequence[i] = (char) tempList.get(i);
        }


        positions = new ArrayList<>();
        var temp2 = Arrays.stream(splittedOnSpace[1].split(","))
                .map(Integer::parseInt)
                .toList();
        for (int i = 0; i < crazy; i++) {
            positions.addAll(temp2);
        }
    }

    public long fillInBlanks() {
        cache.clear();
        return fillUp(sequence, positions);
    }

    private long fillUp(char[] array, List<Integer> numberOfSpring) {
        var key = new String(array) + numberOfSpring;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (array.length == 0) {
            if (numberOfSpring.isEmpty()) {
                return 1L;
            } else {
                return 0L;
            }
        }

        if (array[0] == '.') {
            var value = fillUp(Arrays.copyOfRange(array, 1, array.length), numberOfSpring);
            cache.put(key, value);
            return value;
        }
        if (array[0] == '#') {
            if (numberOfSpring.isEmpty()) {
                return 0L;
            }
            var positions = numberOfSpring.get(0);
            if (array.length < positions) {
                return 0L;
            }
            for (int i = 0; i < positions; i++) {
                if (array[i] == '.') {
                    return 0L;
                }
            }
            if (array.length > positions){
                if (array[positions] == '#') {
                    return 0L;
                }
                positions++;
            }

            var newCharacters = Arrays.copyOfRange(array, positions, array.length);
            var newNumber = new ArrayList<>(numberOfSpring);
            newNumber.remove(0);
            var value = fillUp(newCharacters, newNumber);
            cache.put(key, value);
            return value;
        }
        if (array[0] == '?') {
            array[0] = '.';
            var one = fillUp(array, numberOfSpring);
            array[0] = '#';
            var two = fillUp(array, numberOfSpring);
            var sum = one + two;
            cache.put(key, sum);
            return sum;
        }
        throw new IllegalStateException("OEPS");
    }
}
