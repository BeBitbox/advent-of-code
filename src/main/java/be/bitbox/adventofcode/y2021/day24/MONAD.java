package be.bitbox.adventofcode.y2021.day24;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.IntStream;

public class MONAD {

    private final ALU alu;
    private long currentLowest;

    public MONAD(List<String> input) {
        this.alu = new ALU(input);
    }

    public String getHighestMONAD() {
        //long modelNumber = 77_777_777_777_777L - 1721096197;

        long modelNumber = 77_777_777_777_777L;
        long maximum = 99_999_999_999_999L;
        long minimum = 11_111_111_111_111L;

        currentLowest = 11_111_111_111_111L;
        var number = String.format("%014d", currentLowest);
        while (true) {
            alu.processInput(number);
            if (alu.getZ() == 0) {
                return number;
            } else {
                currentLowest++;
                number = String.format("%014d", currentLowest);
                if (number.contains("0")) {
                    number = number.replaceAll("0", "1");
                    currentLowest = Long.parseLong(number);
                }
            }
            /*modelNumber = currentLowest - (long)(Math.random() * (currentLowest - minimum));
            var number = String.format("%014d", modelNumber);

            System.out.println(number);
            for (int i = 13; i >= 0; i--) {
                int finalI = i;
                String finalNumber = number;

                number = IntStream.rangeClosed(1, 9)
                        .mapToObj(j -> setCharAt((char) (j + '0'), finalI, finalNumber))
                        .min(Comparator.comparingLong(aluToZ()))
                        .orElseThrow();
                alu.processInput(number);
                if (alu.getZ() == 0 ) {
                    var current = Long.parseLong(number);
                    if (current < currentLowest) {
                        currentLowest = current;
                        System.out.println("current " + currentLowest);
                    }
                } else {
                    //System.out.println(number + " " + alu.getW() + " " + alu.getX()+ " " + alu.getY() + " " + alu.getZ());
                }
            }*/
        }
    }

    private ToLongFunction<String> aluToZ() {
        return value -> {
            alu.processInput(value);

            return alu.getW() + alu.getX() + alu.getY() + alu.getZ();
        };
    }

    private String setCharAt(char c, int index, String original) {
        var chars = original.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
}
