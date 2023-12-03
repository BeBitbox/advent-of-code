package be.bitbox.adventofcode.y2023.day2;

public class Game {

    private final int id;
    private int red;
    private int blue;
    private int green;

    public Game(String input) {
        String[] splittedOnSpace = input.split(" ");
        id = Integer.parseInt(splittedOnSpace[1].substring(0, splittedOnSpace[1].length() - 1));
        red = 0;
        blue = 0;
        green = 0;

        for (int i = 2; i < splittedOnSpace.length; i = i + 2) {
            int color = Integer.parseInt(splittedOnSpace[i]);

            if (splittedOnSpace[i + 1].startsWith("red")) {
                red = Math.max(color, red);
            } else if (splittedOnSpace[i + 1].startsWith("green")) {
                green = Math.max(color, green);
            } else if (splittedOnSpace[i + 1].startsWith("blue")) {
                blue = Math.max(color, blue);
            } else {
                throw new IllegalStateException("unexpected color");
            }
        }
    }

    public int getPower() {
        return red * green * blue;
    }

    public int getId() {
        return id;
    }

    public int getRed() {
        return red;
    }

    public int getBlue() {
        return blue;
    }

    public int getGreen() {
        return green;
    }
}
