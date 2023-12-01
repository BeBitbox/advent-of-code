package be.bitbox.adventofcode;

import java.util.ArrayList;
import java.util.List;

public abstract class StringUtils {

    public static List<Integer> onlyDigits(String s) {
        return s.chars()
                .filter(c -> c >= '0' && c <= '9')
                .map(c -> c - '0')
                .boxed()
                .toList();
    }
}
