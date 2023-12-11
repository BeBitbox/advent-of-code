package be.bitbox.adventofcode.y2023.day11;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CosmicExpansionTest {

    @Test
    void testParsing() {
        var cosmicExpansion = new CosmicExpansion(List.of(
                "...#......",
                ".......#..",
                "#.........",
                "..........",
                "......#...",
                ".#........",
                ".........#",
                "..........",
                ".......#..",
                "#...#....."
        ));

        assertThat(cosmicExpansion.olderGalaxies(2)).isEqualTo(374);
        assertThat(cosmicExpansion.olderGalaxies(100)).isEqualTo(8410);
    }

    @Test
    void testFile() {
        var cosmicExpansion = new CosmicExpansion(Util.readFileAsStringList("23_day11.txt"));

        assertThat(cosmicExpansion.olderGalaxies(2)).isEqualTo(9403026L);
        assertThat(cosmicExpansion.olderGalaxies(1_000_000)).isEqualTo(543018317006L);
    }
}