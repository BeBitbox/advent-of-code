package be.bitbox.adventofcode.y2023.day10;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PipeMazeTest {

    @Test
    void testExample1() {
        var pipeMaze = new PipeMaze(List.of(
                ".....",
                ".S-7.",
                ".|.|.",
                ".L-J.",
                "....."
        ));

        assertThat(pipeMaze.loopSize()).isEqualTo(4);
        assertThat(pipeMaze.insideLoopers()).isEqualTo(1);
    }

    @Test
    void testExample2() {
        var pipeMaze = new PipeMaze(List.of(
                "..F7.",
                ".FJ|.",
                "SJ.L7",
                "|F--J",
                "LJ..."
        ));

        assertThat(pipeMaze.loopSize()).isEqualTo(8);
        assertThat(pipeMaze.insideLoopers()).isEqualTo(1);
    }

    @Test
    void testExample3() {
        var pipeMaze = new PipeMaze(List.of(
                "...........",
                ".S-------7.",
                ".|F-----7|.",
                ".||.....||.",
                ".||.....||.",
                ".|L-7.F-J|.",
                ".|..|.|..|.",
                ".L--J.L--J.",
                "..........."
        ));

        assertThat(pipeMaze.loopSize()).isEqualTo(23);
        assertThat(pipeMaze.insideLoopers()).isEqualTo(4);
    }

    @Test
    void testExample4() {
        var pipeMaze = new PipeMaze(List.of(
                "..........",
                ".S------7.",
                ".|F----7|.",
                ".||....||.",
                ".||....||.",
                ".|L-7F-J|.",
                ".|..||..|.",
                ".L--JL--J.",
                ".........."
        ));

        assertThat(pipeMaze.loopSize()).isEqualTo(22);
        assertThat(pipeMaze.insideLoopers()).isEqualTo(4);
    }
    
    @Test
    void testExample5() {
        var pipeMaze = new PipeMaze(List.of(
                ".F----7F7F7F7F-7....",
                    ".|F--7||||||||FJ....",
                    ".||.FJ||||||||L7....",
                    "FJL7L7LJLJ||LJ.L-7..",
                    "L--J.L7...LJS7F-7L7.",
                    "....F-J..F7FJ|L7L7L7",
                    "....L7.F7||L7|.L7L7|",
                    ".....|FJLJ|FJ|F7|.LJ",
                    "....FJL-7.||.||||...",
                    "....L---J.LJ.LJLJ..."
        ));

        assertThat(pipeMaze.loopSize()).isEqualTo(70);
        assertThat(pipeMaze.insideLoopers()).isEqualTo(8);
    }


    @Test
    void testFile() {
        var pipeMaze = new PipeMaze(Util.readFileAsStringList("23_day10.txt"));

        assertThat(pipeMaze.loopSize()).isEqualTo(6838);
        assertThat(pipeMaze.insideLoopers()).isEqualTo(515);
    }
}