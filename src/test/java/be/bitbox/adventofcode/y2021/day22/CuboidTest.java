package be.bitbox.adventofcode.y2021.day22;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class CuboidTest {

    @Test
    void testCreation() {
        var cuboid1 = new Cuboid(new Instruction("on x=10..12,y=10..12,z=10..12"));
        var cuboid2 = new Cuboid(new Instruction("on x=11..13,y=11..13,z=11..13"));
        var cuboid3 = new Cuboid(new Instruction("off x=9..11,y=9..11,z=9..11"));

        assertThat(cuboid1.size()).isEqualTo(27L);

        var intersect = cuboid1.intersect(cuboid2);
        assertThat(cuboid2.size()).isEqualTo(27L);
        assertThat(intersect.size()).isEqualTo(27L - 19L);
        assertThat(intersect.x1).isEqualTo(11);
        assertThat(intersect.x2).isEqualTo(12);
        assertThat(intersect.y1).isEqualTo(11);
        assertThat(intersect.y2).isEqualTo(12);
        assertThat(intersect.z1).isEqualTo(11);
        assertThat(intersect.z2).isEqualTo(12);
    }
}