package be.bitbox.adventofcode.y2021.day5;

import be.bitbox.adventofcode.Tuple;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HydroThermalVentTest {

    @Test
    void testTuplesHorizontal() {
        var hydroThermalVent = new HydroThermalVent("1,1 -> 1,3");
        assertThat(hydroThermalVent.isNotDiagonal()).isTrue();
        assertThat(hydroThermalVent.getTuples()).containsExactlyInAnyOrder(new Tuple<>(1,1), new Tuple<>(1,2), new Tuple<>(1,3));
    }

    @Test
    void testTuplesVertical() {
        var hydroThermalVent = new HydroThermalVent("9,7 -> 7,7");
        assertThat(hydroThermalVent.isNotDiagonal()).isTrue();
        assertThat(hydroThermalVent.getTuples()).containsExactlyInAnyOrder(new Tuple<>(9,7), new Tuple<>(8,7), new Tuple<>(7,7));
    }

    @Test
    void testTuplesDiagonal() {
        var hydroThermalVent = new HydroThermalVent("1,1 -> 3,3");
        assertThat(hydroThermalVent.isNotDiagonal()).isFalse();
        assertThat(hydroThermalVent.getTuples()).containsExactlyInAnyOrder(new Tuple<>(1,1), new Tuple<>(2,2), new Tuple<>(3,3));
    }

    @Test
    void testTuplesDiagonal2() {
        var hydroThermalVent = new HydroThermalVent("9,7 -> 7,9");
        assertThat(hydroThermalVent.isNotDiagonal()).isFalse();
        assertThat(hydroThermalVent.getTuples()).containsExactlyInAnyOrder(new Tuple<>(9,7), new Tuple<>(8,8), new Tuple<>(7,9));
    }
}