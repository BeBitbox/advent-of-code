package be.bitbox.adventofcode.y2023.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Card {

    private final int id;
    private final List<Integer> winningNumbers;
    private final List<Integer> ownNumbers;

    public Card(String input) {
        input = input.replaceAll(" +", " ");
        var splittedOnSpace = input.split(" ");
        id = Integer.parseInt(splittedOnSpace[1].split(":")[0]);
        winningNumbers = new ArrayList<>();
        ownNumbers = new ArrayList<>();
        boolean firstPart = true;

        for (int i = 2; i < splittedOnSpace.length; i++) {
            if ("|".equals(splittedOnSpace[i])) {
                firstPart = false;
            } else {
                if (firstPart) {
                    winningNumbers.add(Integer.parseInt(splittedOnSpace[i]));
                } else {
                    ownNumbers.add(Integer.parseInt(splittedOnSpace[i]));
                }
            }
        }
    }

    public int getPoints() {
        var count = getCount();
        if (count == 0) {
            return 0;
        } else {
            return (int) Math.pow(2, count - 1);
        }
    }

    private long getCount() {
        return ownNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public int[] getWonCards() {
        var count = getCount();
        return IntStream.range(1, (int) count + 1)
                .map(i -> i + id)
                .toArray();
    }

    public int getId() {
        return id;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public List<Integer> getOwnNumbers() {
        return ownNumbers;
    }
}