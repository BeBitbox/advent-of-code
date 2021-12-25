package be.bitbox.adventofcode.y2021.day24;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MONADTest {

    @Test
    void getHighestMONAD() {
        var monad = new MONAD(Util.readFileAsStringList("21_day24.txt"));

        assertThat(monad.getHighestMONAD()).isEqualTo("4566l");
    }
}