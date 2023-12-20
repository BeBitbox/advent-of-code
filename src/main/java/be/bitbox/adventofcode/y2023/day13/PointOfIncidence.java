package be.bitbox.adventofcode.y2023.day13;

import java.util.ArrayList;
import java.util.List;

public class PointOfIncidence {

    private final List<Reflection> reflectionList;

    public PointOfIncidence(List<String> inputList) {
        this.reflectionList = new ArrayList<>();

        var reflectionStringList = new ArrayList<String>();
        for (String s : inputList) {
            if (s.trim().isEmpty()) {
                reflectionList.add(new Reflection(reflectionStringList));
                reflectionStringList = new ArrayList<>();
            } else {
                reflectionStringList.add(s);
            }
        }

        if (!reflectionStringList.isEmpty()) {
            reflectionList.add(new Reflection(reflectionStringList));
        }
    }

    public int summarize() {
        return reflectionList.stream()
                .map(Reflection::summarize)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    public int summarize2() {
        return reflectionList.stream()
                .map(Reflection::smudge)
                .reduce(Integer::sum)
                .orElseThrow();
    }
}