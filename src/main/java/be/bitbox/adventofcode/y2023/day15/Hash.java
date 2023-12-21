package be.bitbox.adventofcode.y2023.day15;

import java.util.Objects;

public class Hash {

    private final String input;
    private final String boxCode;
    private final int boxHash;
    private final int focalLength;

    public Hash(String input) {
        this.input = input;

        if (input.contains("=")) {
            var split = input.split("=");
            boxCode = split[0];
            boxHash = calculate(boxCode);
            focalLength = Integer.parseInt(split[1]);
        } else {
            boxCode = input.split("-")[0];
            boxHash = calculate(boxCode);
            focalLength = 0;
        }
    }

    public int calculate() {
        return calculate(input);
    }

    public static int calculate(String value) {
        int currentValue = 0;
        for (int i = 0; i < value.length(); i++) {
            currentValue += value.charAt(i);
            currentValue *= 17;
            currentValue %= 256;
        }
        return currentValue;
    }

    public int getBoxHash() {
        return boxHash;
    }

    public int getFocalLength() {
        return focalLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hash hash = (Hash) o;
        return Objects.equals(boxCode, hash.boxCode);
    }

    @Override
    public int hashCode() {
        return boxHash;
    }
}
