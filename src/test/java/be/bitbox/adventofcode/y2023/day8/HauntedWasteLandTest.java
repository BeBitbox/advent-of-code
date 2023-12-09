package be.bitbox.adventofcode.y2023.day8;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HauntedWasteLandTest {

    @Test
    void testParsing() {
        var hauntedWasteLand = new HauntedWasteLand(List.of(
                "RL",
                "",
                "AAA = (BBB, CCC)",
                "BBB = (DDD, EEE)",
                "CCC = (ZZZ, GGG)",
                "DDD = (DDD, DDD)",
                "EEE = (EEE, EEE)",
                "GGG = (GGG, GGG)",
                "ZZZ = (ZZZ, ZZZ)"
        ));

        assertThat(hauntedWasteLand.getDirections()).containsExactly('R', 'L');
        assertThat(hauntedWasteLand.getLegend()).hasSize(7);

        var stringTuple = hauntedWasteLand.getLegend().get("CCC");
        assertThat(stringTuple.x).isEqualTo("ZZZ");
        assertThat(stringTuple.y).isEqualTo("GGG");

        assertThat(hauntedWasteLand.numberOfStepsSequential("AAA")).isEqualTo(2);
        assertThat(hauntedWasteLand.numberOfStepsParallel()).isEqualTo(2);
    }

    @Test
    void testOtherExample() {
        var hauntedWasteLand = new HauntedWasteLand(List.of(
                "LLR",
                "",
                "AAA = (BBB, BBB)",
                "BBB = (AAA, ZZZ)",
                "ZZZ = (ZZZ, ZZZ)"
        ));

        assertThat(hauntedWasteLand.numberOfStepsSequential("AAA")).isEqualTo(6);
        assertThat(hauntedWasteLand.numberOfStepsParallel()).isEqualTo(6);
    }

    @Test
    void testParallelExample() {
        var hauntedWasteLand = new HauntedWasteLand(List.of(
                "LR",
                "",
                "11A = (11B, XXX)",
                "11B = (XXX, 11Z)",
                "11Z = (11B, XXX)",
                "22A = (22B, XXX)",
                "22B = (22C, 22C)",
                "22C = (22Z, 22Z)",
                "22Z = (22B, 22B)",
                "XXX = (XXX, XXX)"
        ));

        assertThat(hauntedWasteLand.numberOfStepsParallel()).isEqualTo(6);
    }

    @Test
    void testPart() {
        var hauntedWasteLand = new HauntedWasteLand(Util.readFileAsStringList("23_day8.txt"));

        assertThat(hauntedWasteLand.numberOfStepsSequential("AAA")).isEqualTo(12361);
        assertThat(hauntedWasteLand.numberOfStepsParallel()).isEqualTo(18215611419223L);
    }
}