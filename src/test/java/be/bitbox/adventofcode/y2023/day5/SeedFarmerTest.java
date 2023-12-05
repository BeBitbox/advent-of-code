package be.bitbox.adventofcode.y2023.day5;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SeedFarmerTest {

    @Test
    void testExample() {
        var seedFarmer = new SeedFarmer(List.of(
                "seeds: 79 14 55 13",
                "",
                "seed-to-soil map:",
                "50 98 2",
                "52 50 48",
                "",
                "soil-to-fertilizer map:",
                "0 15 37",
                "37 52 2",
                "39 0 15",
                "",
                "fertilizer-to-water map:",
                "49 53 8",
                "0 11 42",
                "42 0 7",
                "57 7 4",
                "",
                "water-to-light map:",
                "88 18 7",
                "18 25 70",
                "",
                "light-to-temperature map:",
                "45 77 23",
                "81 45 19",
                "68 64 13",
                "",
                "temperature-to-humidity map:",
                "0 69 1",
                "1 0 69",
                "",
                "humidity-to-location map:",
                "60 56 37",
                "56 93 4"
        ));

        assertThat(seedFarmer.getSeeds()).containsExactly(79L, 14L, 55L, 13L);
        assertThat(seedFarmer.getTransformerList()).hasSize(7);
        assertThat(seedFarmer.minimumLocation()).isEqualTo(35);
        assertThat(seedFarmer.minimumLocationWithSeedRanges()).isEqualTo(46);
    }

    @Test
    void test() {
        var seedFarmer = new SeedFarmer(Util.readFileAsStringList("23_day5.txt"));

        assertThat(seedFarmer.minimumLocation()).isEqualTo(111627841L);
        assertThat(seedFarmer.minimumLocationWithSeedRanges()).isEqualTo(69323688L);
    }
}