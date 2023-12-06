package be.bitbox.adventofcode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    void testDigitList() {
        Assertions.assertThat(StringUtils.onlyDigits("80s085")).containsExactly(8, 0, 0, 8, 5);
        Assertions.assertThat(StringUtils.onlyDigits("ab0123e4r567_)899ee")).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9);
    }

    @Test
    void testRemovalDoubleSpaces() {
        Assertions.assertThat(StringUtils.removeDoubleSpaces("  a  b c   d  ")).isEqualTo(" a b c d ");
    }
}