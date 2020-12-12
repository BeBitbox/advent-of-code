package be.bitbox.adventofcode.y2020.day2;

import be.bitbox.adventofcode.y2020.Util;
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
        assertThat(new PasswordEntry("2-2 a: a").isValid()).isFalse();
        assertThat(new PasswordEntry("2-2 a: aaaaa").isValid()).isFalse();
        assertThat(new PasswordEntry("1-3 b: cdefg").isValid()).isFalse();
        assertThat(new PasswordEntry("4-7 c: cccc").isValid()).isTrue();
        assertThat(new PasswordEntry("4-7 c: ccccccc").isValid()).isTrue();
        assertThat(new PasswordEntry("4-7 c: cccccccc").isValid()).isFalse();
    }

    @Test
    void testValidMethod2() {
        assertThat(new PasswordEntry("1-2 a: ab").isValidPart2()).isTrue();
        assertThat(new PasswordEntry("1-2 a: ba").isValidPart2()).isTrue();
        assertThat(new PasswordEntry("1-2 a: aa").isValidPart2()).isFalse();
        assertThat(new PasswordEntry("1-2 a: bb").isValidPart2()).isFalse();
        assertThat(new PasswordEntry("1-3 a: abcde").isValidPart2()).isTrue();
        assertThat(new PasswordEntry("1-3 b: cdefg").isValidPart2()).isFalse();
        assertThat(new PasswordEntry("2-9 c: ccccccccc").isValidPart2()).isFalse();
    }

    @Test
    void testFile() {
        var validOnes = Util.readFileAsStringList("day2.txt").stream()
                .map(PasswordEntry::new)
                .map(PasswordEntry::isValid)
                .filter(valid -> valid)
                .count();
        assertThat(validOnes).isEqualTo(424);
    }

    @Test
    void testFilePart2() {
        var validOnes = Util.readFileAsStringList("day2.txt").stream()
                .map(PasswordEntry::new)
                .map(PasswordEntry::isValidPart2)
                .filter(valid -> valid)
                .count();
        assertThat(validOnes).isEqualTo(747);
    }
}