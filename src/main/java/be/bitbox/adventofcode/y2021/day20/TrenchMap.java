package be.bitbox.adventofcode.y2021.day20;

import be.bitbox.adventofcode.Util;

import java.util.Arrays;
import java.util.List;

public class TrenchMap {
    private final String algorithm;
    private char[][] image;
    private char fillCharacter = '.';

    public TrenchMap(List<String> input) {
        algorithm = input.get(0).trim();

        image = new char[input.size() - 2][input.get(2).length()];
        for (int i = 2; i < input.size(); i++) {
            var line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                image[i - 2][j] = line.charAt(j);
            }
        }
    }

    public void enchance(int times) {
        for (int i = 0; i < times; i++) {
            enchance();
        }
    }

    public void enchance() {
        var height = image.length + 2;
        var width = image[0].length + 2;
        char[][] newImage = new char[height][width];
        char[][] enchancedImage = new char[height][width];

        for (int i = 0; i < height; i++) {
            Arrays.fill(newImage[i], fillCharacter);
        }
        for (int i = 0; i < image.length; i++) {
            System.arraycopy(image[i], 0, newImage[i + 1], 1, image[0].length);
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                enchancedImage[i][j] = determineNewChar(i, j, newImage);
            }
        }
        if (algorithm.charAt(0) == '#') {
            if (fillCharacter == '.') {
                fillCharacter = '#';
            } else {
                fillCharacter = '.';
            }
        }
        image = enchancedImage;
    }

    public int numberOfPixelsLit() {
        int counter = 0;
        for (char[] chars : image) {
            for (char aChar : chars) {
                if (aChar == '#') {
                    counter++;
                }
            }
        }
        return counter;
    }

    private char determineNewChar(int i, int j, char[][] newImage) {
        String buffer = String.valueOf(charAt(i - 1, j - 1, newImage)) +
                charAt(i - 1, j, newImage) +
                charAt(i - 1, j + 1, newImage) +
                charAt(i, j - 1, newImage) +
                charAt(i, j, newImage) +
                charAt(i, j + 1, newImage) +
                charAt(i + 1, j - 1, newImage) +
                charAt(i + 1, j, newImage) +
                charAt(i + 1, j + 1, newImage);

        var index = Integer.parseInt(buffer, 2);
        return algorithm.charAt(index);
    }

    private char charAt(int i, int j, char[][] newImage) {
        if (i < 0 || i >= newImage.length) {
            return fillCharacter == '.' ? '0' : '1';
        }
        if (j < 0 || j >= newImage[0].length) {
            return fillCharacter == '.' ? '0' : '1';
        }
        if (newImage[i][j] == '#') {
            return '1';
        } else {
            return '0';
        }
    }

    @Override
    public String toString() {
        return Util.printMatrix(image);
    }
}
