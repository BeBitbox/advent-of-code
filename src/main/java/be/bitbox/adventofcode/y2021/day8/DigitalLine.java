package be.bitbox.adventofcode.y2021.day8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DigitalLine {
    public static final List<Integer> DETERMINED_SIZES = List.of(2, 3, 4, 7);
    private final List<String> input;
    private final List<String> output;

    private char up;
    private char up_left;
    private char up_right;
    private char middle;
    private char down_left;
    private char down_right;
    private char down;

    public DigitalLine(String line) {
        var split = line.split("\\|");
        input = Arrays.stream(split[0].trim().split(" "))
                .collect(Collectors.toList());
        output = Arrays.stream(split[1].trim().split(" "))
                .collect(Collectors.toList());
        decipherCode();
    }

    public int numberOf_1_4_7_8() {
        return (int) output.stream()
                .filter(number -> DETERMINED_SIZES.contains(number.length()))
                .count();
    }

    public int output() {
        return Integer.parseInt(output.stream()
                .map(this::output)
                .collect(Collectors.joining()));
    }

    private String output(String code) {
        if (equalChars(code, List.of(up, up_right, down_right, down, down_left, up_left))) {
            return "0";
        } else if (equalChars(code, List.of(up_right, down_right))) {
            return "1";
        } else if (equalChars(code, List.of(up, up_right, down, down_left, middle))) {
            return "2";
        } else if (equalChars(code, List.of(up, up_right, down_right, down, middle))) {
            return "3";
        } else if (equalChars(code, List.of(up_right, up_left, middle, down_right))) {
            return "4";
        } else if (equalChars(code, List.of(up, up_left, middle, down_right, down))) {
            return "5";
        } else if (equalChars(code, List.of(up, middle, down_right, down, down_left, up_left))) {
            return "6";
        } else if (equalChars(code, List.of(up, up_right, down_right))) {
            return "7";
        } else if (equalChars(code, List.of(up, up_right, middle, down_right, down, down_left, up_left))) {
            return "8";
        } else if (equalChars(code, List.of(up, up_right, down_right, down, middle, up_left))) {
            return "9";
        }
        throw new RuntimeException("not found " + code);
    }

    private void decipherCode() {
        var number7 = getInputOfLength(3);
        var number1 = getInputOfLength(2);
        var number4 = getInputOfLength(4);
        var number8 = getInputOfLength(7);
        var length6 = getInputsOfLength(6);
        var number9 = length6.stream()
                .filter(number -> equalCharsInside(number, number4) == 4)
                .findAny()
                .orElseThrow();
        var number6 = length6.stream()
                .filter(number -> equalCharsInside(number, number1) == 1)
                .findAny()
                .orElseThrow();
        var number0 = length6.stream()
                .filter(number -> !number.equals(number6) && !number.equals(number9))
                .findAny()
                .orElseThrow();


        up = filterOutMissingChar(number7, number1);
        down_left = filterOutMissingChar(number8, number9);
        up_right = filterOutMissingChar(number8, number6);
        middle = filterOutMissingChar(number8, number0);
        down_right = filterOutMissingChar(number7, List.of(up, up_right));
        var number3 = getInputsOfLength(5).stream()
                .filter(number -> number.contains(String.valueOf(up_right)) && number.contains(String.valueOf(down_right)))
                .findAny()
                .orElseThrow();
        down = filterOutMissingChar(number3, List.of(up, up_right, middle, down_right));
        up_left = filterOutMissingChar(number4, List.of(up_right, middle, down_right));
    }

    private int equalCharsInside(String source, String minus) {
        return (int) source.chars()
                .filter(n -> minus.indexOf((char) n) > -1)
                .count();
    }

    private boolean equalChars(String source, List<Character> chars) {
        return source.length() == chars.size()
                && source.chars().allMatch(n -> chars.contains((char) n));
    }

    private String getInputOfLength(int desiredLength) {
        return input.stream()
                .filter(s -> s.length() == desiredLength)
                .findAny()
                .orElseThrow();
    }

    private List<String> getInputsOfLength(int desiredLength) {
        return input.stream()
                .filter(s -> s.length() == desiredLength)
                .collect(Collectors.toList());
    }

    private char filterOutMissingChar(String source, String minus) {
        return (char) source.chars()
                .filter(n -> minus.indexOf((char) n) == -1)
                .findAny()
                .orElseThrow();
    }

    private char filterOutMissingChar(String source, List<Character> minus) {
        return (char) source.chars()
                .filter(o -> !minus.contains((char) o))
                .findAny()
                .orElseThrow();
    }
}
