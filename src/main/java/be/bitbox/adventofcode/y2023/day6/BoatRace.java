package be.bitbox.adventofcode.y2023.day6;

import be.bitbox.adventofcode.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BoatRace {
    private final List<Record> recordList;

    public BoatRace(List<String> input, boolean part2) {
        recordList = new ArrayList<>();
        var temp1 = StringUtils.removeDoubleSpaces(input.get(0));
        var temp2 = StringUtils.removeDoubleSpaces(input.get(1));

        if (part2) {
            temp1 = temp1.replaceAll(" ", "").replaceAll(":", ": ");
            temp2 = temp2.replaceAll(" ", "").replaceAll(":", ": ");
        }

        var cleanTimes = temp1.split(" ");
        var cleanDistances = temp2.split(" ");

        for (int i = 1; i < cleanTimes.length; i++) {
            recordList.add(new Record(Long.parseLong(cleanTimes[i]), Long.parseLong(cleanDistances[i])));
        }
    }

    public long possibleWins() {
        return recordList.stream()
                .map(Record::possibleWins)
                .reduce(((integer, integer2) -> integer * integer2))
                .orElseThrow();
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public record Record(long time, long distance) {

        public long possibleWins() {
            int wins = 0;
            for (long wait = 0; wait < time; wait++) {
                long achieved = (time - wait) * wait;
                if (achieved > distance) {
                    wins++;
                }
            }
            return wins;
        }
    }
}
