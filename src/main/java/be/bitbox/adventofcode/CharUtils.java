package be.bitbox.adventofcode;

public abstract class CharUtils {

    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isDigit(int c) {
        return c >= '0' && c <= '9';
    }

    public static int toInt(char c) {
        if (isDigit(c)) {
            return c - '0';
        } else {
            throw new IllegalArgumentException("Not a digit: " + c);
        }
    }
    public static int toInt(int c) {
        if (isDigit(c)) {
            return c - '0';
        } else {
            throw new IllegalArgumentException("Not a digit: " + c);
        }
    }
}
