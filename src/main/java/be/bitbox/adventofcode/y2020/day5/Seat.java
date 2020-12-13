package be.bitbox.adventofcode.y2020.day5;

public class Seat {
    private final int row;
    private final int column;

    public Seat(String line) {
        if (line.startsWith("BBB")) {
            System.out.println("aa");
        }
        String rowString = line.substring(0, 7)
                .replaceAll("F", "0")
                .replaceAll("B", "1");
        row = Integer.parseInt(rowString, 2);

        String colomString = line.substring(7)
                .replaceAll("L", "0")
                .replaceAll("R", "1");
        column = Integer.parseInt(colomString, 2);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSeatId() {
        return row * 8 + column;
    }

}
