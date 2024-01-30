package be.bitbox.adventofcode.y2023.day19;

public class MachinePart {

    private final int x;
    private final int m;
    private final int a;
    private final int s;

    public MachinePart(String line) {
        x = split(line, 'x');
        m = split(line, 'm');
        a = split(line, 'a');
        s = split(line, 's');
    }

    public MachinePart(int x, int m, int a, int s) {
        this.x = x;
        this.m = m;
        this.a = a;
        this.s = s;
    }

    private int split(String line, char character) {
        var split = line.split(character + "=");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < split[1].length(); i++) {
            var c = split[1].charAt(i);
            if (c == '}' || c == ',') {
                break;
            }
            builder.append(c);
        }
        return Integer.parseInt(builder.toString());
    }

    public int getSum() {
        return x + m + a + s;
    }

    public int getX() {
        return x;
    }

    public int getM() {
        return m;
    }

    public int getA() {
        return a;
    }

    public int getS() {
        return s;
    }
}
