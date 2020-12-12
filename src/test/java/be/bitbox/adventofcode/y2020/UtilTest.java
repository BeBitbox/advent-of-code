package be.bitbox.adventofcode.y2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UtilTest {

    @Test
    void testReadingIntList() {
        List<Integer> integers = Util.readFileAsIntList("day1.txt");
        assertThat(integers).hasSize(200);
        assertThat(integers.get(0)).isEqualTo(2004);
        assertThat(integers.get(199)).isEqualTo(1532);
    }

    @Test
    void testReadingStringList() {
        List<String> stringList = Util.readFileAsStringList("day2.txt");
        assertThat(stringList).hasSize(1000);
        assertThat(stringList.get(0)).isEqualTo("15-16 l: klfbblslvjclmlnqklvg");
        assertThat(stringList.get(999)).isEqualTo("5-6 d: dddtpdd");
    }
}