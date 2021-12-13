package be.bitbox.adventofcode.y2021.day13;

import be.bitbox.adventofcode.Tuple;
import be.bitbox.adventofcode.Util;
import be.bitbox.adventofcode.y2021.day13.TransparentPaper.Direction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TransparentPaperTest {

    @Test
    void small() {
        var transparentPaper = new TransparentPaper(List.of(
                "6,10",
                "0,14",
                "9,10",
                "0,3",
                "10,4",
                "4,11",
                "6,0",
                "6,12",
                "4,1",
                "0,13",
                "10,12",
                "3,4",
                "3,0",
                "8,4",
                "1,10",
                "2,14",
                "8,10",
                "9,0",
                "",
                "fold along y=7",
                "fold along x=5"
        ));

        assertThat(transparentPaper.getDots()).hasSize(18);
        assertThat(transparentPaper.getDots()).contains(new Tuple<>(10, 12));
        assertThat(transparentPaper.getFolds()).hasSize(2);
        assertThat(transparentPaper.getFolds()).contains(new Tuple<>(Direction.Y, 7));
        assertThat(transparentPaper.foldFirst()).isEqualTo(16);
    }

    @Test
    void big() {
        var transparentPaper = new TransparentPaper(Util.readFileAsStringList("21_day13.txt"));

        assertThat(transparentPaper.getDots()).hasSize(840);
        assertThat(transparentPaper.getFolds()).hasSize(12);
        assertThat(transparentPaper.foldFirst()).isEqualTo(96);
    }
}