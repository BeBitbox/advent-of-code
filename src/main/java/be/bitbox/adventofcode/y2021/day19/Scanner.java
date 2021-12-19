package be.bitbox.adventofcode.y2021.day19;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("SuspiciousNameCombination")
public class Scanner {
    public static final List<Function<Coordinate, Coordinate>> ROTATION_FUNCTIONS = List.of(
            Function.identity(),
            coordinate -> new Coordinate(-coordinate.y, coordinate.x, coordinate.z),
            coordinate -> new Coordinate(-coordinate.x, -coordinate.y, coordinate.z),
            coordinate -> new Coordinate(coordinate.y, -coordinate.x, coordinate.z));
    public static final List<Function<Coordinate, Coordinate>> DIRECTION_FUNCTIONS = List.of(
            Function.identity(),
            coordinate -> new Coordinate(coordinate.x, -coordinate.y, -coordinate.z),
            coordinate -> new Coordinate(coordinate.x, -coordinate.z, coordinate.y),
            coordinate -> new Coordinate(-coordinate.y, -coordinate.z, coordinate.x),
            coordinate -> new Coordinate(-coordinate.x, -coordinate.z, -coordinate.y),
            coordinate -> new Coordinate(coordinate.y, -coordinate.z, -coordinate.x));
    private final Integer number;
    private final List<Coordinate> coordinates;
    private final Coordinate scannerLocation;

    public Scanner(List<String> inputList) {
        number = Integer.parseInt(inputList.get(0).split(" ")[2]);
        coordinates = new ArrayList<>();
        for (int i = 1; i < inputList.size(); i++) {
            var line = inputList.get(i).trim();
            if (line.length() > 0) {
                coordinates.add(new Coordinate(line));
            }
        }
        coordinates.forEach(coordinate ->
                coordinates.forEach(other -> {
                    if (coordinate != other) {
                        coordinate.addDistance(other);
                    }
                })
        );
        scannerLocation = new Coordinate(0, 0, 0);
    }

    public Scanner(Integer number, List<Coordinate> coordinates, Coordinate scannerLocation) {
        this.number = number;
        this.coordinates = coordinates;
        coordinates.forEach(coordinate ->
                coordinates.forEach(other -> {
                    if (coordinate != other) {
                        coordinate.addDistance(other);
                    }
                })
        );
        this.scannerLocation = scannerLocation;
    }

    public Set<Scanner> rotate() {
        Set<Scanner> scanners = new HashSet<>();
        for (Function<Coordinate, Coordinate> upFunction : DIRECTION_FUNCTIONS) {
            for (Function<Coordinate, Coordinate> rotFunction : ROTATION_FUNCTIONS) {
                List<Coordinate> copied = coordinates.stream()
                        .map(upFunction)
                        .map(rotFunction)
                        .collect(toList());
                scanners.add(new Scanner(number, copied, null));
            }
        }
        return scanners;
    }

    public Scanner add(Scanner.Coordinate offset) {
        return new Scanner(number, coordinates.stream()
                .map(coordinate -> new Coordinate(coordinate.x + offset.x, coordinate.y + offset.y, coordinate.z + offset.z))
                .collect(toList()), offset);
    }

    public static class Coordinate {
        public int x, y, z;
        public List<Double> relativeDistances;

        private Coordinate(String line) {
            var split = line.split(",");
            x = Integer.parseInt(split[0]);
            y = Integer.parseInt(split[1]);
            z = Integer.parseInt(split[2]);
            relativeDistances = new ArrayList<>();
        }

        public Coordinate(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            relativeDistances = new ArrayList<>();
        }

        public int manhattanDistance(Coordinate coordinate) {
            return Math.abs(x - coordinate.x) + Math.abs(y - coordinate.y) + Math.abs(z - coordinate.z);
        }

        public void addDistance(Coordinate coordinate) {
            var distance = Math.sqrt(
                    Math.pow(coordinate.x - x, 2) +
                            Math.pow(coordinate.y - y, 2) +
                            Math.pow(coordinate.z - z, 2));
            relativeDistances.add(distance);
        }

        public Coordinate determineNewPosition(Coordinate coordinate) {
            return new Coordinate(coordinate.x - x, coordinate.y - y, coordinate.z - z);
        }

        public List<Double> getRelativeDistances() {
            return relativeDistances;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y && z == that.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }

        @Override
        public String toString() {
            return "[x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    ']';
        }
    }

    public Integer getNumber() {
        return number;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public Coordinate getScannerLocation() {
        return scannerLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scanner scanner = (Scanner) o;
        return Objects.equals(number, scanner.number) && Objects.equals(coordinates, scanner.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, coordinates);
    }

    @Override
    public String toString() {
        return "Scanner{" +
                "number=" + number +
                ", coordinates=" + coordinates +
                '}';
    }
}
