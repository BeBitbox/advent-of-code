package be.bitbox.adventofcode.y2023.day7;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalHandTest {

    @Test
    void testExamples() {
        assertThat(new NormalHand("AAAAA 1").getType()).isEqualTo(NormalHand.Type.FIVE_OF_A_KIND);
        assertThat(new NormalHand("AA8AA 1").getType()).isEqualTo(NormalHand.Type.FOUR_OF_A_KIND);
        assertThat(new NormalHand("23332 1").getType()).isEqualTo(NormalHand.Type.FULL_HOUSE);
        assertThat(new NormalHand("TTT98 1").getType()).isEqualTo(NormalHand.Type.THREE_OF_A_KIND);
        assertThat(new NormalHand("23432 1").getType()).isEqualTo(NormalHand.Type.TWO_PAIR);
        assertThat(new NormalHand("A23A4 1").getType()).isEqualTo(NormalHand.Type.ONE_PAIR);
        assertThat(new NormalHand("23456 1").getType()).isEqualTo(NormalHand.Type.HIGH_CARD);
        assertThat(new NormalHand("23456 788").getBid()).isEqualTo(788);

        assertThat(new NormalHand("AAAAA 1").compareTo(new NormalHand("23332 9"))).isNegative();
        assertThat(new NormalHand("23456 1").compareTo(new NormalHand("A23A4 9"))).isPositive();

        assertThat(new NormalHand("33332 1").compareTo(new NormalHand("2AAAA 9"))).isNegative();
        assertThat(new NormalHand("77888 1").compareTo(new NormalHand("77788 9"))).isNegative();
    }
}