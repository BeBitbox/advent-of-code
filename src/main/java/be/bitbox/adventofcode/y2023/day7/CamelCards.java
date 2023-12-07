package be.bitbox.adventofcode.y2023.day7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CamelCards {

    private final List<JokerHand> jokerHands;
    private final List<NormalHand> normalHands;

    public CamelCards(List<String> inputList) {
        jokerHands = inputList.stream()
                .map(JokerHand::new)
                .toList();

        normalHands = inputList.stream()
                .map(NormalHand::new)
                .toList();
    }

    public int totalWinningsNormalHands() {
        var sortedList = new ArrayList<>(normalHands);
        sortedList.sort(Comparator.reverseOrder());

        int score = 0;
        for (int i = 0; i < sortedList.size(); i++) {
            score += ((i + 1) * sortedList.get(i).getBid());
        }
        return score;
    }

    public int totalWinningsJokerHands() {
        var sortedList = new ArrayList<>(jokerHands);
        sortedList.sort(Comparator.reverseOrder());

        int score = 0;
        for (int i = 0; i < sortedList.size(); i++) {
            score += ((i + 1) * sortedList.get(i).getBid());
        }
        return score;
    }
}
