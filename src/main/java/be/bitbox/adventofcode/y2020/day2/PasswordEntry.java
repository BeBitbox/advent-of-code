package be.bitbox.adventofcode.y2020.day2;

public class PasswordEntry {
    private final int index1, index2;
    private final char letter;
    private final String password;

    public PasswordEntry(String line) {
        String[] parts = line.split(" ");
        String[] indexes = parts[0].split("-");
        index1 = Integer.parseInt(indexes[0]);
        index2 = Integer.parseInt(indexes[1]);
        letter = parts[1].charAt(0);
        password = parts[2];
    }

    public boolean isValid() {
        return password.contains(String.valueOf(letter));
    }

    public int getIndex1() {
        return index1;
    }

    public int getIndex2() {
        return index2;
    }

    public char getLetter() {
        return letter;
    }

    public String getPassword() {
        return password;
    }
}
