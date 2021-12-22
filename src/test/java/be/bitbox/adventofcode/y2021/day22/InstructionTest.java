package be.bitbox.adventofcode.y2021.day22;

import be.bitbox.adventofcode.Interval;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InstructionTest {

    @Test
    void testCreation_1() {
        var instruction = new Instruction("on x=11..13,y=1..9,z=110..130");

        assertThat(instruction.isOn()).isTrue();
        assertThat(instruction.getxRange()).isEqualTo(new Interval(11, 13));
        assertThat(instruction.getyRange()).isEqualTo(new Interval(1, 9));
        assertThat(instruction.getzRange()).isEqualTo(new Interval(110, 130));
    }
}