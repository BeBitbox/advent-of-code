package be.bitbox.adventofcode.y2021.day18;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

class SnailNumberTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "[1,2]",
            "[[1,2],3]",
            "[9,[8,7]]",
            "[[1,9],[8,5]]",
            "[[[[1,2],[3,4]],[[5,6],[7,8]]],9]",
            "[[[9,[3,8]],[[0,9],6]],[[[3,7],[4,9]],3]]",
            "[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]"
    })
    void testCreation(String input) {
        assertThat(new SnailNumber(input).toString()).isEqualTo(input);
    }

    @Test
    void testAddition() {
        var snailNumber = new SnailNumber("[1,2]");
        var snailNumber2 = new SnailNumber("[[3,4],5]");

        var sum = snailNumber.add(snailNumber2);
        assertThat(sum.toString()).isEqualTo("[[1,2],[[3,4],5]]");
    }

    @Test
    void testAddition2() {
        var snailNumber = new SnailNumber("[[[[4,3],4],4],[7,[[8,4],9]]]");
        var snailNumber2 = new SnailNumber("[1,1]");

        var sum = snailNumber.add(snailNumber2);

        assertThat(sum.toString()).isEqualTo("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "[[[[[9,8],1],2],3],4]:[[[[0,9],2],3],4]",
            "[7,[6,[5,[4,[3,2]]]]]:[7,[6,[5,[7,0]]]]",
            "[[6,[5,[4,[3,2]]]],1]:[[6,[5,[7,0]]],3]",
            "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]:[[3,[2,[8,0]]],[9,[5,[7,0]]]]"
    }, delimiter = ':')
    void testReduce(String input, String expected) {
        var snailNumber = new SnailNumber(input);
        snailNumber.reduce();
        snailNumber.getPair().leafs(new LinkedList<>());
        assertThat(snailNumber.toString()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "[9,1]:29",
            "[[1,2],[[3,4],5]]:143",
            "[[[[3,0],[5,3]],[4,4]],[5,5]]:791",
            "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]:3488",
    }, delimiter = ':')
    void testMagnitude(String input, Long magnitude) {
        var snailNumber = new SnailNumber(input);
        snailNumber.reduce();
        assertThat(snailNumber.getPair().magnitude()).isEqualTo(magnitude);
    }
}