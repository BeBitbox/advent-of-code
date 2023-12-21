package be.bitbox.adventofcode.y2023.day14;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParabolicReflectorDishTest {

    @Test
    void testExample() {
        var parabolicReflectorDish = new ParabolicReflectorDish(List.of(
                "O....#....",
                "O.OO#....#",
                ".....##...",
                "OO.#O....O",
                ".O.....O#.",
                "O.#..O.#.#",
                "..O..#O..O",
                ".......O..",
                "#....###..",
                "#OO..#...."
        ));

        parabolicReflectorDish.tiltNorth();
        assertThat(parabolicReflectorDish.totalLoad()).isEqualTo(136);
    }

    @Test
    void testExamplePart2() {
        var parabolicReflectorDish = new ParabolicReflectorDish(List.of(
                "O....#....",
                "O.OO#....#",
                ".....##...",
                "OO.#O....O",
                ".O.....O#.",
                "O.#..O.#.#",
                "..O..#O..O",
                ".......O..",
                "#....###..",
                "#OO..#...."
        ));

        assertThat(parabolicReflectorDish.totalLoadPart2()).isEqualTo(64);
    }

    @Test
    void testFile() {
        var parabolicReflectorDish = new ParabolicReflectorDish(Util.readFileAsStringList("23_day14.txt"));

        parabolicReflectorDish.tiltNorth();
        assertThat(parabolicReflectorDish.totalLoad()).isEqualTo(110565);
    }

    @Test
    void testFilePart2() {
        var parabolicReflectorDish = new ParabolicReflectorDish(Util.readFileAsStringList("23_day14.txt"));

        assertThat(parabolicReflectorDish.totalLoadPart2()).isEqualTo(89845);
    }
}