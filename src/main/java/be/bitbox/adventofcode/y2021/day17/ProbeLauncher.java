package be.bitbox.adventofcode.y2021.day17;


import be.bitbox.adventofcode.Interval;
import be.bitbox.adventofcode.Tuple;

import java.util.LinkedList;

public class ProbeLauncher {

    private final Interval xRange;
    private final Interval yRange;

    public ProbeLauncher(String input) {
        var split = input.split("=");
        var splitX = (split[1].split(",")[0]).split("\\.\\.");
        var splitY = split[2].split("\\.\\.");
        xRange = new Interval(Integer.parseInt(splitX[0]), Integer.parseInt(splitX[1]));
        yRange = new Interval(Integer.parseInt(splitY[0]), Integer.parseInt(splitY[1]));
    }

    public Tuple<Integer, Integer> calculateHightestAndTotal() {
        int maxHeight = 0;
        int totalHits = 0;
        for (int i = 0; i <= xRange.right; i++) {
            for (int j = yRange.left; j <= Math.abs(yRange.left); j++) {
                var trajectory = createTrajectory(new Tuple<>(i, j));
                if (insideOceanTrench(trajectory)) {
                    totalHits++;
                    var localMax = trajectory.stream()
                            .map(tuple -> tuple.y)
                            .max(Integer::compareTo)
                            .orElse(0);
                    if (localMax > maxHeight) {
                        maxHeight = localMax;
                    }
                }
            }
        }

        return new Tuple<>(maxHeight, totalHits);
    }

    public LinkedList<Tuple<Integer, Integer>> createTrajectory(Tuple<Integer, Integer> velocity) {
        LinkedList<Tuple<Integer, Integer>> trajectory = new LinkedList<>();
        var current = new Tuple<>(0, 0);
        var currentVelocity = new Tuple<>(velocity);

        while (current.x <= xRange.right && current.y >= yRange.left) {
            trajectory.add(current);

            current = new Tuple<>(current.x + currentVelocity.x, current.y + currentVelocity.y);
            var newLeftVelocity = 0;
            if (currentVelocity.x > 0) {
                newLeftVelocity = currentVelocity.x - 1;
            } else if (currentVelocity.x < 0) {
                newLeftVelocity = currentVelocity.x + 1;
            }
            currentVelocity = new Tuple<>(newLeftVelocity, currentVelocity.y - 1);
        }

        return trajectory;
    }

    public boolean insideOceanTrench(LinkedList<Tuple<Integer, Integer>> trajectory) {
        var last = trajectory.peekLast();
        return xRange.contains(last.x) && yRange.contains(last.y);
    }

    public Interval getxRange() {
        return xRange;
    }

    public Interval getyRange() {
        return yRange;
    }
}
