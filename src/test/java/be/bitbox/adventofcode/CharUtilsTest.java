package be.bitbox.adventofcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CharUtilsTest {

    @Test
    void digit() {
        assertThat(CharUtils.isDigit('0')).isTrue();
        assertThat(CharUtils.isDigit(48)).isTrue();

        assertThat(CharUtils.isDigit('9')).isTrue();
        assertThat(CharUtils.isDigit(57)).isTrue();

        assertThat(CharUtils.isDigit('A')).isFalse();
        assertThat(CharUtils.isDigit(97)).isFalse();

        assertThat(CharUtils.isDigit('$')).isFalse();
        assertThat(CharUtils.isDigit(36)).isFalse();

        assertThat(CharUtils.isDigit(':')).isFalse();
        assertThat(CharUtils.isDigit(58)).isFalse();
    }

    @Test
    void charToDigitInt() {
        assertThat(CharUtils.toInt('0')).isEqualTo(0);
        assertThat(CharUtils.toInt(48)).isEqualTo(0);

        assertThat(CharUtils.toInt('9')).isEqualTo(9);
        assertThat(CharUtils.toInt(57)).isEqualTo(9);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> CharUtils.toInt(':'));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> CharUtils.toInt(58));
    }
}