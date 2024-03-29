package be.bitbox.adventofcode.y2021.day8;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DigitCounterTest {

    @Test
    void calculateNumber() {
        var digitCounter = new DigitCounter(List.of(
                "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe",
                "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc    ",
                "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg         ",
                "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb   ",
                "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea   ",
                "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb  ",
                "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe  ",
                "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef    ",
                "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb       ",
                "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce      "
        ));

        assertThat(digitCounter.calculateNumberOf_1_4_7_8()).isEqualTo(26);
        assertThat(digitCounter.sumOutputs()).isEqualTo(61229);
    }

    @Test
    void calculateNumber_big() {
        var digitCounter = new DigitCounter(Util.readFileAsStringList("21_day8.txt"));

        assertThat(digitCounter.calculateNumberOf_1_4_7_8()).isEqualTo(261);
        assertThat(digitCounter.sumOutputs()).isEqualTo(987553);
    }
}