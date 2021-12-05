package be.bitbox.adventofcode.y2021.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeaFloor {
    private final List<HydroThermalVent> vents;

    public SeaFloor(List<String> input) {
        vents = new ArrayList<>();
        for (String line : input) {
            vents.add(new HydroThermalVent(line));
        }
    }

    public Long numberOfOverlaps(boolean withDiagnols) {
        var collect = vents.stream()
                .filter(hydroThermalVent -> withDiagnols || hydroThermalVent.isNotDiagonal())
                .flatMap(hydroThermalVent -> hydroThermalVent.getTuples().stream())
                .collect(Collectors.groupingBy(tuple -> tuple, Collectors.counting()));

        return collect.values()
                .stream()
                .filter(count -> count > 1)
                .count();
    }
}
