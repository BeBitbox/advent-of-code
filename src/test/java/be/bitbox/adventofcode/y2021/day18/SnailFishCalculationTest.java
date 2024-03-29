package be.bitbox.adventofcode.y2021.day18;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class SnailFishCalculationTest {

    @Test
    void addition_1() {
        var snailFishCalculation = new SnailFishCalculation(List.of(
                "[1,1]",
                "[2,2]",
                "[3,3]",
                "[4,4]"
        ));

        assertThat(snailFishCalculation.addition().toString()).isEqualTo("[[[[1,1],[2,2]],[3,3]],[4,4]]");
    }

    @Test
    void addition_2() {
        var snailFishCalculation = new SnailFishCalculation(List.of(
                "[1,1]",
                "[2,2]",
                "[3,3]",
                "[4,4]",
                "[5,5]"
        ));

        assertThat(snailFishCalculation.addition().toString()).isEqualTo("[[[[3,0],[5,3]],[4,4]],[5,5]]");
    }

    @Test
    void addition_3() {
        var snailFishCalculation = new SnailFishCalculation(List.of(
                "[1,1]",
                "[2,2]",
                "[3,3]",
                "[4,4]",
                "[5,5]",
                "[6,6]"
        ));

        assertThat(snailFishCalculation.addition().toString()).isEqualTo("[[[[5,0],[7,4]],[5,5]],[6,6]]");
    }

    @Test
    void addition_4() {
        var snailFishCalculation = new SnailFishCalculation(List.of(
                "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]            ",
                "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]                    ",
                "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]            ",
                "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
                "[7,[5,[[3,8],[1,4]]]]                                ",
                "[[2,[2,2]],[8,[8,1]]]                                ",
                "[2,9]                                                ",
                "[1,[[[9,3],9],[[9,0],[0,7]]]]                        ",
                "[[[5,[7,4]],7],1]                                    ",
                "[[[[4,2],2],6],[8,7]]                                "
        ));

        assertThat(snailFishCalculation.addition().toString()).isEqualTo("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]");
    }

    @Test
    void magnitude() {
        var snailFishCalculation = new SnailFishCalculation(List.of(
                "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
                "[[[5,[2,8]],4],[5,[[9,9],0]]]                    ",
                "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]                ",
                "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]                ",
                "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]            ",
                "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]            ",
                "[[[[5,4],[7,7]],8],[[8,3],8]]                    ",
                "[[9,3],[[9,9],[6,[4,9]]]]                        ",
                "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]            ",
                "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]        "
        ));

        var addition = snailFishCalculation.addition();
        assertThat(addition.toString()).isEqualTo("[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]");
        assertThat(addition.getPair().magnitude()).isEqualTo(4140L);
    }

    @Test
    void maxMagnitude() {
        var snailFishCalculation = new SnailFishCalculation(List.of(
                "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
                "[[[5,[2,8]],4],[5,[[9,9],0]]]                    ",
                "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]                ",
                "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]                ",
                "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]            ",
                "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]            ",
                "[[[[5,4],[7,7]],8],[[8,3],8]]                    ",
                "[[9,3],[[9,9],[6,[4,9]]]]                        ",
                "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]            ",
                "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]        "
        ));

        assertThat(snailFishCalculation.biggestMagnitude()).isEqualTo(3993L);
    }

    @Test
    void magnitude_big() {
        var snailFishCalculation = new SnailFishCalculation(Util.readFileAsStringList("21_day18.txt"));

        var addition = snailFishCalculation.addition();
        assertThat(addition.getPair().magnitude()).isEqualTo(3305L);

        assertThat(snailFishCalculation.biggestMagnitude()).isEqualTo(4563L);
    }
}