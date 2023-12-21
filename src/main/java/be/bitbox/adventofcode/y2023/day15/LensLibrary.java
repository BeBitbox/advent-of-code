package be.bitbox.adventofcode.y2023.day15;

import java.util.*;

public class LensLibrary {

    private final List<Hash> hashList;

    public LensLibrary(List<String> inputList) {
        this.hashList = new ArrayList<>();

        for (String string : inputList) {
            for (String s : string.split(",")) {
                hashList.add(new Hash(s));
            }
        }
    }

    public int resultPart1() {
        return hashList.stream()
                .map(Hash::calculate)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    public int resultPart2() {
        Map<Integer, LinkedList<Hash>> map = new HashMap<>();

        for (Hash hash : hashList) {
            var lensList = map.get(hash.getBoxHash());
            if (hash.getFocalLength() == 0) {
                if (lensList != null) {
                    lensList.remove(hash);
                }
            } else {
                if (lensList == null) {
                    var linkedList = new LinkedList<Hash>();
                    linkedList.add(hash);
                    map.put(hash.getBoxHash(), linkedList);
                } else {
                    if (lensList.contains(hash)) {
                        var index = lensList.indexOf(hash);
                        lensList.set(index, hash);
                    } else {
                        lensList.add(hash);
                    }
                }
            }
        }

        int sum = 0;
        for (Map.Entry<Integer, LinkedList<Hash>> entry : map.entrySet()) {
            var boxMultiplier = entry.getKey() + 1;

            var linkedList = entry.getValue();
            for (int i = 0; i < linkedList.size(); i++) {
                sum += (boxMultiplier * (i + 1) * linkedList.get(i).getFocalLength());
            }
        }
        return sum;
    }
}
