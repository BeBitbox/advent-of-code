package be.bitbox.adventofcode.y2023.day7;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JokerHandTest {

    @Test
    void testExamples() {
        assertThat(new JokerHand("AAAAA 1").getType()).isEqualTo(JokerHand.Type.FIVE_OF_A_KIND);
        assertThat(new JokerHand("AA8AA 1").getType()).isEqualTo(JokerHand.Type.FOUR_OF_A_KIND);
        assertThat(new JokerHand("T55J5 1").getType()).isEqualTo(JokerHand.Type.FOUR_OF_A_KIND);
        assertThat(new JokerHand("KTJJT 1").getType()).isEqualTo(JokerHand.Type.FOUR_OF_A_KIND);
        assertThat(new JokerHand("QQQJA 1").getType()).isEqualTo(JokerHand.Type.FOUR_OF_A_KIND);
        assertThat(new JokerHand("23332 1").getType()).isEqualTo(JokerHand.Type.FULL_HOUSE);
        assertThat(new JokerHand("TTT98 1").getType()).isEqualTo(JokerHand.Type.THREE_OF_A_KIND);
        assertThat(new JokerHand("23432 1").getType()).isEqualTo(JokerHand.Type.TWO_PAIR);
        assertThat(new JokerHand("A23A4 1").getType()).isEqualTo(JokerHand.Type.ONE_PAIR);
        assertThat(new JokerHand("23456 1").getType()).isEqualTo(JokerHand.Type.HIGH_CARD);
        assertThat(new JokerHand("23456 788").getBid()).isEqualTo(788);

        assertThat(new JokerHand("AAAAA 1").compareTo(new JokerHand("23332 9"))).isNegative();
        assertThat(new JokerHand("23456 1").compareTo(new JokerHand("A23A4 9"))).isPositive();

        assertThat(new JokerHand("33332 1").compareTo(new JokerHand("2AAAA 9"))).isNegative();
        assertThat(new JokerHand("77888 1").compareTo(new JokerHand("77788 9"))).isNegative();
    }

    @Test
    void specialCases() {
        //5 J
        assertThat(new JokerHand("JJJJJ 1").getType()).isEqualTo(JokerHand.Type.FIVE_OF_A_KIND);

        //4 J
        assertThat(new JokerHand("JJAJJ 1").getType()).isEqualTo(JokerHand.Type.FIVE_OF_A_KIND);

        //3 J
        assertThat(new JokerHand("JJJAA 1").getType()).isEqualTo(JokerHand.Type.FIVE_OF_A_KIND);
        assertThat(new JokerHand("JJJ23 1").getType()).isEqualTo(JokerHand.Type.FOUR_OF_A_KIND);

        //2 J
        assertThat(new JokerHand("JJ123 1").getType()).isEqualTo(JokerHand.Type.THREE_OF_A_KIND);
        assertThat(new JokerHand("JJ113 1").getType()).isEqualTo(JokerHand.Type.FOUR_OF_A_KIND);
        assertThat(new JokerHand("JJ111 1").getType()).isEqualTo(JokerHand.Type.FIVE_OF_A_KIND);

        //1 J
        assertThat(new JokerHand("J1234 1").getType()).isEqualTo(JokerHand.Type.ONE_PAIR);
        assertThat(new JokerHand("J1134 1").getType()).isEqualTo(JokerHand.Type.THREE_OF_A_KIND);
        assertThat(new JokerHand("J1114 1").getType()).isEqualTo(JokerHand.Type.FOUR_OF_A_KIND);
        assertThat(new JokerHand("J1111 1").getType()).isEqualTo(JokerHand.Type.FIVE_OF_A_KIND);
    }
}