package be.bitbox.adventofcode.y2023.day2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    @Test
    void testParsing1() {
        var game = new Game("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red");

        assertThat(game.getId()).isEqualTo(4);
        assertThat(game.getRed()).isEqualTo(14);
        assertThat(game.getGreen()).isEqualTo(3);
        assertThat(game.getBlue()).isEqualTo(15);
        assertThat(game.getPower()).isEqualTo(630);
    }

    @Test
    void testParsing2() {
        var game = new Game("Game 77: 18 green, 19 red, 11 blue; 1 blue, 18 red; 5 blue, 10 red, 16 green");

        assertThat(game.getId()).isEqualTo(77);
        assertThat(game.getRed()).isEqualTo(19);
        assertThat(game.getGreen()).isEqualTo(18);
        assertThat(game.getBlue()).isEqualTo(11);
        assertThat(game.getPower()).isEqualTo(19 * 18 * 11);
    }
}