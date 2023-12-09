package be.bitbox.adventofcode.y2023.day8;

import be.bitbox.adventofcode.Tuple;
import be.bitbox.adventofcode.y2023.MathUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HauntedWasteLand {
    private final Map<String, Tuple<String, String>> legend;
    private final List<Character> directions;

    public HauntedWasteLand(List<String> inputList) {
        directions = inputList.get(0).chars()
                .mapToObj(i -> (char) i)
                .toList();

        legend = new HashMap<>();
        for (int i = 2; i < inputList.size(); i++) {
            var line = inputList.get(i);
            var splittedOnSpace = line.split(" ");
            var key = splittedOnSpace[0];
            var left = splittedOnSpace[2].substring(1, 4);
            var right = splittedOnSpace[3].substring(0, 3);
            legend.put(key, new Tuple<>(left, right));
        }
    }

    public int numberOfStepsSequential(String key) {
        int steps = 0;

        for (int i = 0; i < directions.size(); i++) {
            steps++;
            var stringTuple = legend.get(key);
            if (directions.get(i) == 'L') {
                key = stringTuple.x;
            } else {
                key = stringTuple.y;
            }
            if (key.endsWith("Z")) {
                return steps;
            }

            // RESET
            if (i == directions.size() - 1) {
                i = -1;
            }

        }
        return 0;
    }

    public long numberOfStepsParallel() {
        var keys = legend.keySet().stream()
                .filter(key -> key.endsWith("A"))
                .toList();


        var list = keys.stream()
                .map(this::numberOfStepsSequential)
                .toList();

        long lcm = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            lcm = MathUtil.calculateLowestCommonMultiple(lcm, list.get(i));
        }

        return lcm;
    }

    public Map<String, Tuple<String, String>> getLegend() {
        return legend;
    }

    public List<Character> getDirections() {
        return directions;
    }
}
