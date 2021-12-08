package be.bitbox.adventofcode.y2021.day8;

import java.util.List;
import java.util.stream.Collectors;

public class DigitCounter {

    private final List<DigitalLine> digitalLines;

    public DigitCounter(List<String> lines) {
        digitalLines = lines.stream()
                .map(DigitalLine::new)
                .collect(Collectors.toList());
    }

    public int calculateNumberOf_1_4_7_8() {
        return digitalLines.stream()
                .map(DigitalLine::numberOf_1_4_7_8)
                .reduce(Integer::sum)
                .orElse(-1);
    }

    public int sumOutputs() {
        return digitalLines.stream()
                .map(DigitalLine::output)
                .reduce(Integer::sum)
                .orElse(-1);
    }
}
