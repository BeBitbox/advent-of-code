package be.bitbox.adventofcode.y2023.day2;

import be.bitbox.adventofcode.Util;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CubeConundrumTest {

    @Test
    void testExample() {
        CubeConundrum cubeConundrum = new CubeConundrum(List.of(
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        ));

        assertThat(cubeConundrum.sumOfMatchingGames(12, 13, 14)).isEqualTo(8);
        assertThat(cubeConundrum.powerOfAllGames()).isEqualTo(2286);
    }

    @Test
    void testPart1() {
        var cubeConundrum = new CubeConundrum(Util.readFileAsStringList("23_day2.txt"));

        assertThat(cubeConundrum.sumOfMatchingGames(12, 13, 14)).isEqualTo(3099);
        assertThat(cubeConundrum.powerOfAllGames()).isEqualTo(72970);
    }
}