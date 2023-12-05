package be.bitbox.adventofcode.y2023.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class SeedFarmer {

    private final List<Long> seeds;
    private final List<Transformer> transformerList;

    public SeedFarmer(List<String> inputList) {
        seeds = Arrays.stream(inputList.get(0).split(": ")[1].split(" "))
                .map(Long::parseLong)
                .toList();

        transformerList = new ArrayList<>();
        int startIndex = 2;
        for (int i = startIndex; i < inputList.size(); i++) {
            if (inputList.get(i).trim().isEmpty() || i == inputList.size() - 1) {
                transformerList.add(new Transformer(inputList.subList(startIndex, i)));
                startIndex = i + 1;
            }
        }
    }

    public long minimumLocation() {
        return seeds.stream()
                .map(seed -> {
                    long result = seed;
                    for (Transformer transformer : transformerList) {
                        result = transformer.morph(result);
                    }
                    return result;
                })
                .min(Long::compareTo)
                .orElseThrow();
    }

    public long minimumLocationWithSeedRanges() {
        long minimum = Long.MAX_VALUE;
        for (int i = 0; i < seeds.size(); i = i + 2) {
            minimum = Math.min(minimum, minimumLocationWithSeedRanges(seeds.get(i), seeds.get(i + 1)));
        }
        return minimum;
    }

    private long minimumLocationWithSeedRanges(long start, long range) {
        return LongStream.range(start, start + range)
                .parallel()
                .map(seed -> {
                    long result = seed;
                    for (Transformer transformer : transformerList) {
                        result = transformer.morph(result);
                    }
                    return result;
                })
                .boxed()
                .min(Long::compareTo)
                .orElseThrow();
    }

    public List<Long> getSeeds() {
        return seeds;
    }

    public List<Transformer> getTransformerList() {
        return transformerList;
    }
}
