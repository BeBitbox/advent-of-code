package be.bitbox.adventofcode.y2021.day1;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DepthCalculatorTest {

    @Test
    void testDepthCounter() {
        var depthCalculator = new DepthCalculator(List.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263));

        assertThat(depthCalculator.numberOfDepthIncreases()).isEqualTo(7);
        assertThat(depthCalculator.getMeasurementWindows()).containsExactly(607, 618, 618, 617, 647, 716, 769, 792);

        assertThat(new DepthCalculator(depthCalculator.getMeasurementWindows()).numberOfDepthIncreases()).isEqualTo(5);
    }

    @Test
    void testDepthCounterBig() {
        var depthCalculator = new DepthCalculator(Util.readFileAsIntList("21_day1.txt"));

        assertThat(depthCalculator.numberOfDepthIncreases()).isEqualTo(1139);
        assertThat(new DepthCalculator(depthCalculator.getMeasurementWindows()).numberOfDepthIncreases()).isEqualTo(1103);
    }
}