package be.bitbox.adventofcode.y2021.day5;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SeaFloorTest {

    @Test
    void smpall() {
        var seaFloor = new SeaFloor(List.of(
                "0,9 -> 5,9",
                "8,0 -> 0,8",
                "9,4 -> 3,4",
                "2,2 -> 2,1",
                "7,0 -> 7,4",
                "6,4 -> 2,0",
                "0,9 -> 2,9",
                "3,4 -> 1,4",
                "0,0 -> 8,8",
                "5,5 -> 8,2"
        ));

        assertThat(seaFloor.numberOfOverlaps(false)).isEqualTo(5L);
        assertThat(seaFloor.numberOfOverlaps(true)).isEqualTo(12L);
    }

    @Test
    void big() {
        var seaFloor = new SeaFloor(Util.readFileAsStringList("21_day5.txt"));

        assertThat(seaFloor.numberOfOverlaps(false)).isEqualTo(6666L);
        assertThat(seaFloor.numberOfOverlaps(true)).isEqualTo(19081L);
    }
}