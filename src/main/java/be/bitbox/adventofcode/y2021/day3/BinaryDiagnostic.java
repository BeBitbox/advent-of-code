package be.bitbox.adventofcode.y2021.day3;

import java.util.Arrays;
import java.util.List;

public class BinaryDiagnostic {
    private final List<String> inputList;

    public BinaryDiagnostic(List<String> inputList) {
        this.inputList = inputList;
    }

    public int calculatePart1() {
        var size = inputList.get(0).length();

        int[] numberOfOnes = new int[size];

        for (String input : inputList) {
            for (int i = 0; i < size; i++) {
                if (input.charAt(i) == '1') {
                    numberOfOnes[i] = numberOfOnes[i] + 1;
                }
            }
        }

        StringBuilder epsilon = new StringBuilder();
        StringBuilder gamma = new StringBuilder();

        for (int i = 0; i < size; i++) {
            if (numberOfOnes[i] > inputList.size() / 2) {
                epsilon.append("0");
                gamma.append("1");
            } else {
                epsilon.append("1");
                gamma.append("0");
            }
        }

        int epsilonDecimal = Integer.parseInt(epsilon.toString(), 2);
        int gammaDecimal = Integer.parseInt(gamma.toString(), 2);
        return epsilonDecimal * gammaDecimal;
    }

    public int calculatePart2() {
        var size = inputList.get(0).length();

        int[] oxygonKeepers = new int[inputList.size()];
        int[] scrubberKeepers = new int[inputList.size()];
        Arrays.fill(oxygonKeepers, 1);
        Arrays.fill(scrubberKeepers, 1);

        int index = 0;
        while (index < size) {
            var oxygenAction = determineAction(index, oxygonKeepers);
            var scrubberAction = determineAction(index, scrubberKeepers);
            for (int i = 0; i < inputList.size(); i++) {
                if (!hasOnlyOneLeft(oxygonKeepers) && oxygonKeepers[i] == 1) {
                    if (oxygenAction == '1') {
                        if (inputList.get(i).charAt(index) == '0') {
                            oxygonKeepers[i] = 0;
                        }
                    } else if (oxygenAction == '=') {
                        if (inputList.get(i).charAt(index) == '0') {
                            oxygonKeepers[i] = 0;
                        }
                    } else {
                        if (inputList.get(i).charAt(index) == '1') {
                            oxygonKeepers[i] = 0;
                        }
                    }
                }
                if (!hasOnlyOneLeft(scrubberKeepers) && scrubberKeepers[i] == 1) {
                    if (scrubberAction == '1') {
                        if (inputList.get(i).charAt(index) == '1') {
                            scrubberKeepers[i] = 0;
                        }
                    } else if (scrubberAction == '=') {
                        if (inputList.get(i).charAt(index) == '1') {
                            scrubberKeepers[i] = 0;
                        }
                    } else {
                        if (inputList.get(i).charAt(index) == '0') {
                            scrubberKeepers[i] = 0;
                        }
                    }
                }
            }
            index++;
        }

        int oxygen = Integer.parseInt(inputList.get(getTheOneIndex(oxygonKeepers)), 2);
        var scrubbers = Integer.parseInt(inputList.get(getTheOneIndex(scrubberKeepers)), 2);
        return oxygen * scrubbers;
    }

    private char determineAction(int index, int[] keepers) {
        double numberOfOnes = 0;
        double numberOfKeepers = 0;
        for (int i = 0; i < inputList.size(); i++) {
            if (keepers[i] == 1) {
                numberOfKeepers++;
                if (inputList.get(i).charAt(index) == '1') {
                    numberOfOnes++;
                }
            }
        }
        char action;
        if (numberOfOnes > numberOfKeepers / 2) {
            action = '1';
        } else if (numberOfOnes == numberOfKeepers / 2) {
            action = '=';
        } else {
            action = '0';
        }
        return action;
    }

    private boolean hasOnlyOneLeft(int[] array) {
        int ones = 0;
        for (int i : array) {
            if (i == 1) {
                ones++;
            }
        }
        return ones == 1;
    }

    private int getTheOneIndex(int[] array) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                if (index >= 0) {
                    System.err.println("unexpected");
                } else {
                    index = i;
                }
            }
        }
        return index;
    }
}
