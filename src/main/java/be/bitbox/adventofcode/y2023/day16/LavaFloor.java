package be.bitbox.adventofcode.y2023.day16;

import be.bitbox.adventofcode.Tuple;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class LavaFloor {

    private final char[][] matrix;
    private final int height;
    private final int width;

    private final Set<Beam> visited = new HashSet<>();

    public LavaFloor(List<String> input) {
        height = input.size();
        width = input.get(0).length();
        matrix = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = input.get(i).charAt(j);
            }
        }
    }

    public int maxNumberEnergized() {
        int maximalEnergy = 0;

        for (int i = 0; i < height; i++) {
            var energy = numberEnergized(new Beam(i, 0, Direction.EAST));
            maximalEnergy = Math.max(energy, maximalEnergy);

            energy = numberEnergized(new Beam(i, width - 1, Direction.WEST));
            maximalEnergy = Math.max(energy, maximalEnergy);
        }

        for (int i = 0; i < width; i++) {
            var energy = numberEnergized(new Beam(0, i, Direction.SOUTH));
            maximalEnergy = Math.max(energy, maximalEnergy);

            energy = numberEnergized(new Beam(i, height - 1, Direction.NORTH));
            maximalEnergy = Math.max(energy, maximalEnergy);
        }
        return maximalEnergy;
    }

    public int numberEnergized() {
        return numberEnergized(new Beam(0, 0, Direction.EAST));
    }

    public int numberEnergized(Beam startBeam) {
        visited.clear();

        visit(startBeam);

        Set<Tuple<Integer, Integer>> unique = new HashSet<>();
        visited.forEach(beam -> unique.add(new Tuple<>(beam.x, beam.y)));
        return unique.size();
    }

    public void visit(Beam beam) {
        if (beam.x < 0 || beam.y < 0 || beam.x >= height || beam.y >= width) {
            return;
        }

        if (visited.contains(beam)) {
            return;
        }

        char current = matrix[beam.x][beam.y];
        visited.add(beam);
        beam.nextBeam(current).forEach(this::visit);
    }

    public record Beam(int x, int y, Direction direction) {
        List<Beam> nextBeam(char current) {
            return direction.nextDirections(current).stream()
                    .map(this::nextBeam)
                    .toList();
        }

        Beam nextBeam(Direction nextDirection) {
            return new Beam(x + nextDirection.x, y + nextDirection.y, nextDirection);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Beam beam = (Beam) o;
            return x == beam.x && y == beam.y && direction == beam.direction;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, direction);
        }
    }

    public enum Direction {
        NORTH(-1, 0), EAST(0, 1), SOUTH(1, 0), WEST(0, -1);

        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        List<Direction> nextDirections(char current) {
            if (current == '.') {
                return List.of(this);
            }
            if (current == '/') {
                switch (this) {
                    case NORTH -> {
                        return List.of(EAST);
                    }
                    case EAST -> {
                        return List.of(NORTH);
                    }
                    case SOUTH -> {
                        return List.of(WEST);
                    }
                    case WEST -> {
                        return List.of(SOUTH);
                    }
                }
            }
            if (current == '\\') {
                switch (this) {
                    case NORTH -> {
                        return List.of(WEST);
                    }
                    case EAST -> {
                        return List.of(SOUTH);
                    }
                    case SOUTH -> {
                        return List.of(EAST);
                    }
                    case WEST -> {
                        return List.of(NORTH);
                    }
                }
            }
            if (current == '|') {
                switch (this) {
                    case NORTH:
                    case SOUTH:
                        return List.of(this);
                    case EAST:
                    case WEST:
                        return List.of(NORTH, SOUTH);
                }
            }
            if (current == '-') {
                switch (this) {
                    case NORTH:
                    case SOUTH:
                        return List.of(EAST, WEST);
                    case EAST:
                    case WEST:
                        return List.of(this);
                }
            }
            throw new IllegalStateException("NOO " + current);
        }
    }
}
