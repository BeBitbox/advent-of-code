package be.bitbox.adventofcode.y2021.day7;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CrabLineTest {

    @Test
    void calculateLeastFuel() {
        var crabLine = new CrabLine("16,1,2,0,4,2,7,1,2,14");

        assertThat(crabLine.calculateLeastFuel(true)).isEqualTo(37);
        assertThat(crabLine.calculateLeastFuel(false)).isEqualTo(168);
    }

    @Test
    void calculateLeastFuel_Big() {
        var crabLine = new CrabLine(Util.readFileAsString("21_day7.txt"));

        assertThat(crabLine.calculateLeastFuel(true)).isEqualTo(345035L);
        assertThat(crabLine.calculateLeastFuel(false)).isEqualTo(97038163L);
    }
}