package be.bitbox.adventofcode.y2023.day15;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HashTest {

    @Test
    void calculate() {
        assertThat(new Hash("HASH").calculate()).isEqualTo(52);
        assertThat(new Hash("rn=1").calculate()).isEqualTo(30);
        assertThat(new Hash("cm-").calculate()).isEqualTo(253);
        assertThat(new Hash("pc-").calculate()).isEqualTo(48);
        assertThat(new Hash("rn").calculate()).isEqualTo(0);
        assertThat(new Hash("cm").calculate()).isEqualTo(0);
    }
}