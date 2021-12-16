package be.bitbox.adventofcode.y2021.day16;

import java.util.Objects;

public class LiteralPacket extends Packet {
    private final long value;

    protected LiteralPacket(int version, long value) {
        super(version, 4);
        this.value = value;
    }

    @Override
    public int getVersionSum() {
        return getVersion();
    }

    @Override
    public long calculate() {
        return value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LiteralPacket that = (LiteralPacket) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public String toString() {
        return "LiteralPacket{" +
                "value=" + value +
                ", packet=" + super.toString() +
                '}';
    }
}
