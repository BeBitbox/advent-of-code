package be.bitbox.adventofcode.y2021.day10;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SyntaxCheckerTest {

    @Test
    void calculateInvalidLines() {
        var syntaxChecker = new SyntaxChecker(List.of(
            "[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]"
        ));

        assertThat(syntaxChecker.calculateInvalidLines()).isEqualTo(26397);
        assertThat(syntaxChecker.calculateIncompleteScore()).isEqualTo(288957L);
    }

    @Test
    void calculateInvalidLines_Big() {
        var syntaxChecker = new SyntaxChecker(Util.readFileAsStringList("21_day10.txt"));

        assertThat(syntaxChecker.calculateInvalidLines()).isEqualTo(392421);
        assertThat(syntaxChecker.calculateIncompleteScore()).isEqualTo(2769449099L);
    }
}