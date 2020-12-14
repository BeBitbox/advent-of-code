package be.bitbox.adventofcode.y2020.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FerryProgram {

    private final Map<Long, Long> memoryMap;
    private final Map<Long, Long> floatingMemoryMap;

    public FerryProgram(List<String> input) {
        memoryMap = new HashMap<>();
        floatingMemoryMap = new HashMap<>();

        BitMask currentBitMask = new BitMask("");
        for (String line : input) {
            if (line.startsWith("mem")) {
                var parts = line.split(" ");
                var memoryIndex = Long.parseLong(parts[0].substring(4, parts[0].indexOf("]")));

                var value = Long.parseLong(parts[2]);
                var maskedValue = currentBitMask.afterMaskApplication(value);
                memoryMap.put(memoryIndex, maskedValue);

                var updatableIndexes = currentBitMask.afterFloatingMaskApplication(memoryIndex);
                updatableIndexes.forEach(index -> floatingMemoryMap.put(index, value));
            } else if (line.startsWith("mask")){
                var parts = line.split(" ");
                currentBitMask = new BitMask(parts[2]);
            }
        }
    }

    public long collectiveSum() {
        return memoryMap.values().stream()
                .reduce(Long::sum)
                .orElse(0L);
    }

    public long collectiveFloatingSum() {
        return floatingMemoryMap.values().stream()
                .reduce(Long::sum)
                .orElse(0L);
    }
}
