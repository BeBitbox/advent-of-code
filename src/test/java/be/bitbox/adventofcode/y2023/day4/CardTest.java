package be.bitbox.adventofcode.y2023.day4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    void testParsing() {
        var card = new Card("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36");

        assertThat(card.getId()).isEqualTo(5);
        assertThat(card.getWinningNumbers()).containsExactly(87, 83, 26, 28, 32);
        assertThat(card.getOwnNumbers()).containsExactly(88, 30, 70, 12, 93, 22, 82, 36);
        assertThat(card.getPoints()).isEqualTo(0);
        assertThat(card.getWonCards()).hasSize(0);
    }

    @Test
    void testParsing2() {
        var card = new Card("Card 1: 41 48 83 86 17 | 83 86 6 31 17 9 48 53");

        assertThat(card.getId()).isEqualTo(1);
        assertThat(card.getWinningNumbers()).containsExactly(41, 48, 83, 86, 17);
        assertThat(card.getOwnNumbers()).containsExactly(83, 86, 6, 31, 17, 9, 48, 53);
        assertThat(card.getPoints()).isEqualTo(8);
        assertThat(card.getWonCards()).containsExactly(2, 3, 4, 5);
    }
}