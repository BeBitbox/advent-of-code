package be.bitbox.adventofcode.y2021.day12;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CavernPathsTest {

    @Test
    void determinePaths_1() {
        var cavernPaths = new CavernPaths(List.of(
                "start-A",
                "start-b",
                "A-c    ",
                "A-b    ",
                "b-d    ",
                "A-end  ",
                "b-end  "
        ));

        assertThat(cavernPaths.determinePaths(false)).containsExactlyInAnyOrder(
                "start,A,b,A,c,A,end",
                "start,A,b,A,end",
                "start,A,b,end",
                "start,A,c,A,b,A,end",
                "start,A,c,A,b,end",
                "start,A,c,A,end",
                "start,A,end",
                "start,b,A,c,A,end",
                "start,b,A,end",
                "start,b,end");
        assertThat(cavernPaths.determinePaths(true)).containsExactlyInAnyOrder(
                "start,A,b,A,b,A,c,A,end",
                "start,A,b,A,b,A,end",
                "start,A,b,A,b,end",
                "start,A,b,A,c,A,b,A,end",
                "start,A,b,A,c,A,b,end",
                "start,A,b,A,c,A,c,A,end",
                "start,A,b,A,c,A,end",
                "start,A,b,A,end",
                "start,A,b,d,b,A,c,A,end",
                "start,A,b,d,b,A,end",
                "start,A,b,d,b,end",
                "start,A,b,end",
                "start,A,c,A,b,A,b,A,end",
                "start,A,c,A,b,A,b,end",
                "start,A,c,A,b,A,c,A,end",
                "start,A,c,A,b,A,end",
                "start,A,c,A,b,d,b,A,end",
                "start,A,c,A,b,d,b,end",
                "start,A,c,A,b,end",
                "start,A,c,A,c,A,b,A,end",
                "start,A,c,A,c,A,b,end",
                "start,A,c,A,c,A,end",
                "start,A,c,A,end",
                "start,A,end",
                "start,b,A,b,A,c,A,end",
                "start,b,A,b,A,end",
                "start,b,A,b,end",
                "start,b,A,c,A,b,A,end",
                "start,b,A,c,A,b,end",
                "start,b,A,c,A,c,A,end",
                "start,b,A,c,A,end",
                "start,b,A,end",
                "start,b,d,b,A,c,A,end",
                "start,b,d,b,A,end",
                "start,b,d,b,end",
                "start,b,end"
        );
    }

    @Test
    void determinePaths_2() {
        var cavernPaths = new CavernPaths(List.of(
                "dc-end  ",
                "HN-start",
                "start-kj",
                "dc-start",
                "dc-HN   ",
                "LN-dc   ",
                "HN-end  ",
                "kj-sa   ",
                "kj-HN   ",
                "kj-dc   "
        ));

        assertThat(cavernPaths.determinePaths(false)).containsExactlyInAnyOrder(
                "start,HN,dc,HN,end",
                "start,HN,dc,HN,kj,HN,end",
                "start,HN,dc,end",
                "start,HN,dc,kj,HN,end",
                "start,HN,end",
                "start,HN,kj,HN,dc,HN,end",
                "start,HN,kj,HN,dc,end",
                "start,HN,kj,HN,end",
                "start,HN,kj,dc,HN,end",
                "start,HN,kj,dc,end",
                "start,dc,HN,end",
                "start,dc,HN,kj,HN,end",
                "start,dc,end",
                "start,dc,kj,HN,end",
                "start,kj,HN,dc,HN,end",
                "start,kj,HN,dc,end",
                "start,kj,HN,end",
                "start,kj,dc,HN,end",
                "start,kj,dc,end"
        );
        assertThat(cavernPaths.determinePaths(true)).hasSize(103);
    }

    @Test
    void determinePaths_3() {
        var cavernPaths = new CavernPaths(List.of(
                "fs-end  ",
                "he-DX   ",
                "fs-he   ",
                "start-DX",
                "pj-DX   ",
                "end-zg  ",
                "zg-sl   ",
                "zg-pj   ",
                "pj-he   ",
                "RW-he   ",
                "fs-DX   ",
                "pj-RW   ",
                "zg-RW   ",
                "start-pj",
                "he-WI   ",
                "zg-he   ",
                "pj-fs   ",
                "start-RW"
        ));

        assertThat(cavernPaths.determinePaths(false).size()).isEqualTo(226);
        assertThat(cavernPaths.determinePaths(true)).hasSize(3509);
    }

    @Test
    void determinePaths_Big() {
        var cavernPaths = new CavernPaths(Util.readFileAsStringList("21_day12.txt"));

        assertThat(cavernPaths.determinePaths(false).size()).isEqualTo(3713);
        assertThat(cavernPaths.determinePaths(true)).hasSize(91292);
    }
}