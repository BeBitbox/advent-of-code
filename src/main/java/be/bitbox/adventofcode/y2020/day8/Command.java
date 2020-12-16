package be.bitbox.adventofcode.y2020.day8;

import java.util.Objects;

public class Command {

    private Operation operation;
    private final Integer position;

    public Command(Operation operation, Integer position) {
        this.operation = operation;
        this.position = position;
    }

    public Command(String line) {
        var parts = line.split(" ");
        switch (parts[0]) {
            case "acc":
                operation = Operation.ACCUMULATOR;
                break;
            case "jmp":
                operation = Operation.JUMP;
                break;
            case "nop":
                operation = Operation.NO_OPERATION;
                break;
            default:
                throw new IllegalArgumentException("unknown operation " + line);
        }
        position = Integer.parseInt(parts[1]);
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public Integer getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return operation == command.operation && Objects.equals(position, command.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, position);
    }

    @Override
    public String toString() {
        return "Command{" +
                "operation=" + operation +
                ", position=" + position +
                '}';
    }
}
