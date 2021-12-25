package be.bitbox.adventofcode.y2021.day25;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SeaCucumberFieldTest {

    @Test
    void moveUntillAllQuiet() {
        var seaCucumberField = new SeaCucumberField(List.of(
                "v...>>.vv>",
                ".vv>>.vv..",
                ">>.>v>...v",
                ">>v>>.>.v.",
                "v>v.vv.v..",
                ">.>>..v...",
                ".vv..>.>v.",
                "v.v..>>v.v",
                "....v..v.>"
        ));

        assertThat(seaCucumberField.moveUntillAllQuiet()).isEqualTo(58);
    }

    @Test
    void moveUntillAllQuiet_big() {
        var seaCucumberField = new SeaCucumberField(Util.readFileAsStringList("21_day25.txt"));

        assertThat(seaCucumberField.moveUntillAllQuiet()).isEqualTo(419);
    }
}