package be.bitbox.adventofcode.y2020.day8;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameboyTest {

    @Test
    void testGameboyCommandRetrieval() {
        var gameboy = new Gameboy(getExampleProgram());

        assertThat(gameboy.getCommandAt(0)).isEqualTo(new Command(Operation.NO_OPERATION, 0));
        assertThat(gameboy.getCommandAt(1)).isEqualTo(new Command(Operation.ACCUMULATOR, 1));
        assertThat(gameboy.getCommandAt(2)).isEqualTo(new Command(Operation.JUMP, 4));
        assertThat(gameboy.getCommandAt(4)).isEqualTo(new Command(Operation.JUMP, -3));
    }

    @Test
    void testSimpleGameboyRun() {
        var gameboy = new Gameboy(List.of("acc +1 ", "jmp -1"));

        var programResult = gameboy.runInfiniteLoopProgram();
        assertThat(programResult.getResult()).isEqualTo(1);
        assertThat(programResult.isInfinite()).isTrue();
        assertThat(gameboy.runDebuggedProgram()).isEqualTo(1);
    }

    @Test
    void testGameboyRun() {
        var gameboy = new Gameboy(getExampleProgram());

        var programResult = gameboy.runInfiniteLoopProgram();
        assertThat(programResult.getResult()).isEqualTo(5);
        assertThat(programResult.isInfinite()).isTrue();
        assertThat(gameboy.runDebuggedProgram()).isEqualTo(8);
    }

    @Test
    void testGameboyRunFile() {
        var gameboy = new Gameboy(Util.readFileAsStringList("day8.txt"));

        var programResult = gameboy.runInfiniteLoopProgram();
        assertThat(programResult.getResult()).isEqualTo(1818);
        assertThat(programResult.isInfinite()).isTrue();
        assertThat(gameboy.runDebuggedProgram()).isEqualTo(631);
    }

    private List<String> getExampleProgram() {
        return List.of(
                "nop +0 ",
                "acc +1 ",
                "jmp +4 ",
                "acc +3 ",
                "jmp -3 ",
                "acc -99",
                "acc +1 ",
                "jmp -4 ",
                "acc +6 "
        );
    }
}