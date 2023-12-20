package be.bitbox.adventofcode.y2023.day13;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReflectionTest {

    @Test
    void testExample1() {
        var reflection = new Reflection(List.of(
                "#.##..##.",
                "..#.##.#.",
                "##......#",
                "##......#",
                "..#.##.#.",
                "..##..##.",
                "#.#.##.#."
        ));

        assertThat(reflection.summarize()).isEqualTo(5);
        assertThat(reflection.smudge()).isEqualTo(300);
    }

    @Test
    void testExample2() {
        var reflection = new Reflection(List.of(
                "#...##..#",
                "#....#..#",
                "..##..###",
                "#####.##.",
                "#####.##.",
                "..##..###",
                "#....#..#"
        ));

        assertThat(reflection.summarize()).isEqualTo(400);
        assertThat(reflection.smudge()).isEqualTo(100);
    }

    @Test
    void testExample3() {
        var reflection = new Reflection(List.of(
                "#.##..#",
                "#####..",
                "##.##..",
                "##.##..",
                "#####..",
                "#.##..#",
                ".###.##",
                ".#.##..",
                ".###.##",
                ".##..##",
                "#.#.###",
                ".#.....",
                "##.##..",
                ".######",
                ".#.####"
        ));

        assertThat(reflection.summarize()).isEqualTo(300);
        assertThat(reflection.smudge()).isEqualTo(1400);
    }

    @Test
    void testExample4() {
        var reflection = new Reflection(List.of(
                "###.##.##",
                "##.####.#",
                "##.#..#.#",
                "####..###",
                "....##...",
                "##.#..#.#",
                "...#..#..",
                "##..###.#",
                "##......#",
                "##......#",
                "..#.##.#.",
                "...#..#..",
                "##.####.#",
                "....##...",
                "...####..",
                "....##...",
                "##.####.#"
        ));

        assertThat(reflection.summarize()).isEqualTo(1);
        assertThat(reflection.smudge()).isEqualTo(5);
    }
}