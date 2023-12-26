package be.bitbox.adventofcode.y2023.day18;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LavaDuctLagoonTest {

    @Test
    void testExample() {
        var lavaDuctLagoon = new LavaDuctLagoon(List.of(
                "R 6 (#70c710)",
                "D 5 (#0dc571)",
                "L 2 (#5713f0)",
                "D 2 (#d2c081)",
                "R 2 (#59c680)",
                "D 2 (#411b91)",
                "L 5 (#8ceee2)",
                "U 2 (#caa173)",
                "L 1 (#1b58a2)",
                "U 2 (#caa171)",
                "R 2 (#7807d2)",
                "U 3 (#a77fa3)",
                "L 2 (#015232)",
                "U 2 (#7a21e3)"
        ));

        assertThat(lavaDuctLagoon.surface(true)).isEqualTo(62);
        assertThat(lavaDuctLagoon.surface(false)).isEqualTo(952408144115L);
    }
    @Test
    void testFile() {
        var lavaDuctLagoon = new LavaDuctLagoon(Util.readFileAsStringList("23_day18.txt"));

        assertThat(lavaDuctLagoon.surface(true)).isEqualTo(45159);
        assertThat(lavaDuctLagoon.surface(false)).isEqualTo(134549294799713L);
    }
}