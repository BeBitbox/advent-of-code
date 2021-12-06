package be.bitbox.adventofcode.y2021.day6;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LanternSchoolTest {

    @Test
    void calculate() {
        var lanternSchool = new LanternSchool("3,4,3,1,2");

        assertThat(lanternSchool.calculateBruteForce(80)).isEqualTo(5934);
        assertThat(lanternSchool.calculate(80)).isEqualTo(5934L);
        assertThat(lanternSchool.calculate(256)).isEqualTo(26984457539L);
    }

    @Test
    void calculateBig() {
        var lanternSchool = new LanternSchool(Util.readFileAsStringList("21_day6.txt").get(0));

        assertThat(lanternSchool.calculateBruteForce(80)).isEqualTo(387413);
        assertThat(lanternSchool.calculate(256)).isEqualTo(1738377086345L);
    }
}