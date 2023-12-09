package be.bitbox.adventofcode.y2023.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MirageMaintenance {

    private final List<List<Integer>> historyLines;

    public MirageMaintenance(List<String> inputList) {
        historyLines = inputList.stream()
                .map(line -> Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .toList())
                .toList();
    }

    public long prediction() {
        return historyLines.stream()
                .map(MirageMaintenance::prediction)
                .reduce(Long::sum)
                .orElseThrow();
    }

    public long predictionReversed() {
        return historyLines.stream()
                .map(list -> prediction(list.reversed()))
                .reduce(Long::sum)
                .orElseThrow();
    }

    public static long prediction(List<Integer> integerList) {
        if(integerList.stream().allMatch(integer -> integer == 0)) {
            return 0;
        }

        var nextLine = new ArrayList<Integer>();
        for (int i = 1; i < integerList.size(); i++) {
            var diff = integerList.get(i) - integerList.get(i - 1);
            nextLine.add(diff);
        }

        return prediction(nextLine) + integerList.getLast();
    }
}
