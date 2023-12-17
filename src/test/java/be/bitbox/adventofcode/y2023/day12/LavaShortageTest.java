package be.bitbox.adventofcode.y2023.day12;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LavaShortageTest {

    @Test
    void testLavaShortage() {
        var lavaShortage = new LavaShortage(List.of(
                "???.### 1,1,3",
                ".??..??...?##. 1,1,3",
                "?#?#?#?#?#?#?#? 1,3,1,6",
                "????.#...#... 4,1,1",
                "????.######..#####. 1,6,5",
                "?###???????? 3,2,1"
        ), false);

        assertThat(lavaShortage.possibilities()).isEqualTo(21);
    }

    @Test
    void testFilePart1() {
        var lavaShortage = new LavaShortage(Util.readFileAsStringList("23_day12.txt"), false);

        assertThat(lavaShortage.possibilities()).isEqualTo(7251);
    }

    @Test
    void testFilePart2() {
        var lavaShortage = new LavaShortage(Util.readFileAsStringList("23_day12.txt"), true);

        assertThat(lavaShortage.possibilities()).isEqualTo(2128386729962L);
    }
}