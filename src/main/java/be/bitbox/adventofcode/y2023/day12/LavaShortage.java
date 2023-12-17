package be.bitbox.adventofcode.y2023.day12;

import java.util.List;

public class LavaShortage {

    private final List<HotSpring> hotSprings;

    public LavaShortage(List<String> inputs, boolean crazy) {
        if (crazy) {
            hotSprings = inputs.stream()
                    .map(line -> new HotSpring(line, 5))
                    .toList();
        } else {
            hotSprings = inputs.stream()
                    .map(HotSpring::new)
                    .toList();
        }
    }

    public long possibilities() {
        return hotSprings.stream()
                .parallel()
                .map(HotSpring::fillInBlanks)
                .reduce(Long::sum)
                .orElseThrow();
    }
}
