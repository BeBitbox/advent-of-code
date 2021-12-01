package be.bitbox.adventofcode.y2020.day9;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class XMASSequenceTest {

    @Test
    void testSimpleList() {
        var xmasSequence = new XMASSequence(List.of(1L, 2L, 3L, 4L, 5L, 400L));

        assertThat(xmasSequence.firstNumberToBreak(5)).isEqualTo(400L);
        assertThat(xmasSequence.encryptionWeakness(5)).isEqualTo(-1L);
    }

    @Test
    void testHarderList() {
        var xmasSequence = new XMASSequence(List.of(1L, 2L, 3L, 4L, 5L, 9L, 5L, 7L, 8L, 13L));

        assertThat(xmasSequence.firstNumberToBreak(5)).isEqualTo(8L);
        assertThat(xmasSequence.encryptionWeakness(5)).isEqualTo(-1L);
    }

    @Test
    void testExampleList() {
        var xmasSquence = new XMASSequence(List.of(
                35L,
                20L,
                15L,
                25L,
                47L,
                40L,
                62L,
                55L,
                65L,
                95L,
                102L,
                117L,
                150L,
                182L,
                127L,
                219L,
                299L,
                277L,
                309L,
                576L
        ));

        assertThat(xmasSquence.firstNumberToBreak(5)).isEqualTo(127);
        assertThat(xmasSquence.encryptionWeakness(5)).isEqualTo(62);
    }

    @Test
    void testXMASfromFile() {
        var xmasSequence = new XMASSequence(Util.readFileAsLongList("day9.txt"));

        assertThat(xmasSequence.firstNumberToBreak(5)).isEqualTo(28L);
        assertThat(xmasSequence.firstNumberToBreak(25)).isEqualTo(248131121L);
        assertThat(xmasSequence.encryptionWeakness(5)).isEqualTo(-1L);
        assertThat(xmasSequence.encryptionWeakness(25)).isEqualTo(31580383L);
    }

}