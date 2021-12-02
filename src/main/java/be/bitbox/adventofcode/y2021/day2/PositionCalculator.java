package be.bitbox.adventofcode.y2021.day2;

import java.util.List;

public class PositionCalculator {
    private final List<String> positions;

    public PositionCalculator(List<String> positions) {
        this.positions = positions;
    }

    public int calculatePosition() {
        int horizontal = 0, depth = 0;

        for (String position : positions) {
            var split = position.split(" ");
            var direction = split[0];
            var distance = Integer.parseInt(split[1]);

            if (direction.startsWith("for")) {
                horizontal += distance;
            } else if (direction.startsWith("up")) {
                depth -= distance;
            } else {
                depth += distance;
            }

        }
        return horizontal * depth;
    }

    public int calculatePosition2() {
        int horizontal = 0, depth = 0, aim = 0;

        for (String position : positions) {
            var split = position.split(" ");
            var direction = split[0];
            var distance = Integer.parseInt(split[1]);

            if (direction.startsWith("for")) {
                horizontal += distance;
                depth += aim * distance;
            } else if (direction.startsWith("up")) {
                aim -= distance;
            } else {
                aim += distance;
            }

        }
        return horizontal * depth;
    }
}
