package be.bitbox.adventofcode.y2020;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Util {

    public static List<Integer> readFileAsIntList(String filename) {
        try {
            return Files.lines(Path.of(Util.class.getClassLoader().getResource(filename).toURI()))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
