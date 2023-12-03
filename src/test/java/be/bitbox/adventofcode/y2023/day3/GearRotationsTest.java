package be.bitbox.adventofcode.y2023.day3;

import be.bitbox.adventofcode.Util;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GearRotationsTest {

    @Test
    void testSize() {
        var gearRotations = new GearRotations(List.of(
                "467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598.."));

        gearRotations.print();
        assertThat(gearRotations.calculate()).isEqualTo(4361);
        assertThat(gearRotations.getGearRatios()).isEqualTo(467835);
    }

    @Test
    void part1() {
        var gearRotations = new GearRotations(Util.readFileAsStringList("23_day3.txt"));

        assertThat(gearRotations.calculate()).isEqualTo(533775);
        assertThat(gearRotations.getGearRatios()).isEqualTo(78236071L);
    }
}