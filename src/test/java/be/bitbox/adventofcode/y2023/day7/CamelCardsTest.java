package be.bitbox.adventofcode.y2023.day7;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CamelCardsTest {

    @Test
    void testExample() {
        var camelCards = new CamelCards(List.of(
                "32T3K 765",
                "T55J5 684",
                "KK677 28",
                "KTJJT 220",
                "QQQJA 483"
        ));

        assertThat(camelCards.totalWinningsNormalHands()).isEqualTo(6440);
        assertThat(camelCards.totalWinningsJokerHands()).isEqualTo(5905);
    }

    @Test
    void testFile() {
        var camelCards = new CamelCards(Util.readFileAsStringList("23_day7.txt"));

        assertThat(camelCards.totalWinningsNormalHands()).isEqualTo(250957639);
        assertThat(camelCards.totalWinningsJokerHands()).isEqualTo(251515496);
    }
}