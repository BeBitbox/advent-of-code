package be.bitbox.adventofcode.y2023.day7;

import java.util.Map;

public class JokerHand extends Hand implements Comparable<JokerHand> {

    public JokerHand(String input) {
        super(input);

        var charArray = original.toCharArray();
        int numberOfJokers = 0;
        for (char c : charArray) {
            if (c == 'J') {
                numberOfJokers++;
            } else if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        if (numberOfJokers == 5) {
            map.put('A', 5);
        } else if (numberOfJokers == 4) {
            map.entrySet().iterator().next().setValue(5);
        } else if (numberOfJokers == 3 && map.size() == 2) {
            map.entrySet().iterator().next().setValue(4);
        } else if (numberOfJokers == 3 && map.size() == 1) {
            map.entrySet().iterator().next().setValue(5);
        } else if (numberOfJokers == 2 && map.size() == 1) {
            map.entrySet().iterator().next().setValue(5);
        } else if (numberOfJokers == 2 && map.size() == 2) {
            var iterator = map.entrySet().iterator();
            var entry1 = iterator.next();
            var entry2 = iterator.next();
            if (entry1.getValue() == 2) {
                entry1.setValue(4);
            } else if (entry2.getValue() == 2) {
                entry2.setValue(4);
            } else {
                throw new IllegalStateException("PANIC");
            }
        } else if (numberOfJokers == 2 && map.size() == 3) {
            map.entrySet().iterator().next().setValue(3);
        } else if (numberOfJokers == 1) {
            Map.Entry<Character, Integer> max = map.entrySet().stream().findFirst().orElseThrow();

            for (Map.Entry<Character, Integer> next : map.entrySet()) {
                if (next.getValue() > max.getValue()) {
                    max = next;
                }
            }
            max.setValue(max.getValue() + 1);
        }
    }

    @Override
    public int compareTo(JokerHand other) {
        var type = getType();
        var otherType = other.getType();
        if (type != otherType) {
            return type.compareTo(otherType);
        }

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) != other.getOriginal().charAt(i)) {
                return CardNumber.of(original.charAt(i)).compareTo(CardNumber.of(other.getOriginal().charAt(i)));
            }
        }

        throw new IllegalArgumentException("unexpected");
    }

    public enum CardNumber implements Comparable<CardNumber> {
        ACE, KING, QUEEN, TRUMP, N9, N8, N7, N6, N5, N4, N3, N2, JOKER;

        public static CardNumber of(char c) {
            return switch (c) {
                case 'A' -> ACE;
                case 'K' -> KING;
                case 'Q' -> QUEEN;
                case 'J' -> JOKER;
                case 'T' -> TRUMP;
                case '9' -> N9;
                case '8' -> N8;
                case '7' -> N7;
                case '6' -> N6;
                case '5' -> N5;
                case '4' -> N4;
                case '3' -> N3;
                case '2' -> N2;
                default -> throw new IllegalArgumentException("Not card " + c);
            };
        }
    }
}
