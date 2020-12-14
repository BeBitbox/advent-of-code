package be.bitbox.adventofcode.y2020.day14;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BitMaskTest {

    @Test
    void testMaskMinimal() {
        var bitMask = new BitMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        assertThat(bitMask.afterMaskApplication(1452L)).isEqualTo(1452L);
        //IMPOSSIBLE TO DO WITH ALL X's :)
        assertThat(bitMask.afterFloatingMaskApplication(1452L)).hasSize(0);
    }

    @Test
    void testMaskMaximal() {
        var bitMask = new BitMask("111111111111111111111111111111111111");

        assertThat(bitMask.afterMaskApplication(1452L)).isEqualTo((long) Math.pow(2, 36) - 1);
        assertThat(bitMask.afterFloatingMaskApplication(1452L)).containsExactly((long) Math.pow(2, 36) - 1);
    }

    @Test
    void testMaskExamples() {
        var bitMask = new BitMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X");

        assertThat(bitMask.afterMaskApplication(11)).isEqualTo(73);
        assertThat(bitMask.afterMaskApplication(101)).isEqualTo(101);
        assertThat(bitMask.afterMaskApplication(0)).isEqualTo(64);
    }

    @Test
    void testFloatingMaskExample1() {
        var bitMask = new BitMask("000000000000000000000000000000X1001X");

        assertThat(bitMask.afterFloatingMaskApplication(42)).contains(26L, 27L, 58L, 59L);
    }

    @Test
    void testFloatingMaskExample2() {
        var bitMask = new BitMask("00000000000000000000000000000000X0XX");

        assertThat(bitMask.afterFloatingMaskApplication(26)).contains(16L, 17L, 18L, 19L, 24L, 25L, 26L, 27L);
    }
}