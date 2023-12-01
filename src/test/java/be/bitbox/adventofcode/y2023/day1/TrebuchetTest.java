package be.bitbox.adventofcode.y2023.day1;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TrebuchetTest {

    @Test
    void testCalibrationDigits_example() {
        Trebuchet trebuchet1 = new Trebuchet(List.of("1abc2"));
        Trebuchet trebuchet2 = new Trebuchet(List.of("88", "77"));
        Trebuchet trebuchet3 = new Trebuchet(List.of(
                "1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet"));
        Trebuchet trebuchet4 = new Trebuchet(List.of("lpms5", "3xckjzm"));


        assertThat(trebuchet1.getCalibrationOnlyDigits()).isEqualTo(12);
        assertThat(trebuchet2.getCalibrationOnlyDigits()).isEqualTo(165);
        assertThat(trebuchet3.getCalibrationOnlyDigits()).isEqualTo(142);
        assertThat(trebuchet4.getCalibrationOnlyDigits()).isEqualTo(88);
    }

    @RepeatedTest(20)
    void testCalibrationText_example() {
        Trebuchet trebuchet1 = new Trebuchet(List.of("1abc2"));
        Trebuchet trebuchet2 = new Trebuchet(List.of("seven"));
        Trebuchet trebuchet3 = new Trebuchet(List.of(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen"
        ));

        assertThat(trebuchet1.getCalibration()).isEqualTo(12);
        assertThat(trebuchet2.getCalibration()).isEqualTo(77);
        assertThat(trebuchet3.getCalibration()).isEqualTo(281);
    }

    @Test
    void testCalibration() {
        List<String> input = Util.readFileAsStringList("23_day1.txt");
        Trebuchet trebuchet = new Trebuchet(input);

        assertThat(trebuchet.getCalibrationOnlyDigits()).isEqualTo(54968);
        assertThat(trebuchet.getCalibration()).isEqualTo(54094);
    }
}