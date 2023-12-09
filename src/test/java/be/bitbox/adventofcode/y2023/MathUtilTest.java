package be.bitbox.adventofcode.y2023;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MathUtilTest {

    @Test
    void greatestCommonDivisor() {
        assertThat(MathUtil.calculateGreatestCommonDivisor(1, 5)).isEqualTo(1);
        assertThat(MathUtil.calculateGreatestCommonDivisor(5, 3)).isEqualTo(1);
        assertThat(MathUtil.calculateGreatestCommonDivisor(12, 18)).isEqualTo(6);
        assertThat(MathUtil.calculateGreatestCommonDivisor(400_000, 500_000)).isEqualTo(100_000);
    }

    @Test
    void lowestCommonMultiple() {
        assertThat(MathUtil.calculateLowestCommonMultiple(3, 5)).isEqualTo(15);
        assertThat(MathUtil.calculateLowestCommonMultiple(12, 18)).isEqualTo(36);
    }
}