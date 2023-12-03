package be.bitbox.adventofcode.y2023.day1;

import be.bitbox.adventofcode.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Trebuchet {

    private final List<String> inputList;
    private final static Map<String, String> CONVERSION_TABLE = Map.of(
            "one", "one1one",
            "two", "two2two",
            "three", "three3three",
            "four", "four4four",
            "five", "five5five",
            "six", "six6six",
            "seven", "seven7seven",
            "eight", "eight8eight",
            "nine", "nine9nine"
    );

    public Trebuchet(List<String> input) {
        inputList = input;
    }

    public int getCalibrationOnlyDigits() {
        return getCalibrationOnlyDigits(inputList);
    }

    public int getCalibration() {
        List<String> transformedList = inputList.stream()
                .map(s -> {
                    AtomicReference<String> temp = new AtomicReference<>(s);
                    CONVERSION_TABLE.forEach((key, value) -> temp.set(temp.get().replaceAll(key, value)));
                    return temp.get();
                })
                .toList();
        return getCalibrationOnlyDigits(transformedList);
    }

    private int getCalibrationOnlyDigits(List<String> inputList) {
        return inputList.stream()
                .map(s -> {
                    List<Integer> digits = StringUtils.onlyDigits(s);
                    return String.valueOf(digits.getFirst()) + digits.getLast();
                })
                .map(Integer::parseInt)
                .reduce(Integer::sum)
                .orElseThrow();
    }
}
