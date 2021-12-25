package be.bitbox.adventofcode.y2021.day24;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ALUTest {

    @Test
    void negateNumber() {
        var alu = new ALU(List.of("inp x", "mul x -1"));

        alu.processInput("8");
        assertThat(alu.getX()).isEqualTo(-8);
    }

    @Test
    void isThreeTimesLarger() {
        var alu = new ALU(List.of("inp z", "inp x", "mul z 3", "eql z x"));

        alu.processInput("28");
        assertThat(alu.getZ()).isEqualTo(0);

        alu.processInput("26");
        assertThat(alu.getZ()).isEqualTo(1);

        alu.processInput("92");
        assertThat(alu.getZ()).isEqualTo(0);
    }

    @Test
    void toBinary() {
        var alu = new ALU(List.of(
                "inp w",
                "add z w",
                "mod z 2",
                "div w 2",
                "add y w",
                "mod y 2",
                "div w 2",
                "add x w",
                "mod x 2",
                "div w 2",
                "mod w 2"
        ));

        alu.processInput("0");
        assertThat(alu.getW()).isEqualTo(0);
        assertThat(alu.getX()).isEqualTo(0);
        assertThat(alu.getY()).isEqualTo(0);
        assertThat(alu.getZ()).isEqualTo(0);

        alu.processInput("4");
        assertThat(alu.getW()).isEqualTo(0);
        assertThat(alu.getX()).isEqualTo(1);
        assertThat(alu.getY()).isEqualTo(0);
        assertThat(alu.getZ()).isEqualTo(0);

        alu.processInput("9");
        assertThat(alu.getW()).isEqualTo(1);
        assertThat(alu.getX()).isEqualTo(0);
        assertThat(alu.getY()).isEqualTo(0);
        assertThat(alu.getZ()).isEqualTo(1);

        alu.processInput("7");
        assertThat(alu.getW()).isEqualTo(0);
        assertThat(alu.getX()).isEqualTo(1);
        assertThat(alu.getY()).isEqualTo(1);
        assertThat(alu.getZ()).isEqualTo(1);
    }
}