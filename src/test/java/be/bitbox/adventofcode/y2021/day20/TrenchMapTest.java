package be.bitbox.adventofcode.y2021.day20;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class TrenchMapTest {

    @Test
    void enchance() {
        var trenchMap = new TrenchMap(List.of(
                "..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..###..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###.######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#..#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#......#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.....####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.......##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#",
                "",
                "#..#.",
                "#....",
                "##..#",
                "..#..",
                "..###"));

        assertThat(trenchMap.numberOfPixelsLit()).isEqualTo(10);
        trenchMap.enchance();
        assertThat(trenchMap.numberOfPixelsLit()).isEqualTo(24);
        trenchMap.enchance();
        assertThat(trenchMap.numberOfPixelsLit()).isEqualTo(35);

        trenchMap.enchance(48);
        assertThat(trenchMap.numberOfPixelsLit()).isEqualTo(3351);
    }

    @Test
    void enchance_big() {
        var trenchMap = new TrenchMap(Util.readFileAsStringList("21_day20.txt"));

        trenchMap.enchance();
        trenchMap.enchance();
        assertThat(trenchMap.numberOfPixelsLit()).isEqualTo(5819);

        trenchMap.enchance(48);
        assertThat(trenchMap.numberOfPixelsLit()).isEqualTo(18516);
    }
}