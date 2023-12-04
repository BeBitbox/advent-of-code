package be.bitbox.adventofcode.y2023.day4;

import java.util.Arrays;
import java.util.List;

public class Scratchcards {
    private final List<Card> cards;

    public Scratchcards(List<String> input) {
        cards = input.stream()
                .map(Card::new)
                .toList();
    }

    public int score() {
        return cards.stream()
                .map(Card::getPoints)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    public int totalScratchCards() {
        int[] occurrences = new int[cards.size()];
        Arrays.fill(occurrences, 1);

        for (int i = 0; i < cards.size(); i++) {
            var wonCards = cards.get(i).getWonCards();
            for (int wonCard : wonCards) {
                occurrences[wonCard - 1] = occurrences[wonCard - 1] + occurrences[i];
            }
        }

        return Arrays.stream(occurrences).sum();
    }
}