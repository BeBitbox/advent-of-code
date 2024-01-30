package be.bitbox.adventofcode.y2023.day19;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MachinePartTest {

    @Test
    void parsing() {
        var machinePart = new MachinePart("{x=1679,m=44,a=2067,s=496}");

        assertThat(machinePart.getX()).isEqualTo(1679);
        assertThat(machinePart.getM()).isEqualTo(44);
        assertThat(machinePart.getA()).isEqualTo(2067);
        assertThat(machinePart.getS()).isEqualTo(496);
    }
}