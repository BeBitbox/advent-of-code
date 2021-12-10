package be.bitbox.adventofcode.y2021.day10;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

public class SyntaxChecker {
    private final List<String> lines;
    private static final Map<Character, Integer> CORRUPT_CONVERSION_MAP = Map.of(
            ')', 3,
            ']', 57,
            '}', 1197,
            '>', 25137
    );
    private static final Map<Character, Integer> INCOMPLETE_CONVERSION_MAP = Map.of(
            '(', 1,
            '[', 2,
            '{', 3,
            '<', 4
    );

    public SyntaxChecker(List<String> lines) {
        this.lines = lines;
    }

    public int calculateInvalidLines() {
        return lines.stream()
                .map(this::invalidCharacterFound)
                .filter(Objects::nonNull)
                .map(CORRUPT_CONVERSION_MAP::get)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    private Character invalidCharacterFound(String line) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < line.length(); i++) {
            var currentChar = line.charAt(i);

            if (CORRUPT_CONVERSION_MAP.containsKey(currentChar)) {
                var lastOnStack = stack.pop();
                if ((currentChar == ')' && lastOnStack != '(')
                        || (currentChar == '>' && lastOnStack != '<')
                        || (currentChar == ']' && lastOnStack != '[')
                        || (currentChar == '}' && lastOnStack != '{')) {
                    return currentChar;
                }
            } else {
                stack.push(currentChar);
            }

        }
        return null;
    }

    public Long calculateIncompleteScore() {
        var sortedScores = lines.stream()
                .map(this::inCompleteScore)
                .filter(aLong -> aLong > 0)
                .sorted()
                .collect(Collectors.toList());

        return sortedScores.get((sortedScores.size() - 1) / 2);
    }

    private long inCompleteScore(String line) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < line.length(); i++) {
            var currentChar = line.charAt(i);

            if (CORRUPT_CONVERSION_MAP.containsKey(currentChar)) {
                var lastOnStack = stack.pop();
                if ((currentChar == ')' && lastOnStack != '(')
                        || (currentChar == '>' && lastOnStack != '<')
                        || (currentChar == ']' && lastOnStack != '[')
                        || (currentChar == '}' && lastOnStack != '{')) {
                    //CORRUPT -> return
                    return 0L;
                }
            } else {
                stack.push(currentChar);
            }
        }

        long score = 0;
        while (stack.size() > 0) {
            var character = stack.pop();
            var points = INCOMPLETE_CONVERSION_MAP.get(character);
            score = score * 5 + points;
        }
        return score;
    }
}
