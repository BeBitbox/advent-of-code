package be.bitbox.adventofcode.y2023.day7;

public class NormalHand extends Hand implements Comparable<NormalHand> {

    public NormalHand(String input) {
        super(input);

        var charArray = original.toCharArray();
        for (char c : charArray) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
    }

    @Override
    public int compareTo(NormalHand other) {
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
        ACE, KING, QUEEN, JOKER, TRUMP, N9, N8, N7, N6, N5, N4, N3, N2;

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
