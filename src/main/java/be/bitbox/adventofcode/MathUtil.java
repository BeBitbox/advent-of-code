package be.bitbox.adventofcode;

public abstract class MathUtil {

    public static long calculateLowestCommonMultiple(long a, long b) {
        return a * (b / calculateGreatestCommonDivisor(a, b));
    }

    public static long calculateGreatestCommonDivisor(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
