package be.bitbox.adventofcode.y2023.day7;

import java.util.HashMap;
import java.util.Map;

public abstract class Hand {
    final Map<Character, Integer> map;
    final String original;
    final int bid;

    public Hand(String input) {
        map = new HashMap<>();
        var splittedOnSpace = input.split(" ");
        original = splittedOnSpace[0];
        bid = Integer.parseInt(splittedOnSpace[1]);
    }

    public JokerHand.Type getType() {
        if (map.size() == 1) {
            return JokerHand.Type.FIVE_OF_A_KIND;
        }
        if (map.size() == 2) {
            var iterator = map.entrySet().iterator();
            var card1 = iterator.next();
            var card2 = iterator.next();

            if (card1.getValue() == 4 || card2.getValue() == 4) {
                return JokerHand.Type.FOUR_OF_A_KIND;
            } else {
                return JokerHand.Type.FULL_HOUSE;
            }
        }
        if (map.size() == 3) {
            var iterator = map.entrySet().iterator();
            var card1 = iterator.next();
            var card2 = iterator.next();
            var card3 = iterator.next();

            if (card1.getValue() == 3 || card2.getValue() == 3 || card3.getValue() == 3) {
                return JokerHand.Type.THREE_OF_A_KIND;
            } else {
                return JokerHand.Type.TWO_PAIR;
            }
        }
        if (map.size() == 4) {
            return JokerHand.Type.ONE_PAIR;
        }
        if (map.size() == 5) {
            return JokerHand.Type.HIGH_CARD;
        }
        throw new IllegalStateException("something wrong with the cards: " + map);
    }

    public String getOriginal() {
        return original;
    }

    public int getBid() {
        return bid;
    }

    public enum Type {
        FIVE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, THREE_OF_A_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD
    }
}
