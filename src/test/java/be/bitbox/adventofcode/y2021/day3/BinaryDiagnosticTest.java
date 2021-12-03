package be.bitbox.adventofcode.y2021.day3;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryDiagnosticTest {

    @Test
    void part1() {
        var binaryDiagnostic = new BinaryDiagnostic(List.of(
                "00100",
                "11110",
                "10110",
                "10111",
                "10101",
                "01111",
                "00111",
                "11100",
                "10000",
                "11001",
                "00010",
                "01010"
        ));
        assertThat(binaryDiagnostic.calculatePart1()).isEqualTo(198);
    }

    @Test
    void part1_big() {
        var binaryDiagnostic = new BinaryDiagnostic(Util.readFileAsStringList("21_day3.txt"));
        assertThat(binaryDiagnostic.calculatePart1()).isEqualTo(4118544);
    }

    @Test
    void part2() {
        var binaryDiagnostic = new BinaryDiagnostic(List.of(
                "00100",
                "11110",
                "10110",
                "10111",
                "10101",
                "01111",
                "00111",
                "11100",
                "10000",
                "11001",
                "00010",
                "01010"
        ));
        assertThat(binaryDiagnostic.calculatePart2()).isEqualTo(230);
    }

    @Test
    void part2_big() {
        var binaryDiagnostic = new BinaryDiagnostic(Util.readFileAsStringList("21_day3.txt"));
        assertThat(binaryDiagnostic.calculatePart2()).isEqualTo(3832770);
    }
}