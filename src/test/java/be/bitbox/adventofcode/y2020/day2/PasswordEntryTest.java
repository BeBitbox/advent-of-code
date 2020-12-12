package be.bitbox.adventofcode.y2020.day2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordEntryTest {

    @Test
    void testCreation() {
        var passwordEntry = new PasswordEntry("1-3 b: cdefg");
        assertThat(passwordEntry.getIndex1()).isEqualTo(1);
        assertThat(passwordEntry.getIndex2()).isEqualTo(3);
        assertThat(passwordEntry.getLetter()).isEqualTo('b');
        assertThat(passwordEntry.getPassword()).isEqualTo("cdefg");
    }

    @Test
    void testValidMethod1() {
        assertThat(new PasswordEntry("1-1 a: a").isValid()).isTrue();
        assertThat(new PasswordEntry("1-1 a: b").isValid()).isFalse();
    }
}