package be.bitbox.adventofcode.y2020.day5;

import be.bitbox.adventofcode.y2020.Util;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class SeatTest {

    @Test
    void testParsingOfSeats() {
        Seat bottomSeat = new Seat("FFFFFFFLLL");
        assertThat(bottomSeat.getRow()).isEqualTo(0);
        assertThat(bottomSeat.getColumn()).isEqualTo(0);
        assertThat(bottomSeat.getSeatId()).isEqualTo(0);

        Seat topSeat = new Seat("BBBBBBBRRR");
        assertThat(topSeat.getRow()).isEqualTo(127);
        assertThat(topSeat.getColumn()).isEqualTo(7);
        assertThat(topSeat.getSeatId()).isEqualTo(127 * 8 + 7);

        Seat seat1 = new Seat("FBFBBFFRLR");
        assertThat(seat1.getRow()).isEqualTo(44);
        assertThat(seat1.getColumn()).isEqualTo(5);
        assertThat(seat1.getSeatId()).isEqualTo(357);

        Seat seat2 = new Seat("BBFFBBFRLL");
        assertThat(seat2.getRow()).isEqualTo(102);
        assertThat(seat2.getColumn()).isEqualTo(4);
        assertThat(seat2.getSeatId()).isEqualTo(820);
    }

    @Test
    void testHighestSeatIdFromFile() {
        var maxSeatId = Util.readFileAsStringList("day5.txt")
                .stream()
                .map(Seat::new)
                .map(Seat::getSeatId)
                .max(Integer::compareTo)
                .orElse(0);
        assertThat(maxSeatId).isEqualTo(813);
    }


    @Test
    void testEmptySeatFromFile() {
        var sortedIds = Util.readFileAsStringList("day5.txt")
                .stream()
                .map(Seat::new)
                .map(Seat::getSeatId)
                .sorted()
                .collect(Collectors.toList());

        int previousId = Integer.MIN_VALUE;
        int missingSeat = Integer.MIN_VALUE;
        for (Integer current : sortedIds) {
            if (current == previousId + 2) {
                missingSeat = previousId + 1;
            }
            previousId = current;
        }
        assertThat(missingSeat).isEqualTo(612);
    }
}