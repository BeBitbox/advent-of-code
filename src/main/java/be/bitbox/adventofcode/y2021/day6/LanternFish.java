package be.bitbox.adventofcode.y2021.day6;

import java.util.Optional;

public class LanternFish {
    private int timer;

    public LanternFish(int startCounter) {
        timer = startCounter;
    }

    public Optional<LanternFish> newDay() {
        timer--;
        if (timer < 0) {
            timer = 6;
            return Optional.of(new LanternFish(8));
        } else {
            return Optional.empty();
        }
    }

    public int getTimer() {
        return timer;
    }
}
