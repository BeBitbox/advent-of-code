package be.bitbox.adventofcode.y2023.day10;

import be.bitbox.adventofcode.Tuple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PipeMaze {

    private final char[][] matrix;
    private final int height;
    private final int width;
    private Direction startDirection;
    private Tuple<Integer, Integer> start;

    public PipeMaze(List<String> stringList) {
        height = stringList.size();
        width = stringList.get(0).length();
        this.matrix = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = stringList.get(i).charAt(j);
                if (matrix[i][j] == 'S') {
                    start = new Tuple<>(i, j);
                }
            }
        }
    }

    public int loopSize() {
        List<Tuple<Integer, Integer>> path = new ArrayList<>();
        List<Tuple<Integer, Integer>> temp;

        temp = path(start, Direction.NORTH);
        if (temp.size() > path.size()) {
            startDirection = Direction.NORTH;
            path = temp;
        }
        temp = path(start, Direction.WEST);
        if (temp.size() > path.size()) {
            startDirection = Direction.WEST;
            path = temp;
        }
        temp = path(start, Direction.SOUTH);
        if (temp.size() > path.size()) {
            startDirection = Direction.SOUTH;
            path = temp;
        }
        temp = path(start, Direction.EAST);
        if (temp.size() > path.size()) {
            startDirection = Direction.EAST;
            path = temp;
        }
        return path.size() / 2;
    }

    private List<Tuple<Integer, Integer>> path(Tuple<Integer, Integer> currentPosition, Direction direction) {
        List<Tuple<Integer, Integer>> list = new ArrayList<>();

        var currentChar = 'S';
        var nextDirection = direction;
        var nextStop = currentPosition;

        do {
            nextStop = nextDirection.nextStop(nextStop);
            if (nextStop.x < 0 || nextStop.y < 0 || nextStop.x > height - 1 || nextStop.y > width - 1) {
                return list;
            }
            list.add(nextStop);

            currentChar = matrix[nextStop.x][nextStop.y];
            nextDirection = nextDirection.nextDirection(currentChar);

            if (nextDirection == null) {
                return list;
            }
        } while (currentChar != 'S');

        return list;
    }

    public int insideLoopers() {
        if (startDirection == null) {
            throw new IllegalArgumentException("Call Loopsize first");
        }

        var path = path(start, startDirection);
        Set<Tuple<Integer, Integer>> insideLoopers = new HashSet<>();

        for (int i = 0; i < height; i++) {
            boolean inside = false;
            var index = -1;
            for (int j = 0; j < width; j++) {
                var tuple = new Tuple<>(i, j);
                if (path.contains(tuple)) {
                    if (index < 0) {
                        inside = !inside;
                        index = path.indexOf(tuple);
                    } else {
                        var index2 = path.indexOf(tuple);
                        if (Math.abs(index2 - index) == 1 || Math.abs(index2 - index) == path.size() - 1) {
                            index = index2;
                        } else {
                            index = index2;
                            inside = !inside;
                        }
                    }

                } else {
                    index = -1;
                    if (inside) {
                        insideLoopers.add(tuple);
                    }
                }
            }
        }
        Set<Tuple<Integer, Integer>> verified = new HashSet<>();
        for (Tuple<Integer, Integer> insideLooper: insideLoopers) {
            Set<Tuple<Integer, Integer>> set = new HashSet<>();

            try {
                visit(path, set, insideLooper);
                verified.addAll(set);
            } catch (NotInsideLoopException ignored) {}
        }

        return verified.size();
    }
    
    public void visit(List<Tuple<Integer, Integer>> boundaries, Set<Tuple<Integer, Integer>> current, Tuple<Integer, Integer> start) {
        if (boundaries.contains(start) || current.contains(start)) {
            return;
        }

        if (start.x < 0 || start.y < 0 || start.x > height - 1 || start.y > width - 1) {
            throw new NotInsideLoopException();    
        }

        current.add(start);
        visit(boundaries, current, Direction.NORTH.nextStop(start));
        visit(boundaries, current, Direction.SOUTH.nextStop(start));
        visit(boundaries, current, Direction.WEST.nextStop(start));
        visit(boundaries, current, Direction.EAST.nextStop(start));
    }
    
    public class NotInsideLoopException extends RuntimeException {
        
    }

    public enum Direction {

        NORTH(-1, 0), EAST(0, 1), WEST(0, -1), SOUTH(1, 0);

        final int x;
        final int y;
        Direction next;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Tuple<Integer, Integer> nextStop(Tuple<Integer, Integer> currentPosition) {
            return new Tuple<>(currentPosition.x + x, currentPosition.y + y);
        }

        Direction nextDirection(char c) {
            if (this == Direction.NORTH) {
                if (c == '|') {
                    return Direction.NORTH;
                } else if (c == '7') {
                    return Direction.WEST;
                } else if (c == 'F') {
                    return Direction.EAST;
                } else {
                    return null;
                }
            }
            if (this == Direction.SOUTH) {
                if (c == '|') {
                    return Direction.SOUTH;
                } else if (c == 'J') {
                    return Direction.WEST;
                } else if (c == 'L') {
                    return Direction.EAST;
                } else {
                    return null;
                }
            }
            if (this == Direction.EAST) {
                if (c == '-') {
                    return Direction.EAST;
                } else if (c == '7') {
                    return Direction.SOUTH;
                } else if (c == 'J') {
                    return Direction.NORTH;
                } else {
                    return null;
                }
            }
            if (this == Direction.WEST) {
                if (c == '-') {
                    return Direction.WEST;
                } else if (c == 'F') {
                    return Direction.SOUTH;
                } else if (c == 'L') {
                    return Direction.NORTH;
                } else {
                    return null;
                }
            }
            return null;
        }
    }

}
