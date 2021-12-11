package be.bitbox.adventofcode.y2021.day11;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OctopusFieldTest {

    @Test
    void testTicks() {
        var octopusField = new OctopusField(List.of(
                "5483143223",
                "2745854711",
                "5264556173",
                "6141336146",
                "6357385478",
                "4167524645",
                "2176841721",
                "6882881134",
                "4846848554",
                "5283751526"
        ));
        assertThat(octopusField.tick(100)).isEqualTo(1656);
        assertThat(octopusField.tickUntillSynced()).isEqualTo(195);
    }

    @Test
    void testTicks_Big() {
        var octopusField = new OctopusField(Util.readFileAsStringList("21_day11.txt"));

        assertThat(octopusField.tick(100)).isEqualTo(1634);
        assertThat(octopusField.tickUntillSynced()).isEqualTo(210);
    }
}