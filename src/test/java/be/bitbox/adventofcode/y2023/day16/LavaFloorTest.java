package be.bitbox.adventofcode.y2023.day16;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LavaFloorTest {

    @Test
    void testExample() {
        var lavaFloor = new LavaFloor(List.of(
                ".|...\\....",
                "|.-.\\.....",
                ".....|-...",
                "........|.",
                "..........",
                ".........\\",
                "..../.\\\\..",
                ".-.-/..|..",
                ".|....-|.\\",
                "..//.|...."
        ));

        assertThat(lavaFloor.numberEnergized()).isEqualTo(46);
        assertThat(lavaFloor.maxNumberEnergized()).isEqualTo(51);
    }

    @Test
    void testFile() {
        var lavaFloor = new LavaFloor(Util.readFileAsStringList("23_day16.txt"));

        assertThat(lavaFloor.numberEnergized()).isEqualTo(8539);
        assertThat(lavaFloor.maxNumberEnergized()).isEqualTo(8674);
    }
}