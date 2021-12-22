package be.bitbox.adventofcode.y2021.day22;

import be.bitbox.adventofcode.Interval;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReactorReboot {
    private int[][][] reactor;
    private final List<Instruction> instructions;
    private int currentStep;
    private Interval interval = new Interval(0, 100);
    private List<Cuboid> cuboids = new ArrayList<>();

    public ReactorReboot(List<String> input) {
        instructions = input.stream()
                .map(Instruction::new)
                .collect(toList());
        reactor = new int[101][101][101];
        currentStep = 0;
    }

    public void executeAllStep() {
        while (currentStep < instructions.size()) {
            executeStep();
        }
    }

    public void executeStepPart2(Instruction instruction) {
        var cuboid = new Cuboid(instruction);

        for (Cuboid onCuboid : cuboids) {
            var intersect = onCuboid.intersect(cuboid);
            if (intersect != null) {
                if (instruction.isOn()) {
                    cuboid.addDetached(intersect);
                } else {
                    onCuboid.addDetached(intersect);
                }
            }
        }

        if (instruction.isOn()) {
            cuboids.add(cuboid);
        }
    }

    public void executeStep() {
        var instruction = instructions.get(currentStep);
        executeStepPart2(instruction);
        if (instruction.getxRange().left >= -50 && instruction.getxRange().right <= 50
                && instruction.getyRange().left >= -50 && instruction.getyRange().right <= 50
                && instruction.getzRange().left >= -50 && instruction.getzRange().right <= 50) {
            for (int x = instruction.getxRange().left; x <= instruction.getxRange().right; x++) {
                for (int y = instruction.getyRange().left; y <= instruction.getyRange().right; y++) {
                    for (int z = instruction.getzRange().left; z <= instruction.getzRange().right; z++) {
                        int newX = 50 + x;
                        int newY = 50 + y;
                        int newZ = 50 + z;
                        if (instruction.isOn()) {
                            reactor[newX][newY][newZ] = 1;
                        } else {
                            reactor[newX][newY][newZ] = 0;
                        }

                    }
                }
            }
        }
        currentStep++;
    }

    long numberOfCubesOn() {
        long counter = 0;
        for (int x = interval.left; x <= interval.right; x++) {
            for (int y = interval.left; y <= interval.right; y++) {
                for (int z = interval.left; z <= interval.right; z++) {
                    if (reactor[x][y][z] == 1) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    long numberOfCubesOnPart2() {
        return cuboids.stream()
                .map(Cuboid::size)
                .reduce(Long::sum)
                .orElse(0L);
    }
}
