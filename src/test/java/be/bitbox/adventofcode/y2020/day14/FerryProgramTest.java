package be.bitbox.adventofcode.y2020.day14;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FerryProgramTest {

    @Test
    void testSingleBitmask() {
        var ferryProgram = new FerryProgram(List.of("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"));

        assertThat(ferryProgram.collectiveSum()).isEqualTo(0);
    }

    @Test
    void testSingleValueBitmask() {
        var ferryProgram = new FerryProgram(List.of(
                "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
                "mem[8] = 0"));

        assertThat(ferryProgram.collectiveSum()).isEqualTo(64);
    }

    @Test
    void testDoubleValuesBitmask() {
        var ferryProgram = new FerryProgram(List.of(
                "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
                "mem[8] = 0",
                "mem[8] = 11"));

        assertThat(ferryProgram.collectiveSum()).isEqualTo(73);
    }

    @Test
    void testMultipleValuesBitmask() {
        var ferryProgram = new FerryProgram(List.of(
                "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
                "mem[8] = 11",
                "mem[7] = 101",
                "mem[8] = 0"));

        assertThat(ferryProgram.collectiveSum()).isEqualTo(165);
    }

    @Test
    void testMultipleValuesBitmasks() {
        var ferryProgram = new FerryProgram(List.of(
                "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
                "mem[8] = 11",    // after mask 73
                "mem[7] = 101",   // after mask 101
                "mem[8] = 0",     // after mask 64
                "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX000000X",
                "mem[7] = 101"    // after mask 1
                ));

        assertThat(ferryProgram.collectiveSum()).isEqualTo(65);
    }

    @Test
    void testFerryProgramFile() {
        var ferryProgram = new FerryProgram(Util.readFileAsStringList("day14.txt"));

        assertThat(ferryProgram.collectiveSum()).isEqualTo(13727901897109L);
        assertThat(ferryProgram.collectiveFloatingSum()).isEqualTo(5579916171823L);
    }

    @Test
    void testFloatingBitmasks() {
        var ferryProgram = new FerryProgram(List.of(
                "mask = 000000000000000000000000000000X1001X",
                "mem[42] = 100",
                "mask = 00000000000000000000000000000000X0XX",
                "mem[26] = 1"));

        assertThat(ferryProgram.collectiveFloatingSum()).isEqualTo(208);
    }

}