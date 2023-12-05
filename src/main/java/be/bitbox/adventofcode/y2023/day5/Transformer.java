package be.bitbox.adventofcode.y2023.day5;

import java.util.ArrayList;
import java.util.List;

public class Transformer {

    public static final Transform DEFAULT_TRANSFORM = new Transform(0, 0, 0);
    private final TYPE source;
    private final TYPE destination;
    private final List<Transform> transformList;

    public Transformer(List<String> inputs) {
        var splitLine = inputs.get(0).split(" ")[0].split("-");
        source = TYPE.getTypeFrom(splitLine[0]);
        destination = TYPE.getTypeFrom(splitLine[2]);
        transformList = new ArrayList<>();
        for (int i = 1; i < inputs.size(); i++) {
            var splitSpace = inputs.get(i).split(" ");
            transformList.add(new Transform(Long.parseLong(splitSpace[0]), Long.parseLong(splitSpace[1]), Long.parseLong(splitSpace[2])));
        }
    }

    public long morph(long input) {
        var chosenTransformer = transformList.stream()
                .filter(transform -> transform.sourceRange <= input && transform.sourceRange + transform.rangeLength > input)
                .findFirst()
                .orElse(DEFAULT_TRANSFORM);
        return input - (chosenTransformer.sourceRange - chosenTransformer.destinationRage);
    }

    public TYPE getSource() {
        return source;
    }

    public TYPE getDestination() {
        return destination;
    }

    public List<Transform> getTransformList() {
        return transformList;
    }

    public enum TYPE {
        SEED, SOIL, FERTILIZER, WATER, LIGHT, TEMPERATURE, HUMIDITY, LOCATION;

        public static TYPE getTypeFrom(String s) {
            return switch (s) {
                case "seed" -> SEED;
                case "soil" -> SOIL;
                case "fertilizer" -> FERTILIZER;
                case "water" -> WATER;
                case "light" -> LIGHT;
                case "temperature" -> TEMPERATURE;
                case "humidity" -> HUMIDITY;
                case "location" -> LOCATION;
                default -> throw new IllegalArgumentException("unknown type: " + s);
            };
        }
    }

    public record Transform(long destinationRage, long sourceRange, long rangeLength) {}
}