package be.bitbox.adventofcode.y2021.day1;

import java.util.ArrayList;
import java.util.List;

public class DepthCalculator {
    private final List<Integer> depths;
    private final List<Integer> measurementWindows;

    public DepthCalculator(List<Integer> depths) {
        this.depths = depths;
        this.measurementWindows = calcMeasurementWindows();
    }

    public List<Integer> getMeasurementWindows() {
        return measurementWindows;
    }

    public int numberOfDepthIncreases() {
        int initialDepth = Integer.MAX_VALUE;
        int counter = 0;

        for (Integer depth : depths) {
            if (depth > initialDepth) {
                counter++;
            }
            initialDepth = depth;
        }
        return counter;
    }

    private List<Integer> calcMeasurementWindows() {
        List<Integer> measurementWindows = new ArrayList<>();

        for (int i = 0; i < depths.size() - 2; i++) {
            measurementWindows.add(depths.get(i) + depths.get(i + 1) + depths.get(i + 2));
        }
        return measurementWindows;
    }
}
