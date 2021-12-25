package be.bitbox.adventofcode.y2021.day24;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ALU {
    private final List<String> instructions;
    private int w;
    private int x;
    private int y;
    private int z;

    public ALU(List<String> instructions) {
        this.instructions = instructions;
    }

    public void processInput(String input) {
        x = 0;
        y = 0;
        z = 0;
        w = 0;
        var iterator = instructions.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            var instruction = iterator.next();

            if (instruction.startsWith("inp")) {
                inputVariable(input, index, instruction);
                index++;
            } else if (instruction.startsWith("mul")) {
                var split = instruction.split(" ");
                Character character = split[1].charAt(0);
                Supplier<Integer> supplier = getValue(split[2]);
                setValue(character).accept(getValue(character).get() * supplier.get());
            } else if (instruction.startsWith("eql")) {
                var split = instruction.split(" ");
                Character character = split[1].charAt(0);
                Supplier<Integer> supplier = getValue(split[2]);
                setValue(character).accept(Objects.equals(getValue(character).get(), supplier.get()) ? 1 : 0);
            } else if (instruction.startsWith("add")) {
                var split = instruction.split(" ");
                Character character = split[1].charAt(0);
                Supplier<Integer> supplier = getValue(split[2]);
                setValue(character).accept(getValue(character).get() + supplier.get());
            } else if (instruction.startsWith("div")) {
                var split = instruction.split(" ");
                Character character = split[1].charAt(0);
                Supplier<Integer> supplier = getValue(split[2]);
                setValue(character).accept(getValue(character).get() / supplier.get());
            } else if (instruction.startsWith("mod")) {
                var split = instruction.split(" ");
                Character character = split[1].charAt(0);
                Supplier<Integer> supplier = getValue(split[2]);
                setValue(character).accept(getValue(character).get() % supplier.get());
            }
        }
    }

    private Supplier<Integer> getValue(String input) {
        var character = input.charAt(0);
        if (character == 'x' || character == 'y' || character == 'z' || character == 'w') {
            return getValue(character);
        } else {
            return () -> Integer.parseInt(input);
        }
    }

    private void inputVariable(String input, int index, String instruction) {
        var inputChar = instruction.charAt(4);
        var numberInput = input.charAt(index) - '0';
        setValue(inputChar).accept(numberInput);
    }

    private Consumer<Integer> setValue(Character inputChar) {
        switch (inputChar) {
            case 'x':
                return i -> x = i;
            case 'y':
                return i -> y = i;
            case 'z':
                return i -> z = i;
            case 'w':
                return i -> w = i;
        }
        throw new IllegalArgumentException();
    }

    private Supplier<Integer> getValue(Character inputChar) {
        switch (inputChar) {
            case 'x':
                return () -> x;
            case 'y':
                return () -> y;
            case 'z':
                return () -> z;
            case 'w':
                return () -> w;
        }
        throw new IllegalArgumentException();
    }

    public int getW() {
        return w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
