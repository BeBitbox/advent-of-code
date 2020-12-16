package be.bitbox.adventofcode.y2020.day8;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Gameboy {

    private final List<Command> commands;

    public Gameboy(List<String> inputLines) {
        this.commands = inputLines.stream()
                .map(Command::new)
                .collect(Collectors.toList());

    }

    public ProgramResult runInfiniteLoopProgram() {
        Set<Integer> visitedCommands = new HashSet<>();
        long accumulator = 0;
        int currentIndex = 0;
        boolean endOfProgram = false;
        boolean infiniteLoopDetected = false;

        while (!(endOfProgram || infiniteLoopDetected)) {
            visitedCommands.add(currentIndex);

            var command = commands.get(currentIndex);
            switch (command.getOperation()) {
                case JUMP:
                    currentIndex += command.getPosition();
                    break;
                case ACCUMULATOR:
                    accumulator += command.getPosition();
                    currentIndex++;
                    break;
                case NO_OPERATION:
                    currentIndex++;
                    break;
            }

            if (currentIndex >= commands.size()) {
                endOfProgram = true;
            } else if (visitedCommands.contains(currentIndex)) {
                infiniteLoopDetected = true;
            }
        }

        return new ProgramResult(accumulator, infiniteLoopDetected);
    }

    public Command getCommandAt(int position) {
        return commands.get(position);
    }

    public long runDebuggedProgram() {
        var foundResult = 0L;
        for (Command command : commands) {
            if (command.getOperation() == Operation.NO_OPERATION) {
                command.setOperation(Operation.JUMP);
                var programResult = runInfiniteLoopProgram();
                if (!programResult.infinite) {
                    foundResult = programResult.result;
                }
                command.setOperation(Operation.NO_OPERATION);
            } else if (command.getOperation() == Operation.JUMP) {
                command.setOperation(Operation.NO_OPERATION);
                var programResult = runInfiniteLoopProgram();
                if (!programResult.infinite) {
                    foundResult = programResult.result;
                }
                command.setOperation(Operation.JUMP);
            }
        }
        return foundResult;
    }

    public static class ProgramResult {
        private final Long result;
        private final boolean infinite;

        public ProgramResult(Long result, boolean infinite) {
            this.result = result;
            this.infinite = infinite;
        }

        public Long getResult() {
            return result;
        }

        public boolean isInfinite() {
            return infinite;
        }
    }
}
