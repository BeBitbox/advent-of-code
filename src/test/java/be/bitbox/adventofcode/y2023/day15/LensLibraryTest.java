package be.bitbox.adventofcode.y2023.day15;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LensLibraryTest {

    @Test
    void testExample() {
        var lensLibrary = new LensLibrary(List.of("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"));

        assertThat(lensLibrary.resultPart1()).isEqualTo(1320);
        assertThat(lensLibrary.resultPart2()).isEqualTo(145);
    }

    @Test
    void testFile() {
        var lensLibrary = new LensLibrary(Util.readFileAsStringList("23_day15.txt"));

        assertThat(lensLibrary.resultPart1()).isEqualTo(515974);
        assertThat(lensLibrary.resultPart2()).isEqualTo(265894);
    }
}