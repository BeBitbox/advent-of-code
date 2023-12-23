package be.bitbox.adventofcode.y2023.day17;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CrucibleTest {

    @Test
    void example() {
        var crucible = new Crucible(List.of(
                "2413432311323",
                "3215453535623",
                "3255245654254",
                "3446585845452",
                "4546657867536",
                "1438598798454",
                "4457876987766",
                "3637877979653",
                "4654967986887",
                "4564679986453",
                "1224686865563",
                "2546548887735",
                "4322674655533"
        ));

        assertThat(crucible.path()).isEqualTo(102);
    }

    @Test
    void example2() {
        var crucible = new Crucible(List.of(
                "111111111111",
                "999999999991",
                "999999999991",
                "999999999991",
                "999999999991"
        ));

        assertThat(crucible.path()).isEqualTo(65);
    }

    @Test
    void testFile() {
        var crucible = new Crucible(Util.readFileAsStringList("23_day17.txt"));

        assertThat(crucible.path()).isEqualTo(1260);
    }
}