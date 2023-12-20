package be.bitbox.adventofcode.y2023.day13;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PointOfIncidenceTest {

    @Test
    void testExample() {
        var pointOfIncidence = new PointOfIncidence(List.of(
                "#.##..##.",
                "..#.##.#.",
                "##......#",
                "##......#",
                "..#.##.#.",
                "..##..##.",
                "#.#.##.#.",
                "",
                "#...##..#",
                "#....#..#",
                "..##..###",
                "#####.##.",
                "#####.##.",
                "..##..###",
                "#....#..#"
        ));

        assertThat(pointOfIncidence.summarize()).isEqualTo(405);
        assertThat(pointOfIncidence.summarize2()).isEqualTo(400);
    }

    @Test
    void testFile() {
        var pointOfIncidence = new PointOfIncidence(Util.readFileAsStringList("23_day13.txt"));

        assertThat(pointOfIncidence.summarize()).isEqualTo(30535);
        assertThat(pointOfIncidence.summarize2()).isEqualTo(30844);
    }
}