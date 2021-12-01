package be.bitbox.adventofcode.y2020.day7;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LuggageRegulationsTest {

    @Test
    void testParsingBags() {
        var luggageRegulations = new LuggageRegulations(exampleRules());

        var lightRed = luggageRegulations.getLuggageBag("light red");
        assertThat(lightRed).isNotNull();
        assertThat(lightRed.getColor()).isEqualTo("light red");
        assertThat(lightRed.getParents()).isEmpty();

        var darkOrange = luggageRegulations.getLuggageBag("dark orange");
        assertThat(darkOrange).isNotNull();
        assertThat(darkOrange.getColor()).isEqualTo("dark orange");
        assertThat(darkOrange.getParents()).isEmpty();

        var brightWhite = luggageRegulations.getLuggageBag("bright white");
        assertThat(brightWhite).isNotNull();
        assertThat(brightWhite.getColor()).isEqualTo("bright white");
        assertThat(brightWhite.getParents()).contains(darkOrange, lightRed);

        var mutedYellow = luggageRegulations.getLuggageBag("muted yellow");
        assertThat(mutedYellow).isNotNull();
        assertThat(mutedYellow.getColor()).isEqualTo("muted yellow");
        assertThat(mutedYellow.getParents()).contains(darkOrange, lightRed);

        var shinyGold = luggageRegulations.getLuggageBag("shiny gold");
        assertThat(shinyGold).isNotNull();
        assertThat(shinyGold.getColor()).isEqualTo("shiny gold");
        assertThat(shinyGold.getParents()).contains(brightWhite, mutedYellow);

        var fadedBlue = luggageRegulations.getLuggageBag("faded blue");
        assertThat(fadedBlue).isNotNull();
        assertThat(fadedBlue.getColor()).isEqualTo("faded blue");
        assertThat(fadedBlue.getParents()).contains(mutedYellow, new LuggageBag("vibrant plum"), new LuggageBag("dark olive"));

        // CHILDS
        assertThat(lightRed.getChildNumber(brightWhite)).isEqualTo(1);
        assertThat(lightRed.getChildNumber(mutedYellow)).isEqualTo(2);
        assertThat(mutedYellow.getChildNumber(shinyGold)).isEqualTo(2);
        assertThat(mutedYellow.getChildNumber(fadedBlue)).isEqualTo(9);
    }


    @Test
    void testCountingBags() {
        var luggageRegulations = new LuggageRegulations(exampleRules());

        assertThat(luggageRegulations.countTheParents("light red")).isEqualTo(0);
        assertThat(luggageRegulations.countTheParents("dark orange")).isEqualTo(0);
        assertThat(luggageRegulations.countTheParents("bright white")).isEqualTo(2);
        assertThat(luggageRegulations.countTheParents("muted yellow")).isEqualTo(2);
        assertThat(luggageRegulations.countTheParents("shiny gold")).isEqualTo(4);
        assertThat(luggageRegulations.countTheParents("dark olive")).isEqualTo(5);
        assertThat(luggageRegulations.countTheParents("vibrant plum")).isEqualTo(5);
        assertThat(luggageRegulations.countTheParents("faded blue")).isEqualTo(7);
    }

    @Test
    void testContentsBag() {
        var luggageRegulations = new LuggageRegulations(exampleRules());

        assertThat(luggageRegulations.contentsBag("faded blue")).isEqualTo(0);
        assertThat(luggageRegulations.contentsBag("dotted black")).isEqualTo(0);
        assertThat(luggageRegulations.contentsBag("dark olive")).isEqualTo(7);
        assertThat(luggageRegulations.contentsBag("vibrant plum")).isEqualTo(11);
        assertThat(luggageRegulations.contentsBag("shiny gold")).isEqualTo(32);
    }

    @Test
    void testContentsBag2() {
        var luggageRegulations = new LuggageRegulations(exampleRulesForCounting());

        assertThat(luggageRegulations.contentsBag("shiny gold")).isEqualTo(126);
    }

    @Test
    void testFileInput() {
        var luggageRegulations = new LuggageRegulations(Util.readFileAsStringList("day7.txt"));
        assertThat(luggageRegulations.countTheParents("shiny gold")).isEqualTo(128);
        assertThat(luggageRegulations.contentsBag("shiny gold")).isEqualTo(20189);
    }

    private List<String> exampleRules() {
        return List.of(
                "light red bags contain 1 bright white bag, 2 muted yellow bags.    ",
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags. ",
                "bright white bags contain 1 shiny gold bag.                        ",
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.    ",
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.     ",
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.    ",
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.  ",
                "faded blue bags contain no other bags.                             ",
                "dotted black bags contain no other bags.                           "
        );
    }

    private List<String> exampleRulesForCounting() {
        return List.of(
                "shiny gold bags contain 2 dark red bags.",
                "dark red bags contain 2 dark orange bags.",
                "dark orange bags contain 2 dark yellow bags.",
                "dark yellow bags contain 2 dark green bags.",
                "dark green bags contain 2 dark blue bags.",
                "dark blue bags contain 2 dark violet bags.",
                "dark violet bags contain no other bags."
        );
    }
}