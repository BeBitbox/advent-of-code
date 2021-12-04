package be.bitbox.adventofcode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Util {

    public static List<Integer> readFileAsIntList(String filename) {
        return readFileAsList(filename, Integer::parseInt);
    }

    public static List<String> readFileAsStringList(String filename) {
        return readFileAsList(filename, Function.identity());
    }

    public static List<Long> readFileAsLongList(String filename) {
        return readFileAsList(filename, Long::parseLong);
    }

    private static <T> List<T> readFileAsList(String filename, Function<String, T> function) {
        try {
            return Files.lines(Path.of(Util.class.getClassLoader().getResource(filename).toURI()))
                    .map(function)
                    .collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String printMatrix(int[][] matrix) {
        StringBuilder logging = new StringBuilder(System.lineSeparator());
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                logging.append(String.format("%4d", anInt));
            }
            logging.append(System.lineSeparator());
        }
        return logging.toString();
    }

    public static String printMatrix(boolean [][] matrix) {
        StringBuilder logging = new StringBuilder(System.lineSeparator());
        for (boolean[] booleans : matrix) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    logging.append(" 1 ");
                } else {
                    logging.append(" 0 ");
                }
            }
            logging.append(System.lineSeparator());
        }
        return logging.toString();
    }
}
