package be.bitbox.adventofcode.y2021.day9;

import be.bitbox.adventofcode.Tuple;
import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LavaTubeTest {

    @Test
    void getMinPoints() {
        var lavaTube = new LavaTube(List.of(
                "2199943210",
                "3987894921",
                "9856789892",
                "8767896789",
                "9899965678"
        ));

        assertThat(lavaTube.getMinPoints()).containsExactlyInAnyOrder(
                new Tuple<>(0, 1),
                new Tuple<>(0, 9),
                new Tuple<>(2, 2),
                new Tuple<>(4,6));
        assertThat(lavaTube.scorePart1()).isEqualTo(15);
        assertThat(lavaTube.scorePart2()).isEqualTo(1134);
    }

    @Test
    void getMinPoints_big() {
        var lavaTube = new LavaTube(Util.readFileAsStringList("21_day9.txt"));

        assertThat(lavaTube.scorePart1()).isEqualTo(537);
        assertThat(lavaTube.scorePart2()).isEqualTo(1142757);
    }
}