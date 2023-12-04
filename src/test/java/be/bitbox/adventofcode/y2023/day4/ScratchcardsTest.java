package be.bitbox.adventofcode.y2023.day4;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScratchcardsTest {

    @Test
    void testGame() {
        var scratchcards = new Scratchcards(List.of(
                "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
                "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
                "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
                "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
        ));

        assertThat(scratchcards.score()).isEqualTo(13);
        assertThat(scratchcards.totalScratchCards()).isEqualTo(30);
    }

    @Test
    void scratchIt() {
        var scratchcards = new Scratchcards(Util.readFileAsStringList("23_day4.txt"));

        assertThat(scratchcards.score()).isEqualTo(21213);
        assertThat(scratchcards.totalScratchCards()).isEqualTo(8549735);
    }
}