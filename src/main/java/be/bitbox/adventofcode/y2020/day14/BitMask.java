package be.bitbox.adventofcode.y2020.day14;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class BitMask {
    private final String mask;

    public BitMask(String mask) {
        this.mask = mask;
    }

    public long afterMaskApplication(long number) {
        String numberBinary = getBinaryStringWithLeadingZeros(number);

        char[] newChars = new char[mask.length()];
        for (int i = 0; i < mask.length(); i++) {
            newChars[i] = mask.charAt(i) == 'X' ? numberBinary.charAt(i) : mask.charAt(i);
        }

        return Long.parseLong(new String(newChars), 2);
    }

    public List<Long> afterFloatingMaskApplication(long number) {
        String numberBinary = getBinaryStringWithLeadingZeros(number);

        char[] newChars = new char[mask.length()];
        Set<Integer> exes = new HashSet<>();
        for (int i = 0; i < mask.length(); i++) {
            newChars[i] = mask.charAt(i) == '0' ? numberBinary.charAt(i) : mask.charAt(i);
            if (mask.charAt(i) == 'X') {
                exes.add(i);
            }
        }

        if (exes.size() > 10) {
            System.err.println("Could not proces that amount of X's");
            return new ArrayList<>();
        }
        var current = new String(newChars);
        Set<String> numbers = new TreeSet<>();
        numbers.add(current);
        for (Integer x : exes) {
            Set<String> temp = new TreeSet<>();
            for (String numberWithX : numbers) {
                if (x < mask.length() - 1) {
                    temp.add(numberWithX.substring(0, x) + '0' + numberWithX.substring(x + 1));
                    temp.add(numberWithX.substring(0, x) + '1' + numberWithX.substring(x + 1));
                } else {
                    temp.add(numberWithX.substring(0, x) + '0');
                    temp.add(numberWithX.substring(0, x) + '1');
                }
            }
            numbers = temp;
        }

        return numbers.stream()
                .map(s -> Long.parseLong(s, 2))
                .collect(toList());
    }

    private String getBinaryStringWithLeadingZeros(long number) {
        var numberBinary = Long.toBinaryString(number);

        while (numberBinary.length() < mask.length()) {
            numberBinary = "0" + numberBinary;
        }
        return numberBinary;
    }
}
