package be.bitbox.adventofcode.y2023.day12;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HotSpringTest {

    @Test
    void testSimple1() {
        var hotSpring = new HotSpring("???.### 1,1,3");
        var hotSpring2 = new HotSpring("???.### 1,1,3", 5);

        assertThat(hotSpring.fillInBlanks()).isEqualTo(1);
        assertThat(hotSpring2.fillInBlanks()).isEqualTo(1);
    }

    @Test
    void testSimple2() {
        var hotSpring = new HotSpring(".??..??...?##. 1,1,3");
        var hotSpring2 = new HotSpring(".??..??...?##. 1,1,3", 5);

        assertThat(hotSpring.fillInBlanks()).isEqualTo(4);
        assertThat(hotSpring2.fillInBlanks()).isEqualTo(16384);
    }

    @Test
    void testSimple3() {
        var hotSpring = new HotSpring("?#?#?#?#?#?#?#? 1,3,1,6");

        assertThat(hotSpring.fillInBlanks()).isEqualTo(1);
    }

    @Test
    void testSimple4() {
        var hotSpring = new HotSpring("????.#...#... 4,1,1");

        assertThat(hotSpring.fillInBlanks()).isEqualTo(1);
    }

    @Test
    void testSimple5() {
        var hotSpring = new HotSpring("????.######..#####. 1,6,5");

        assertThat(hotSpring.fillInBlanks()).isEqualTo(4);
    }

    @Test
    void testSimple6() {
        var hotSpring = new HotSpring("?###???????? 3,2,1");

        assertThat(hotSpring.fillInBlanks()).isEqualTo(10);
    }

    @Test
    void testCrazy1() {
        var hotSpring = new HotSpring(".# 1");

        assertThat(hotSpring.fillInBlanks()).isEqualTo(1);
    }

    @Test
    void testCrazy2() {
        var hotSpring = new HotSpring("?.?#?#?#?#??.???.#?# 1,9,1,1,1,1", 5);

        assertThat(hotSpring.fillInBlanks()).isEqualTo(32L);
    }
}