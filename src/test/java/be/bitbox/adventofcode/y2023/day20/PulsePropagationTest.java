package be.bitbox.adventofcode.y2023.day20;

import static org.assertj.core.api.Assertions.assertThat;

import be.bitbox.adventofcode.Util;
import java.util.List;
import org.junit.jupiter.api.Test;

class PulsePropagationTest {

  @Test
  void testExamplePartOne() {
    var input = List.of(
        "broadcaster -> a, b, c",
        "%a -> b",
        "%b -> c",
        "%c -> inv",
        "&inv -> a");
    var pulsePropagation = new PulsePropagation(input);

    var result = pulsePropagation.pushButton(1000);
    assertThat(result).isEqualTo(32000000);

  }

  @Test
  void testExample2PartOne() {
    var input = List.of(
        "broadcaster -> a",
        "%a -> inv, con",
        "&inv -> b",
        "%b -> con",
        "&con -> output");
    var pulsePropagation = new PulsePropagation(input);

    var result = pulsePropagation.pushButton(1000);
    assertThat(result).isEqualTo(11687500);
  }

  @Test
  void testPartOne() {
    var input = Util.readFileAsStringList("23_day20.txt");
    var pulsePropagation = new PulsePropagation(input);

    var result = pulsePropagation.pushButton(1000);
    assertThat(result).isEqualTo(817896682);
  }
}