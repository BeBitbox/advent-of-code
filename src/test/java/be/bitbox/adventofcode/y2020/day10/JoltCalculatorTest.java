package be.bitbox.adventofcode.y2020.day10;

import be.bitbox.adventofcode.y2020.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JoltCalculatorTest {

    @Test
    void testSimpleJoltCalculator() {
        JoltCalculator joltCalculator = new JoltCalculator(List.of(1, 2, 4, 7));

        var result = joltCalculator.connectAllJoltAdapters();

        assertThat(result.getOneJoltDiffs()).isEqualTo(2);
        assertThat(result.getTwoJoltDiffs()).isEqualTo(1);
        assertThat(result.getThreeJoltDiffs()).isEqualTo(2);
        assertThat(result.outcome()).isEqualTo(2 * 2);
        assertThat(joltCalculator.possibleWays()).isEqualTo(3);
    }

    @Test
    void testExampleJoltCalculator() {
        JoltCalculator joltCalculator = new JoltCalculator(List.of(
                16,
                10,
                15,
                5,
                1,
                11,
                7,
                19,
                6,
                12,
                4));

        var result = joltCalculator.connectAllJoltAdapters();

        assertThat(result.getOneJoltDiffs()).isEqualTo(7);
        assertThat(result.getTwoJoltDiffs()).isEqualTo(0);
        assertThat(result.getThreeJoltDiffs()).isEqualTo(5);
        assertThat(result.outcome()).isEqualTo(35);
        assertThat(joltCalculator.possibleWays()).isEqualTo(8);
    }

    @Test
    void testBigJoltCalculator() {
        JoltCalculator joltCalculator = new JoltCalculator(List.of(
                28,
                33,
                18,
                42,
                31,
                14,
                46,
                20,
                48,
                47,
                24,
                23,
                49,
                45,
                19,
                38,
                39,
                11,
                1,
                32,
                25,
                35,
                8,
                17,
                7,
                9,
                4,
                2,
                34,
                10,
                3));

        var result = joltCalculator.connectAllJoltAdapters();

        assertThat(result.getOneJoltDiffs()).isEqualTo(22);
        assertThat(result.getTwoJoltDiffs()).isEqualTo(0);
        assertThat(result.getThreeJoltDiffs()).isEqualTo(10);
        assertThat(result.outcome()).isEqualTo(22 * 10);
        assertThat(joltCalculator.possibleWays()).isEqualTo(19208);
    }

    @Test
    void testFileJoltCalculator() {
        JoltCalculator joltCalculator = new JoltCalculator(Util.readFileAsIntList("day10.txt"));

        var result = joltCalculator.connectAllJoltAdapters();

        assertThat(result.outcome()).isEqualTo(1836);
        assertThat(joltCalculator.possibleWays()).isEqualTo(43406276662336L);
    }
}