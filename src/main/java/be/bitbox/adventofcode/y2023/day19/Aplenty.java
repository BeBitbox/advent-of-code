package be.bitbox.adventofcode.y2023.day19;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Aplenty {

    private final Map<String, Workflow> workflows;
    private final List<MachinePart> machineParts;

    public Aplenty(List<String> inputList) {
        boolean encounteredEmpty = false;
        workflows = new HashMap<>();
        machineParts = new ArrayList<>();

        for (String s : inputList) {
            var line = s.trim();

            if (line.isEmpty()) {
                encounteredEmpty = true;
            } else {
                if (encounteredEmpty) {
                    machineParts.add(new MachinePart(line));
                } else {
                    var workflow = new Workflow(line);
                    workflows.put(workflow.getId(), workflow);
                }
            }
        }
    }

    public int acceptedParts() {
        AtomicInteger sum = new AtomicInteger();
        machineParts.forEach(machinePart -> {
            if (accepted(machinePart)) {
                sum.addAndGet(machinePart.getSum());
            }
        });
        return sum.get();
    }

    private boolean accepted(MachinePart machinePart) {
        String currentWorkFlow = "in";

        while (!"A".equals(currentWorkFlow) && !"R".equals(currentWorkFlow)) {
            var workflow = workflows.get(currentWorkFlow);
            var flow = workflow.getFlow().stream()
                    .filter(pr -> pr.predicate().test(machinePart))
                    .findFirst()
                    .orElseThrow();
            currentWorkFlow = flow.workflow();
        }

        return currentWorkFlow.equals("A");
    }

    public long getRanges() {
        Stack<Configuration> configurations = new Stack<>();
        Set<Configuration> accepted = new HashSet<>();

        configurations.add(new Configuration("in", 1, 4000, 1, 4000, 1, 4000, 1, 4000));

        while (!configurations.isEmpty()) {
            var c = configurations.pop();

            if (c.workflow.equals("A")) {
                accepted.add(c);
                continue;
            }
            if (c.workflow.equals("R")) {
                continue;
            }
            var workflow = workflows.get(c.workflow);

            for (Workflow.Flow flow : workflow.getFlow()) {
                boolean match = true;
                if (flow.item() == '*') {
                    configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, c.minM, c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                }
                if (flow.item() == 'x') {
                    if (flow.smaller() && c.minX < flow.number() && c.maxX > flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, flow.number() - 1, c.minM, c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                        configurations.add(new Configuration(c.workflow, flow.number(), c.maxA, c.minM, c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                    } else if (flow.smaller() && c.minX < flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, c.minM, c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                    } else if (!flow.smaller() && c.maxX > flow.number() && c.minX <= flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), flow.number() + 1, c.maxX, c.minM, c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                        configurations.add(new Configuration(c.workflow, c.minX, flow.number(), c.minM, c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                    } else if (!flow.smaller() && c.maxX > flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, c.minM, c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                    } else {
                        match = false;
                    }
                }
                if (flow.item() == 'm') {
                    if (flow.smaller() && c.minM < flow.number() && c.maxM > flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, c.minM, flow.number() - 1, c.minA, c.maxA, c.minS, c.maxS));
                        configurations.add(new Configuration(c.workflow, c.minX, c.maxX, flow.number(), c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                    } else if (flow.smaller() && c.minM < flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, c.minM, c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                    } else if (!flow.smaller() && c.maxM > flow.number() && c.minM <= flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, flow.number() + 1, c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                        configurations.add(new Configuration(c.workflow, c.minX, c.maxX, c.minM, flow.number(), c.minA, c.maxA, c.minS, c.maxS));
                    } else if (!flow.smaller() && c.maxM > flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, c.minM, c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                    } else {
                        match = false;
                    }
                }
                if (flow.item() == 'a') {
                    if (flow.smaller() && c.minA < flow.number() && c.maxA > flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, c.minM, c.maxM, c.minA, flow.number() - 1, c.minS, c.maxS));
                        configurations.add(new Configuration(c.workflow, c.minX, c.maxX, c.minM, c.maxM, flow.number(), c.maxA, c.minS, c.maxS));
                    } else if (flow.smaller() && c.minA < flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, c.minM, c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                    } else if (!flow.smaller() && c.maxA > flow.number() && c.minA <= flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, c.minM, c.maxM, flow.number() + 1, c.maxA, c.minS, c.maxS));
                        configurations.add(new Configuration(c.workflow, c.minX, c.maxX, c.minM, c.maxM, c.minA, flow.number(), c.minS, c.maxS));
                    } else if (!flow.smaller() && c.maxA > flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, c.minM, c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                    } else {
                        match = false;
                    }
                }

                if (flow.item() == 's') {
                    if (flow.smaller() && c.minS < flow.number() && c.maxS > flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, c.minM, c.maxM, c.minA, c.maxA, c.minS, flow.number() - 1));
                        configurations.add(new Configuration(c.workflow, c.minX, c.maxX, c.minM, c.maxM, c.minA, c.maxA, flow.number(), c.maxS));
                    } else if (flow.smaller() && c.minS < flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, c.minM, c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                    } else if (!flow.smaller() && c.maxS > flow.number() && c.minS <= flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, c.minM, c.maxM, c.minA, c.maxA, flow.number() + 1, c.maxS));
                        configurations.add(new Configuration(c.workflow, c.minX, c.maxX, c.minM, c.maxM, c.minA, c.maxA, c.minS, flow.number()));
                    } else if (!flow.smaller() && c.maxS > flow.number()) {
                        configurations.add(new Configuration(flow.workflow(), c.minX, c.maxX, c.minM, c.maxM, c.minA, c.maxA, c.minS, c.maxS));
                    } else {
                        match = false;
                    }
                }

                if (match) {
                    break;
                }
            }
        }

        return accepted.stream()
                .map(configuration -> (long) (configuration.maxX - configuration.minX + 1) * (configuration.maxM - configuration.minM + 1) * (configuration.maxA - configuration.minA + 1) * (configuration.maxS - configuration.minS + 1))
                .reduce(Long::sum)
                .orElseThrow();
    }

    public record Configuration(String workflow, int minX, int maxX, int minM, int maxM, int minA, int maxA, int minS,
                                int maxS) {

    }
}
