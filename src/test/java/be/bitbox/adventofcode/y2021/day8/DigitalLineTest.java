package be.bitbox.adventofcode.y2021.day8;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DigitalLineTest {

    @Test
    void output() {
        var digitalLine = new DigitalLine("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf");

        assertThat(digitalLine.output()).isEqualTo(5353);
    }

    @Test
    void output2() {
        var digitalLine = new DigitalLine("be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe");

        assertThat(digitalLine.output()).isEqualTo(8394);
    }
}