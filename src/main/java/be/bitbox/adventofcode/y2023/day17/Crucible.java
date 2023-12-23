package be.bitbox.adventofcode.y2023.day17;

import java.util.*;

public class Crucible {

    final short[][] matrix;
    private final int[][] optimal;
    private final int width;
    private final int height;

    public Crucible(List<String> inputList) {
        height = inputList.size();
        width = inputList.get(0).length();

        matrix = new short[height][width];
        optimal = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = Byte.parseByte(String.valueOf(inputList.get(i).charAt(j)));
                optimal[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public long path() {
        PriorityQueue<Configuration> configurations = new PriorityQueue<>();

        configurations.add(new Configuration(new Position((short) 1, (short) 0), Direction.SOUTH, 1, 0));
        configurations.add(new Configuration(new Position((short) 0, (short) 1), Direction.EAST, 1, 0));
        Set<Configuration> finished = new HashSet<>();

        while (!configurations.isEmpty()) {
            var first = configurations.poll();
            if (finished.contains(first)) {
                continue;
            }
            var neighbours = visit(first);
            finished.add(first);
            neighbours.stream().filter(n -> !configurations.contains(n)).forEach(configurations::add);
        }

        return optimal[height - 1][width - 1];
    }

    private List<Configuration> visit(Configuration configuration) {
        var position = configuration.position;
        var weight = configuration.weight + matrix[position.x][position.y];

        if (weight - 8 > optimal[position.x][position.y]) {
            return List.of();
        }

        if (weight < optimal[position.x][position.y]) {
            optimal[position.x][position.y] = weight;
        }

        if (position.x == height - 1 && position.y == width - 1) {
            return List.of();
        }

        return nextPositions(configuration, weight);
    }

    List<Configuration> nextPositions(Configuration configuration, int weight) {
        var list = new ArrayList<Configuration>(3);

        configuration.direction.nextDirections().forEach(nextDirection -> {
            if (nextDirection.validInList(configuration.direction, configuration.times)) {
                var nextPosition = nextDirection.nextPosition(configuration.position);
                if (nextPosition.isValid(height, width)) {
                    list.add(new Configuration(nextPosition, nextDirection, nextDirection == configuration.direction ? configuration.times + 1 : 1, weight));
                }
            }
        });
        return list;
    }


    private record Configuration(Position position,
                                 Direction direction,
                                 int times,
                                 int weight) implements Comparable<Configuration> {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Configuration that = (Configuration) o;
            return times == that.times && Objects.equals(position, that.position) && direction == that.direction;
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, direction, times);
        }

        @Override
        public int compareTo(Configuration o) {
            return Long.compare(weight, o.weight);
        }
    }

    private record Position(short x, short y) {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public boolean isValid(int height, int width) {
            return x >= 0 && x < height && y >= 0 && y < width;
        }
    }


    public enum Direction {
        NORTH, EAST, SOUTH, WEST;
        public static final List<Direction> WEST2 = List.of(SOUTH, NORTH, WEST);
        public static final List<Direction> EAST2 = List.of(SOUTH, NORTH, EAST);
        public static final List<Direction> SOUTH1 = List.of(SOUTH, EAST, WEST);
        public static final List<Direction> NORTH1 = List.of(NORTH, EAST, WEST);

        Position nextPosition(Position position) {
            if (this == NORTH) {
                return new Position((short) (position.x - 1), position.y);
            }
            if (this == SOUTH) {
                return new Position((short) (position.x + 1), position.y);
            }
            if (this == EAST) {
                return new Position(position.x, (short) (position.y + 1));
            }
            if (this == WEST) {
                return new Position(position.x, (short) (position.y - 1));
            }
            throw new IllegalStateException("OEPS");
        }

        List<Direction> nextDirections() {
            if (this == NORTH) {
                return NORTH1;
            } else if (this == SOUTH) {
                return SOUTH1;
            } else if (this == EAST) {
                return EAST2;
            } else if (this == WEST) {
                return WEST2;
            }
            throw new IllegalStateException("OEPS");
        }

        boolean validInList(Direction direction, int times) {
            if (times < 3) {
                return true;
            }

            return direction != this;
        }
    }
}
