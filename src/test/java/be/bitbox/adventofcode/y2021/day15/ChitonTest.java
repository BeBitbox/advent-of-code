package be.bitbox.adventofcode.y2021.day15;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChitonTest {

    @Test
    void lowestRiskSmall() {
        var chiton = new Chiton(List.of(
                "1163751742",
                "1381373672",
                "2136511328",
                "3694931569",
                "7463417111",
                "1319128137",
                "1359912421",
                "3125421639",
                "1293138521",
                "2311944581"
        ));

        assertThat(chiton.lowestRiskPart1()).isEqualTo(40);
        assertThat(chiton.lowestRiskPart2()).isEqualTo(315);
    }

    @Test
    void lowestRiskBig() {
        var chiton = new Chiton(Util.readFileAsStringList("21_day15.txt"));

        assertThat(chiton.lowestRiskPart1()).isEqualTo(673);
        assertThat(chiton.lowestRiskPart2()).isEqualTo(2893);
    }
}