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
        long occurences = password.chars()
                .filter(current -> current == letter)
                .count();
        return index1 <= occurences && occurences <= index2;
    }

    public boolean isValidPart2() {
        return password.charAt(index1 - 1) == letter ^ password.charAt(index2 - 1) == letter;
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
