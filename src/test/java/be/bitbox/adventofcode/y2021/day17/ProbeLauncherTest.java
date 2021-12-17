package be.bitbox.adventofcode.y2021.day17;

import be.bitbox.adventofcode.Interval;
import be.bitbox.adventofcode.Tuple;
import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProbeLauncherTest {

    @Test
    void example() {
        var probeLauncher = new ProbeLauncher("target area: x=20..30, y=-10..-5");

        assertThat(probeLauncher.getxRange()).isEqualTo(new Interval(20, 30));
        assertThat(probeLauncher.getyRange()).isEqualTo(new Interval(-10, -5));
        var trajectory = probeLauncher.createTrajectory(new Tuple<>(7, 2));
        assertThat(trajectory).containsExactly(
                new Tuple<>(0, 0),
                new Tuple<>(7, 2),
                new Tuple<>(13, 3),
                new Tuple<>(18, 3),
                new Tuple<>(22, 2),
                new Tuple<>(25, 0),
                new Tuple<>(27, -3),
                new Tuple<>(28, -7)
        );
        assertThat(probeLauncher.insideOceanTrench(trajectory)).isTrue();

        var trajectory2 = probeLauncher.createTrajectory(new Tuple<>(6, 2));
        assertThat(probeLauncher.insideOceanTrench(trajectory2)).isTrue();

        var trajectory3 = probeLauncher.createTrajectory(new Tuple<>(9, 0));
        assertThat(trajectory3).containsExactly(
                new Tuple<>(0, 0),
                new Tuple<>(9, 0),
                new Tuple<>(17, -1),
                new Tuple<>(24, -3),
                new Tuple<>(30, -6)
        );
        assertThat(probeLauncher.insideOceanTrench(trajectory3)).isTrue();

        var trajectory4 = probeLauncher.createTrajectory(new Tuple<>(17, -4));
        assertThat(probeLauncher.insideOceanTrench(trajectory4)).isFalse();
        var calculation = probeLauncher.calculateHightestAndTotal();
        assertThat(calculation.x).isEqualTo(45);
        assertThat(calculation.y).isEqualTo(112);
    }

    @Test
    void part1Big() {
        var probeLauncher = new ProbeLauncher(Util.readFileAsString("21_day17.txt"));

        var calculation = probeLauncher.calculateHightestAndTotal();
        assertThat(calculation.x).isEqualTo(10011);
        assertThat(calculation.y).isEqualTo(2994);
    }
}