package be.bitbox.adventofcode.y2021.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolymerTemplate {
    private final String polymer;
    private final Map<String, Character> pairs;

    public PolymerTemplate(List<String> input) {
        var iterator = input.iterator();
        var line = iterator.next().trim();
        polymer = line;

        line = iterator.next().trim();
        assert line.trim().length() == 0;

        pairs = new HashMap<>();
        while (iterator.hasNext()) {
            line = iterator.next().trim();
            var split = line.split(" ");
            pairs.put(split[0], split[2].charAt(0));
        }
    }

    public Long getScore(int totalSteps) {
        Map<String, Map<Character, Long>> cache = new HashMap<>();
        Map<Character, Long> scores = new HashMap<>();
        for (int i = 0; i < polymer.length() - 1; i++) {
            var scores2 = calculate("" + polymer.charAt(i) + polymer.charAt(i + 1), totalSteps, cache);
            scores2.forEach((character, value) -> scores.put(character, scores.getOrDefault(character, 0L) + value));
        }
        polymer.chars().forEach(i -> scores.put((char) i, scores.getOrDefault((char) i, 0L) + 1));
        var max = scores.values().stream().max(Long::compareTo).orElseThrow();
        var min = scores.values().stream().min(Long::compareTo).orElseThrow();
        return max - min;
    }

    private Map<Character, Long> calculate(String polymer, int total, Map<String, Map<Character, Long>> cache) {
        if (total <= 0) {
            return new HashMap<>();
        }

        var key = polymer + total;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        var newChar = pairs.get(polymer);
        var scores1 = calculate("" + polymer.charAt(0) + newChar, total - 1, cache);
        var scores2 = calculate("" + newChar + polymer.charAt(1), total - 1, cache);
        Map<Character, Long> scores = new HashMap<>(scores1);
        scores2.forEach((character, value) -> scores.put(character, scores.getOrDefault(character, 0L) + value));
        scores.put(newChar, scores.getOrDefault(newChar, 0L) + 1);
        cache.put(key, scores);
        return scores;
    }

    public String getPolymer() {
        return polymer;
    }

    public Map<String, Character> getPairs() {
        return pairs;
    }
}
