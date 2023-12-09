package be.bitbox.adventofcode.y2023.day9;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MirageMaintenanceTest {


    @Test
    void testMirageParsing() {
        assertThat(MirageMaintenance.prediction(List.of(0, 3, 6, 9, 12, 15))).isEqualTo(18);

    }

    @Test
    void testMirage() {
        var mirageMaintenance = new MirageMaintenance(List.of(
                "0 3 6 9 12 15",
                "1 3 6 10 15 21",
                "10 13 16 21 30 45"
        ));

        assertThat(mirageMaintenance.prediction()).isEqualTo(114);
        assertThat(mirageMaintenance.predictionReversed()).isEqualTo(2);
    }

    @Test
    void testFile() {
        var mirageMaintenance = new MirageMaintenance(Util.readFileAsStringList("23_day9.txt"));

        assertThat(mirageMaintenance.prediction()).isEqualTo(1772145754L);
        assertThat(mirageMaintenance.predictionReversed()).isEqualTo(867L);
    }
}