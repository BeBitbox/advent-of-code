package be.bitbox.adventofcode.y2021.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LanternSchool {

    private final List<LanternFish> lanternFishList;
    private final long[] initialFishDistribution;


    public LanternSchool(String input) {
        this.lanternFishList = Stream.of(input.split(","))
                .map(i -> new LanternFish(Integer.parseInt(i)))
                .collect(toList());
        this.initialFishDistribution = new long[9];
        for (LanternFish lanternFish : lanternFishList) {
            initialFishDistribution[lanternFish.getTimer()]++;
        }
    }

    public Long calculate(int numberOfDays) {
        long[] fishTimers = Arrays.copyOf(initialFishDistribution, initialFishDistribution.length);

        for (int day = 0; day < numberOfDays; day++) {
            long[] nextDay = new long[9];
            System.arraycopy(fishTimers, 1, nextDay, 0, 8);
            nextDay[6] += fishTimers[0];
            nextDay[8] = fishTimers[0];

            fishTimers = nextDay;
        }

        return Arrays.stream(fishTimers)
                .reduce((Long::sum))
                .orElse(0L);
    }

    public Integer calculateBruteForce(int number) {
        for (int i = 0; i < number; i++) {
            List<LanternFish> newFish = new ArrayList<>();
            for (LanternFish lanternFish : lanternFishList) {
                lanternFish.newDay().ifPresent(newFish::add);
            }
            lanternFishList.addAll(newFish);
        }
        return lanternFishList.size();
    }
}
