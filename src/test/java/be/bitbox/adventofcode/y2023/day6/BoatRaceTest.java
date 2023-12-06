package be.bitbox.adventofcode.y2023.day6;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BoatRaceTest {

    @Test
    void testExamplePart1() {
        var boatRace = new BoatRace(List.of(
                "Time:      7  15   30",
                "Distance:  9  40  200"
        ), false);

        assertThat(boatRace.getRecordList()).hasSize(3);
        var firstRecord = boatRace.getRecordList().get(0);
        assertThat(firstRecord.time()).isEqualTo(7);
        assertThat(firstRecord.distance()).isEqualTo(9);
        assertThat(firstRecord.possibleWins()).isEqualTo(4);
        assertThat(boatRace.getRecordList().get(1).possibleWins()).isEqualTo(8);
        assertThat(boatRace.getRecordList().get(2).possibleWins()).isEqualTo(9);
        assertThat(boatRace.possibleWins()).isEqualTo(288);
    }

    @Test
    void testExamplePart2() {
        var boatRace = new BoatRace(List.of(
                "Time:      7  15   30",
                "Distance:  9  40  200"
        ), true);

        assertThat(boatRace.getRecordList()).hasSize(1);
        var firstRecord = boatRace.getRecordList().get(0);
        assertThat(firstRecord.time()).isEqualTo(71530);
        assertThat(firstRecord.distance()).isEqualTo(940200);
        assertThat(firstRecord.possibleWins()).isEqualTo(71503);
        assertThat(boatRace.possibleWins()).isEqualTo(71503);
    }

    @Test
    void testFilePart1() {
        var boatRace = new BoatRace(Util.readFileAsStringList("23_day6.txt"), false);

        assertThat(boatRace.getRecordList()).hasSize(4);
        assertThat(boatRace.possibleWins()).isEqualTo(1660968);
    }

    @Test
    void testFilePart2() {
        var boatRace = new BoatRace(Util.readFileAsStringList("23_day6.txt"), true);

        assertThat(boatRace.getRecordList()).hasSize(1);
        assertThat(boatRace.possibleWins()).isEqualTo(26499773L);
    }
}