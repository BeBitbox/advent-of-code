package be.bitbox.adventofcode.y2021.day23;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AmphipodGameTest {

    @Test
    void example_part1() {
        var amphipodGame = new AmphipodGame(List.of(
                "#############",
                "#...........#",
                "###B#C#B#D###",
                "  #A#D#C#A# ",
                "  ######### "
        ));

        assertThat(amphipodGame.determineAllSolutions()).isEqualTo(12521);
    }

    @Test
    void example_part2() {
        var amphipodGame = new AmphipodGame(List.of(
                "#############",
                "#...........#",
                "###B#C#B#D###",
                "  #D#C#B#A#  ",
                "  #D#B#A#C#  ",
                "  #A#D#C#A#  ",
                "  #########  "
        ));

        assertThat(amphipodGame.determineAllSolutions()).isEqualTo(44169);
    }

    @Test
    void part1() {
        var amphipodGame = new AmphipodGame(Util.readFileAsStringList("21_day23.txt"));

        assertThat(amphipodGame.determineAllSolutions()).isEqualTo(14371);
    }


    @Test
    void part2() {
        var amphipodGame = new AmphipodGame(List.of(
                "#############",
                "#...........#",
                "###D#C#D#B###",
                "  #D#C#B#A# ",
                "  #D#B#A#C# ",
                "  #B#A#A#C# ",
                "  ######### "
        ));

        assertThat(amphipodGame.determineAllSolutions()).isEqualTo(40941);
    }
}