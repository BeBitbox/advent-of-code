package be.bitbox.adventofcode.y2021.day19;

import be.bitbox.adventofcode.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class BeaconScanner {
    private final List<Scanner> scannerList;

    public BeaconScanner(List<String> inputList) {
        scannerList = new ArrayList<>();

        int startPoint = 0;
        for (int i = 1; i < inputList.size(); i++) {
            if (inputList.get(i).startsWith("---")) {
                scannerList.add(new Scanner(inputList.subList(startPoint, i - 1)));
                startPoint = i;
            }
        }
        scannerList.add(new Scanner(inputList.subList(startPoint, inputList.size())));
    }

    public Tuple<Set<Scanner.Coordinate>, Integer> compare() {
        List<Scanner> resolvedScanners = new ArrayList<>();
        List<Scanner> toHandleScanners = new ArrayList<>(scannerList);
        var first = scannerList.get(0);
        resolvedScanners.add(first);
        toHandleScanners.remove(first);

        while (toHandleScanners.size() > 0) {
            var newMatchedScanner = getNewMatchedScanner(resolvedScanners, toHandleScanners);
            resolvedScanners.add(newMatchedScanner);
            toHandleScanners.removeIf(scanner -> scanner.getNumber().equals(newMatchedScanner.getNumber()));
        }

        var beacons = resolvedScanners.stream()
                .flatMap(scanner -> scanner.getCoordinates().stream())
                .collect(Collectors.toSet());
        int maxDistance = 0;
        for (int i = 0; i < resolvedScanners.size(); i++) {
            for (int j = i + 1; j < resolvedScanners.size(); j++) {
                var scanner1 = resolvedScanners.get(i);
                var scanner2 = resolvedScanners.get(j);
                var manhattanDistance = scanner1.getScannerLocation().manhattanDistance(scanner2.getScannerLocation());
                if (manhattanDistance > maxDistance) {
                    maxDistance = manhattanDistance;
                }
            }
        }
        return new Tuple<>(beacons, maxDistance);

    }

    private Scanner getNewMatchedScanner(List<Scanner> resolvedScanners, List<Scanner> toHandleScanners) {
        for (Scanner scanner : resolvedScanners) {
            for (Scanner matchScanner : toHandleScanners) {
                var coordinateList = getMatches(scanner, matchScanner);
                if (coordinateList.size() >= 12) {
                    return determine(scanner, matchScanner);
                }
            }

        }
        return null;
    }

    private List<Tuple<Scanner.Coordinate, Scanner.Coordinate>> getMatches(Scanner scanner, Scanner matchScanner) {
        List<Tuple<Scanner.Coordinate, Scanner.Coordinate>> coordinateList = new ArrayList<>();
        scanner.getCoordinates().forEach(coordinate -> {
                    matchScanner.getCoordinates().forEach(coordinate2 -> {
                        var collect = coordinate.getRelativeDistances().stream()
                                .filter(distance -> coordinate2.relativeDistances.contains(distance))
                                .collect(toList());
                        if (collect.size() > 10) {
                            coordinateList.add(new Tuple<>(coordinate, coordinate2));
                        }
                    });
                }
        );
        return coordinateList;
    }

    private Scanner determine(Scanner scanner, Scanner matchScanner) {
        var rotatedScanners = matchScanner.rotate();

        var matchedScanner = rotatedScanners.stream()
                .filter(rotated -> isMatch(scanner, rotated) != null)
                .collect(toList());
        assert matchedScanner.size() == 1;

        var matched = matchedScanner.get(0);
        var matchedCoordinate = isMatch(scanner, matched);

        return matched.add(matchedCoordinate);
    }

    private Scanner.Coordinate isMatch(Scanner scanner, Scanner matchScanner) {
        var matches = getMatches(scanner, matchScanner);
        return isMatching(matches);
    }

    private Scanner.Coordinate isMatching(List<Tuple<Scanner.Coordinate, Scanner.Coordinate>> list) {
        Scanner.Coordinate matchScannerPosition = null;
        for (Tuple<Scanner.Coordinate, Scanner.Coordinate> tuple : list) {
            Scanner.Coordinate temp = tuple.y.determineNewPosition(tuple.x);
            if (matchScannerPosition == null) {
                matchScannerPosition = temp;
            } else if (!matchScannerPosition.equals(temp)) {
                return null;
            }
        }
        return matchScannerPosition;
    }

    public List<Scanner> getScannerList() {
        return scannerList;
    }
}
