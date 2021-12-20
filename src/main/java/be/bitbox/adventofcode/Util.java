package be.bitbox.adventofcode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Comparator;
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

    public static String readFileAsString(String filename) {
        return String.join("", readFileAsStringList(filename));
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

    public static String printMatrix(short[][] matrix) {
        StringBuilder logging = new StringBuilder(System.lineSeparator());
        for (short[] shorts : matrix) {
            for (short aShort : shorts) {
                logging.append(String.format("%2d", aShort));
            }
            logging.append(System.lineSeparator());
        }
        return logging.toString();
    }

    public static String printMatrix(char[][] matrix) {
        StringBuilder logging = new StringBuilder(System.lineSeparator());
        for (char[] charArray : matrix) {
            for (char c : charArray) {
                logging.append(String.format("%2c", c));
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

    public static String printMatrix(Collection<Tuple<Integer, Integer>> tupleList) {
        int maxX = tupleList.stream().max(Comparator.comparingInt(tuple -> tuple.x)).orElseThrow().x;
        int maxY = tupleList.stream().max(Comparator.comparingInt(tuple -> tuple.y)).orElseThrow().y;

        StringBuilder logging = new StringBuilder(System.lineSeparator());
        for (int i = 0; i <= maxY; i++) {
            for (int j = 0; j <= maxX; j++) {
                if (tupleList.contains(new Tuple<>(j, i))) {
                    logging.append(" #");
                } else {
                    logging.append(" .");
                }
            }
            logging.append(System.lineSeparator());
        }
        return logging.toString();
    }
}
