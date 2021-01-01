package be.bitbox.adventofcode.y2020.day20;

import be.bitbox.adventofcode.y2020.Util;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhotoTest {

    @Test
    void testExamplePhoto() {
        var input = Util.readFileAsStringList("day20_example.txt");
        var photo = new Photo(input);

        assertThat(photo.getTiles()).hasSize(9);
        assertThat(photo.getSize()).isEqualTo(3);
        assertThat(photo.getTiles()).contains(new Tile(3079), new Tile(2311), new Tile(1951), new Tile(1171));
        var exampleTile = photo.getTiles().stream()
                .filter(tile -> tile.equals(new Tile(1427)))
                .findAny()
                .orElseThrow();
        assertThat(exampleTile.getId()).isEqualTo(1427);
        assertThat(exampleTile.getBorders()).containsExactlyInAnyOrder(
                "###.##.#..", "..#.##.###",
                "#..#......", "......#..#",
                "..###.#.#.", ".#.#.###..",
                "..##.#..#.", ".#..#.##.."
        );
        assertThat(exampleTile.oppositeBorder("......#..#")).isEqualTo(".#.#.###..");
        assertThat(exampleTile.oppositeBorder(".#.#.###..")).isEqualTo("......#..#");

        assertThat(photo.multiplyCornerTiles()).isEqualTo(20899048083289L);
    }

    @Test
    void testFilePhoto() {
        var input = Util.readFileAsStringList("day20.txt");
        var photo = new Photo(input);

        assertThat(photo.getTiles()).hasSize(1728 / 12);
        assertThat(photo.getSize()).isEqualTo(12);
        assertThat(photo.getTiles()).contains(new Tile(3919), new Tile(1447), new Tile(2897));
        assertThat(photo.multiplyCornerTiles()).isEqualTo(64802175715999L);
    }
}