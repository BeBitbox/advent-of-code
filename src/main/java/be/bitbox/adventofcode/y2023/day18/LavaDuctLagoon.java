package be.bitbox.adventofcode.y2023.day18;

import be.bitbox.adventofcode.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class LavaDuctLagoon {

    private final List<Instruction> instructionList;

    public LavaDuctLagoon(List<String> inputList) {
        instructionList = inputList.stream()
                .map(str -> {
                    var split = str.split(" ");
                    return new Instruction(Direction.of(split[0]), Integer.parseInt(split[1]), split[2]);
                })
                .toList();
    }

    public double surface(boolean part1) {
        var tuples = new ArrayList<Tuple<Long, Long>>();
        AtomicLong x = new AtomicLong();
        AtomicLong y = new AtomicLong();
        AtomicLong perimeter = new AtomicLong();

        instructionList.forEach(instruction -> {
            var direction = part1 ? instruction.direction : instruction.alternateDirection();
            var length = part1 ? instruction.length : instruction.alternateLength();

            tuples.add(new Tuple<>(x.get(), y.get()));
            perimeter.addAndGet(length);
            switch (direction) {
                case UP -> y.addAndGet(-length);
                case DOWN -> y.addAndGet(length);
                case LEFT -> x.addAndGet(-length);
                case RIGHT -> x.addAndGet(length);
            }
        });

        var area = shoelaceFunction(tuples);
        return picksTheorem(area, perimeter.get());
    }

    private static double shoelaceFunction(ArrayList<Tuple<Long, Long>> tuples) {
        double area = 0.0;
        for (int i = 0; i < tuples.size(); i++) {
            var current = tuples.get(i);
            var next = tuples.get((i + 1) % tuples.size());
            area += (current.x * next.y) - (next.x * current.y);
        }

        return Math.abs(area / 2);
    }

    private static double picksTheorem(double area, long perimeter) {
        return area + (double) perimeter / 2 + 1;
    }

    public record Instruction(Direction direction, int length, String colorCode) {
        Direction alternateDirection() {
            return switch (colorCode.charAt(colorCode.length() - 2)) {
                case '0' -> Direction.RIGHT;
                case '1' -> Direction.DOWN;
                case '2' -> Direction.LEFT;
                case '3' -> Direction.UP;
                default -> throw new IllegalStateException("unexpected char " + colorCode);
            };
        }

        Integer alternateLength() {
            return Integer.parseInt(colorCode.substring(2, colorCode.length() - 2), 16);
        }
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;

        static Direction of(String s) {
            return switch (s) {
                case "R" -> RIGHT;
                case "L" -> LEFT;
                case "D" -> DOWN;
                case "U" -> UP;
                default -> throw new IllegalStateException("unknown " + s);
            };
        }
    }
}
