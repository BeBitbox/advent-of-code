package be.bitbox.adventofcode.y2021.day22;

import be.bitbox.adventofcode.Interval;

public class Instruction {
    private final boolean on;
    private final Interval xRange;
    private final Interval yRange;
    private final Interval zRange;

    public Instruction(String input) {
        if (input.startsWith("on")) {
            on = true;
        } else {
            on = false;
        }
        var split = input.split(",");
        xRange = getFrom(split[0]);
        yRange = getFrom(split[1]);
        zRange = getFrom(split[2]);
    }

    private static Interval getFrom(String input) {
        var numbers = input.split("=")[1];
        var split = numbers.split("\\.\\.");
        return new Interval(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    public boolean isOn() {
        return on;
    }

    public Interval getxRange() {
        return xRange;
    }

    public Interval getyRange() {
        return yRange;
    }

    public Interval getzRange() {
        return zRange;
    }
}
