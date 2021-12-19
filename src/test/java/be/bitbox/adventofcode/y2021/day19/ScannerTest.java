package be.bitbox.adventofcode.y2021.day19;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScannerTest {

    @Test
    void testCreation() {
        var scanner = new Scanner(List.of(
                "--- scanner 11 ---",
                "-1,-1,1",
                "-2,-2,2",
                "-3,-3,3",
                "-2,-3,1",
                "5,6,-4 ",
                "8,0,7  "
        ));

        assertThat(scanner.getNumber()).isEqualTo(11);
        assertThat(scanner.getCoordinates()).hasSize(6);
        assertThat(scanner.rotate()).hasSize(24);
    }
}