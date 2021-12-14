package be.bitbox.adventofcode.y2021.day14;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PolymerTemplateTest {

    @Test
    void testSmall() {
        var polymerTemplate = new PolymerTemplate(List.of(
                "NNCB   ",
                "",
                "CH -> B",
                "HH -> N",
                "CB -> H",
                "NH -> C",
                "HB -> C",
                "HC -> B",
                "HN -> C",
                "NN -> C",
                "BH -> H",
                "NC -> B",
                "NB -> B",
                "BN -> B",
                "BB -> N",
                "BC -> B",
                "CC -> N",
                "CN -> C"
        ));

        assertThat(polymerTemplate.getPolymer()).isEqualTo("NNCB");
        assertThat(polymerTemplate.getPairs()).hasSize(16);
        assertThat(polymerTemplate.getPairs().get("BN")).isEqualTo('B');
        assertThat(polymerTemplate.getPairs().get("CN")).isEqualTo('C');

        assertThat(polymerTemplate.getScore(10)).isEqualTo(1588);
        assertThat(polymerTemplate.getScore(40)).isEqualTo(2188189693529L);
    }

    @Test
    void testBig() {
        var polymerTemplate = new PolymerTemplate(Util.readFileAsStringList("21_day14.txt"));

        assertThat(polymerTemplate.getPairs()).hasSize(100);
        assertThat(polymerTemplate.getScore(10)).isEqualTo(3095);
        assertThat(polymerTemplate.getScore(40)).isEqualTo(3152788426516L);
    }
}