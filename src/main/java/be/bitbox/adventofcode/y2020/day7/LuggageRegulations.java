package be.bitbox.adventofcode.y2020.day7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LuggageRegulations {
    private final Map<String, LuggageBag> luggageBags;

    public LuggageRegulations(List<String> rules) {
        luggageBags = new HashMap<>();
        rules.forEach(rule -> {
            var parts = rule.split("contain ");
            var color = parts[0].substring(0, parts[0].indexOf(" bags"));
            if (!parts[1].contains("no other bags")) {
                var luggageBag = luggageBags.getOrDefault(color, new LuggageBag(color));
                luggageBags.put(color, luggageBag);

                var subPartsRight = parts[1].split(" ");
                for (int i = 0; i < subPartsRight.length - 3; i += 4) {
                    var childNumber = Integer.parseInt(subPartsRight[i]);
                    var childColor = subPartsRight[i + 1] + " " + subPartsRight[i + 2];
                    var childLuggageBag = luggageBags.getOrDefault(childColor, new LuggageBag(childColor));
                    childLuggageBag.addParent(luggageBag);
                    luggageBag.addChild(childLuggageBag, childNumber);
                    luggageBags.put(childColor, childLuggageBag);
                }
            }
        });
    }

    public LuggageBag getLuggageBag(String color) {
        return luggageBags.get(color);
    }

    public int countTheParents(String color) {
        var luggageBag = luggageBags.get(color);
        if (luggageBag == null) {
            return 0;
        } else {
            return getParents(luggageBag).size();
        }
    }

    private Set<LuggageBag> getParents(LuggageBag luggageBag) {
        var parents = luggageBag.getParents();
        parents.addAll(parents.stream()
                .flatMap(bag -> getParents(bag).stream())
                .collect(Collectors.toSet()));
        return parents;
    }

    public int contentsBag(String color) {
        var luggageBag = luggageBags.getOrDefault(color, new LuggageBag(color));
        return count(luggageBag) - 1; //Don't include the bag parent bag
    }

    private int count(LuggageBag luggageBag) {
        int count = 1;  // include the bag
        for (Map.Entry<LuggageBag, Integer> entry : luggageBag.getChildren().entrySet()) {
            LuggageBag bag = entry.getKey();
            Integer integer = entry.getValue();
            count += integer * count(bag);
        }
        return count;

    }
}
