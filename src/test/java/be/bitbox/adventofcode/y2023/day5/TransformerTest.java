package be.bitbox.adventofcode.y2023.day5;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TransformerTest {

    @Test
    void testParsing() {
        var transformer = new Transformer(List.of(
                "water-to-light map:",
                "88 18 7",
                "18 25 70"
        ));

        assertThat(transformer.getSource()).isEqualTo(Transformer.TYPE.WATER);
        assertThat(transformer.getDestination()).isEqualTo(Transformer.TYPE.LIGHT);
        assertThat(transformer.getTransformList()).hasSize(2);
        var transform = transformer.getTransformList().get(1);
        assertThat(transform.destinationRage()).isEqualTo(18);
        assertThat(transform.sourceRange()).isEqualTo(25);
        assertThat(transform.rangeLength()).isEqualTo(70);

        assertThat(transformer.morph(17)).isEqualTo(17);
        assertThat(transformer.morph(18)).isEqualTo(88);
        assertThat(transformer.morph(19)).isEqualTo(89);
        assertThat(transformer.morph(24)).isEqualTo(94);
        assertThat(transformer.morph(25)).isEqualTo(18);
        assertThat(transformer.morph(94)).isEqualTo(87);
        assertThat(transformer.morph(95)).isEqualTo(95);
    }
}