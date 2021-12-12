package be.bitbox.adventofcode.y2021.day12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CavernPaths {

    private final Map<String, Set<String>> roads;
    private final Set<String> paths;

    public CavernPaths(List<String> inputs) {
        roads = new HashMap<>();
        paths = new HashSet<>();

        inputs.forEach(line -> {
            var split = line.split("-");
            var begin = split[0].trim();
            var end = split[1].trim();

            if (!roads.containsKey(begin)) {
                roads.put(begin, new HashSet<>());
            }
            roads.get(begin).add(end);
            if (!roads.containsKey(end)) {
                roads.put(end, new HashSet<>());
            }
            roads.get(end).add(begin);
        });
    }

    public Set<String> determinePaths(boolean oneDoubleAllowed) {
        paths.clear();
        roads.get("start").forEach(road -> visit("start,", road, oneDoubleAllowed));
        return paths;
    }

    private void visit(String currentPath, String newCave, boolean oneDoubleAllowed) {
        if ("start".equals(newCave)) {
            return;
        }
        if ("end".equals(newCave)) {
            paths.add(currentPath + newCave);
            return;
        }
        if (Character.isUpperCase(newCave.charAt(0))) {
            String newPath = currentPath + newCave + ",";
            roads.get(newCave).forEach(road -> visit(newPath, road, oneDoubleAllowed));
        } else {
            var isDouble = currentPath.contains("," + newCave + ",");
            if (!isDouble) {
                String newPath = currentPath + newCave + ",";
                roads.get(newCave).forEach(road -> visit(newPath, road, oneDoubleAllowed));
                return;
            }
            if (oneDoubleAllowed) {
                String newPath = currentPath + newCave + ",";
                roads.get(newCave).forEach(road -> visit(newPath, road, false));
            }
        }
    }
}
