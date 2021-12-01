package be.bitbox.adventofcode.y2020.day3;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ForestMapTest {

    @Test
    void testSimpleMapCreation() {
        var simpleTreeLine = new ForestMap(List.of(".#"));
        assertThat(simpleTreeLine.isTree(0, 0)).isFalse();
        assertThat(simpleTreeLine.isTree(1, 0)).isTrue();
        assertThat(simpleTreeLine.isTree(2, 0)).isFalse();
        assertThat(simpleTreeLine.isTree(3, 0)).isTrue();
    }

    @Test
    void testBigMapCreation() {
        var forestMap = createBigExampleForestMap();
        assertThat(forestMap.isTree(0, 0)).isFalse();
        assertThat(forestMap.isTree(1, 0)).isFalse();
        assertThat(forestMap.isTree(2, 0)).isTrue();
        assertThat(forestMap.isTree(3, 0)).isTrue();
        assertThat(forestMap.isTree(13, 0)).isTrue();
        assertThat(forestMap.isTree(14, 0)).isTrue();

        assertThat(forestMap.isTree(0, 1)).isTrue();
        assertThat(forestMap.isTree(0, 2)).isFalse();
    }

    @Test
    void testSimpleMoveThroughForest() {
        var simpleForest = new ForestMap(List.of(".#.", ".#.", ".#."));
        assertThat(simpleForest.numberOfTrees(1,1)).isEqualTo(1);
        assertThat(simpleForest.numberOfTrees(2,2)).isEqualTo(0);
        assertThat(simpleForest.numberOfTrees(2,1)).isEqualTo(1);
        assertThat(simpleForest.numberOfTrees(3,1)).isEqualTo(0);
    }

    @Test
    void testSimpleMoveThroughTreeLine() {
        var simpleForest = new ForestMap(List.of("#", "#", "#", ".", "#"));
        assertThat(simpleForest.numberOfTrees(0,1)).isEqualTo(4);
        assertThat(simpleForest.numberOfTrees(2,2)).isEqualTo(3);
    }

    @Test
    void testMoveThroughForest() {
        var forestMap = createBigExampleForestMap();

        int trees_1_1 = forestMap.numberOfTrees(1, 1);
        int trees_3_1 = forestMap.numberOfTrees(3, 1);
        int trees_5_1 = forestMap.numberOfTrees(5, 1);
        int trees_7_1 = forestMap.numberOfTrees(7, 1);
        int trees_1_2 = forestMap.numberOfTrees(1, 2);

        assertThat(trees_1_1).isEqualTo(2);
        assertThat(trees_3_1).isEqualTo(7);
        assertThat(trees_5_1).isEqualTo(3);
        assertThat(trees_7_1).isEqualTo(4);
        assertThat(trees_1_2).isEqualTo(2);
    }

    @Test
    void countTreesFromFile() {
        var forestMap = new ForestMap(Util.readFileAsStringList("day3.txt"));
        int trees_1_1 = forestMap.numberOfTrees(1, 1);
        int trees_3_1 = forestMap.numberOfTrees(3, 1);
        int trees_5_1 = forestMap.numberOfTrees(5, 1);
        int trees_7_1 = forestMap.numberOfTrees(7, 1);
        int trees_1_2 = forestMap.numberOfTrees(1, 2);

        assertThat(trees_3_1).isEqualTo(262);
        assertThat((long) trees_1_1 * trees_3_1 * trees_5_1 * trees_7_1 * trees_1_2).isEqualTo(2698900776L);
    }

    private ForestMap createBigExampleForestMap() {
        return new ForestMap(List.of(
                "..##.......",
                "#...#...#..",
                ".#....#..#.",
                "..#.#...#.#",
                ".#...##..#.",
                "..#.##.....",
                ".#.#.#....#",
                ".#........#",
                "#.##...#...",
                "#...##....#",
                ".#..#...#.#"));
    }
}