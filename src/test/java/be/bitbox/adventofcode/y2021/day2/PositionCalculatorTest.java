package be.bitbox.adventofcode.y2021.day2;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PositionCalculatorTest {

    @Test
    void testPart1() {
        var positionCalculator = new PositionCalculator(List.of(
                "forward 5",
                "down 5   ",
                "forward 8",
                "up 3     ",
                "down 8   ",
                "forward 2"));

        assertThat(positionCalculator.calculatePosition()).isEqualTo(150);
        assertThat(positionCalculator.calculatePosition2()).isEqualTo(900);
    }

    @Test
    void testBigPart1() {
        var positionCalculator = new PositionCalculator(Util.readFileAsStringList("21_day2.txt"));

        assertThat(positionCalculator.calculatePosition()).isEqualTo(2187380);
        assertThat(positionCalculator.calculatePosition2()).isEqualTo(2086357770);
    }
}