package be.bitbox.adventofcode.y2020.day7;

import java.util.*;

public class LuggageBag {
    private final String color;
    private final Set<LuggageBag> parents;
    private final Map<LuggageBag, Integer> children;

    public LuggageBag(String color) {
        this.color = color;
        parents = new HashSet<>();
        children = new HashMap<>();
    }

    public String getColor() {
        return color;
    }

    public void addParent(LuggageBag luggageBag) {
        parents.add(luggageBag);
    }

    public void addChild(LuggageBag luggageBag, Integer number) {
        children.put(luggageBag, number);
    }

    public Set<LuggageBag> getParents() {
        return parents;
    }

    public Map<LuggageBag, Integer> getChildren() {
        return children;
    }

    public Integer getChildNumber(LuggageBag luggageBag) {
        return children.get(luggageBag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LuggageBag that = (LuggageBag) o;
        return color.equals(that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return "LuggageBag{" +
                "color='" + color + '\'' +
                ", parents=" + parents +
                '}';
    }
}
