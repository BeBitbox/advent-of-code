package be.bitbox.adventofcode.y2021.day21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiracDiceTest {

    @Test
    void deterministicExample() {
        var diracDice = new DiracDice(4, 8);

        diracDice.rollDeterministic();
        assertThat(diracDice.getPosition1()).isEqualTo(10);
        assertThat(diracDice.getPosition2()).isEqualTo(8);
        assertThat(diracDice.getRolls()).isEqualTo(3);
        assertThat(diracDice.getScore1()).isEqualTo(10);
        assertThat(diracDice.getScore2()).isEqualTo(0);

        diracDice.rollDeterministic();
        assertThat(diracDice.getPosition1()).isEqualTo(10);
        assertThat(diracDice.getPosition2()).isEqualTo(3);
        assertThat(diracDice.getRolls()).isEqualTo(6);
        assertThat(diracDice.getScore1()).isEqualTo(10);
        assertThat(diracDice.getScore2()).isEqualTo(3);

        diracDice.rollDeterministic();
        assertThat(diracDice.getPosition1()).isEqualTo(4);
        assertThat(diracDice.getPosition2()).isEqualTo(3);
        assertThat(diracDice.getRolls()).isEqualTo(9);
        assertThat(diracDice.getScore1()).isEqualTo(14);
        assertThat(diracDice.getScore2()).isEqualTo(3);

        diracDice.rollDeterministic();
        assertThat(diracDice.getPosition1()).isEqualTo(4);
        assertThat(diracDice.getPosition2()).isEqualTo(6);
        assertThat(diracDice.getRolls()).isEqualTo(12);
        assertThat(diracDice.getScore1()).isEqualTo(14);
        assertThat(diracDice.getScore2()).isEqualTo(9);

        diracDice.rollDeterministic();
        assertThat(diracDice.getPosition1()).isEqualTo(6);
        assertThat(diracDice.getPosition2()).isEqualTo(6);
        assertThat(diracDice.getRolls()).isEqualTo(15);
        assertThat(diracDice.getScore1()).isEqualTo(20);
        assertThat(diracDice.getScore2()).isEqualTo(9);

        assertThat(diracDice.rollDeterministicTillScore1000()).isEqualTo(739785);
    }

    @Test
    void diracExample() {
        var diracDice = new DiracDice(4, 8);

        assertThat(diracDice.playMadGame()).isEqualTo(444356092776315L);
    }


    @Test
    void deterministicExample_part2() {
        var diracDice = new DiracDice(7, 2);

        assertThat(diracDice.rollDeterministicTillScore1000()).isEqualTo(678468);
    }

    @Test
    void diracExample_part2() {
        var diracDice = new DiracDice(7, 2);

        assertThat(diracDice.playMadGame()).isEqualTo(131180774190079L);
    }
}