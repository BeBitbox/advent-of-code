package be.bitbox.adventofcode.y2021.day23;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AmphipodGame {

    private final Amphipod start;

    public AmphipodGame(List<String> input) {
        char[] rooms;
        if (input.size() < 6) {
            rooms = new char[] {
                    input.get(2).charAt(3),
                    input.get(3).charAt(3),
                    input.get(2).charAt(5),
                    input.get(3).charAt(5),
                    input.get(2).charAt(7),
                    input.get(3).charAt(7),
                    input.get(2).charAt(9),
                    input.get(3).charAt(9)
            };
        } else {
            rooms = new char[] {
                    input.get(2).charAt(3),
                    input.get(3).charAt(3),
                    input.get(4).charAt(3),
                    input.get(5).charAt(3),
                    input.get(2).charAt(5),
                    input.get(3).charAt(5),
                    input.get(4).charAt(5),
                    input.get(5).charAt(5),
                    input.get(2).charAt(7),
                    input.get(3).charAt(7),
                    input.get(4).charAt(7),
                    input.get(5).charAt(7),
                    input.get(2).charAt(9),
                    input.get(3).charAt(9),
                    input.get(4).charAt(9),
                    input.get(5).charAt(9)
            };
        }
        start = new Amphipod(rooms);
    }

    public int determineAllSolutions() {
        Map<Amphipod, Integer> visited = new HashMap<>();
        var queue = new PriorityQueue<>(Comparator.comparingLong(Amphipod::getEnergy));
        queue.add(start);
        int minimum = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            var pod = queue.poll();

            if (pod.isComplete()) {
                System.out.println(pod);
                minimum = Math.min(minimum, pod.getEnergy());
            }
            visited.put(pod, pod.getEnergy());
            pod.determineNextPossibleMoves().stream()
                    .filter(amphipod -> !visited.containsKey(amphipod) || visited.get(amphipod) > amphipod.getEnergy())
                    .forEach(amphipod -> {
                        visited.put(amphipod, amphipod.getEnergy());
                        queue.add(amphipod);
                    });
        }
        return minimum;
    }
}
