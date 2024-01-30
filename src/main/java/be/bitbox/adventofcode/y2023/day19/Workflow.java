package be.bitbox.adventofcode.y2023.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Workflow {

    private final String id;
    private final List<Flow> flow;


    public Workflow(String line) {
        var splitByAccolade = line.split("\\{");
        id = splitByAccolade[0];
        flow = new ArrayList<>();
        var predicateStrings = splitByAccolade[1].split(",");
        for (String predicateString : predicateStrings) {
            flow.add(parse(predicateString));
        }
    }

    private Flow parse(String line) {
        var splitted = line.split(":");
        if (splitted.length == 1) {
            return new Flow(machinePart -> true, splitted[0].substring(0, splitted[0].length() - 1), '*', false, 0);
        }
        var pred = splitted[0];
        var workFlow = splitted[1];
        var compareNumber = pred.contains("<") ? -1 : 1;
        var number = Integer.parseInt(pred.substring(2));

        Predicate<MachinePart> predicate = switch (line.charAt(0)) {
            case 'x' -> machinePart -> Integer.compare(machinePart.getX(), number) == compareNumber;
            case 'm' -> machinePart -> Integer.compare(machinePart.getM(), number) == compareNumber;
            case 'a' -> machinePart -> Integer.compare(machinePart.getA(), number) == compareNumber;
            case 's' -> machinePart -> Integer.compare(machinePart.getS(), number) == compareNumber;
            default -> throw new IllegalStateException("Unexpected value: " + line.charAt(0));
        };

        return new Flow(predicate, workFlow, line.charAt(0), pred.contains("<"), number);
    }

    public String getId() {
        return id;
    }

    public List<Flow> getFlow() {
        return flow;
    }

    public record Flow(Predicate<MachinePart> predicate, String workflow, char item, boolean smaller, int number) {

    }
}
