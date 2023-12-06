package be.bitbox.adventofcode;

import java.util.ArrayList;
import java.util.List;

public abstract class StringUtils {

    public static List<Integer> onlyDigits(String s) {
        return s.chars()
                .filter(CharUtils::isDigit)
                .map(CharUtils::toInt)
                .boxed()
                .toList();
    }

    public static String removeDoubleSpaces(String s) {
        return s.replaceAll(" +", " ");
    }
}
