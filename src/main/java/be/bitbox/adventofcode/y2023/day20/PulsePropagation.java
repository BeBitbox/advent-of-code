package be.bitbox.adventofcode.y2023.day20;

import be.bitbox.adventofcode.Tuple;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class PulsePropagation {

  private final Map<String, Module> modules;
  private final Map<Module, List<Module>> mapping;

  public PulsePropagation(List<String> inputList) {
    mapping = new HashMap<>();
    modules = new HashMap<>();

    inputList.forEach(line -> {
      var split = line.split("->");
      var rawModuleName = split[0].trim();
      retrieveModule(rawModuleName);
    });

    inputList.forEach(line -> {
      var split = line.split("->");
      var rawModuleName = split[0].trim();
      var moduleName = cleanModuleName(rawModuleName);

      var split2 = split[1].split(",");
      var moduleList = Stream.of(split2)
          .map(name -> modules.get(name.trim()))
          .toList();
      mapping.put(modules.get(moduleName), moduleList);
    });

    modules.values().stream()
        .filter(module -> module instanceof Conjunction)
        .forEach(module -> {
          Conjunction conjunction = (Conjunction) module;
          mapping.forEach((key, value) -> {
            if (value.contains(conjunction)) {
              conjunction.add(key);
            }
          });
        });

    //PART 2
    var rxModule = new FlipFlop("rx");
    modules.put("rx", rxModule);
    mapping.put(rxModule, List.of());
  }

  private void retrieveModule(String rawModuleName) {
    var moduleName = cleanModuleName(rawModuleName);
    Module module;
    if (rawModuleName.startsWith("%")) {
      module = new FlipFlop(moduleName);
    } else if (rawModuleName.startsWith("&")) {
      module = new Conjunction(moduleName);
    } else if (rawModuleName.equals("broadcaster")) {
      module = new Broadcaster();
    } else {
      throw new RuntimeException();
    }
    modules.put(moduleName, module);
  }

  public int pushButton(int times) {
    int lows = 0;
    int highs = 0;
    for (int i = 0; i < times; i++) {
      var integerTuple = pushButton();
      lows += integerTuple.x;
      highs += integerTuple.y;
    }
    return lows * highs;
  }

  private Tuple<Integer, Integer> pushButton() {
    int lows = 0;
    int highs = 0;

    var module = modules.get("broadcaster");
    var actionQueue = new LinkedList<Action>();

    actionQueue.push(new Action(Signal.LOW, null, module));

    while (!actionQueue.isEmpty()) {
      var action = actionQueue.pollFirst();
      if (action.signal == Signal.LOW) {
        lows++;
      }
      if (action.signal == Signal.HIGH) {
        highs++;
      }
      if (action.nextModule == null) {
        continue;
      }
      var signal = action.nextModule.nextSignal(action.signal, action.sourceModule());
      if (signal == Signal.NONE) {
        continue;
      }
      mapping.get(action.nextModule)
          .forEach(nextOne -> actionQueue.add(new Action(signal, action.nextModule, nextOne)));
    }

    return new Tuple<>(lows, highs);
  }

  private static String cleanModuleName(String rawModuleName) {
    return rawModuleName.startsWith("%") || rawModuleName.startsWith("&") ? rawModuleName.substring(1) : rawModuleName;
  }

  private record Action(Signal signal, Module sourceModule, Module nextModule) {

    @Override
    public String toString() {
      return String.format("%s %s-> %s", sourceModule == null ? "button" : sourceModule.getName(), signal, nextModule.getName());
    }
  }

  private interface Module {

    String getName();

    Signal nextSignal(Signal signal, Module sourceModule);
  }

  private static class FlipFlop implements Module {

    private final String name;
    private boolean off = true;

    private FlipFlop(String name) {
      this.name = name;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public Signal nextSignal(Signal signal, Module sourceModule) {
      if (signal == Signal.HIGH) {
        return Signal.NONE;
      }
      if (off) {
        off = false;
        return Signal.HIGH;
      } else {
        off = true;
        return Signal.LOW;
      }
    }
  }

  private enum Signal {
    LOW, HIGH, NONE
  }

  private static class Conjunction implements Module {

    private final String name;
    private final Map<Module, Signal> map;

    private Conjunction(String name) {
      this.name = name;
      map = new HashMap<>();
    }

    private void add(Module module) {
      map.put(module, Signal.LOW);
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public Signal nextSignal(Signal signal, Module sourceModule) {
      map.put(sourceModule, signal);

      if (map.values().stream().allMatch(s -> s == Signal.HIGH)) {
        return Signal.LOW;
      }
      return Signal.HIGH;
    }
  }

  private static class Broadcaster implements Module {

    @Override
    public String getName() {
      return "broadcaster";
    }

    @Override
    public Signal nextSignal(Signal signal, Module sourceModule) {
      return signal;
    }
  }
}
