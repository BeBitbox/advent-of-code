package be.bitbox.adventofcode.y2021.day16;

import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;

public class OperatorPacket extends Packet {
    private final List<Packet> packets;

    protected OperatorPacket(int version, int typeId, List<Packet> packets) {
        super(version, typeId);
        this.packets = packets;
    }

    public List<Packet> getPackets() {
        return packets;
    }

    @Override
    public int getVersionSum() {
        return getVersion() + packets.stream()
                .map(Packet::getVersionSum)
                .reduce(Integer::sum)
                .orElse(0);
    }

    @Override
    public long calculate() {
        return packets.stream()
                .map(Packet::calculate)
                .reduce(getStrategy())
                .orElse(0L);
    }

    private BinaryOperator<Long> getStrategy() {
        switch (typeId) {
            case 0:
                return Long::sum;
            case 1:
                return (aLong, aLong2) -> aLong * aLong2;
            case 2:
                return Math::min;
            case 3:
                return Math::max;
            case 5:
                return (aLong, aLong2) -> (long) (aLong > aLong2 ? 1 : 0);
            case 6:
                return (aLong, aLong2) -> (long) (aLong < aLong2 ? 1 : 0);
            case 7:
                return (aLong, aLong2) -> (long) (aLong.equals(aLong2) ? 1 : 0);
            default:
                throw new IllegalArgumentException();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OperatorPacket that = (OperatorPacket) o;
        return Objects.equals(packets, that.packets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), packets);
    }

    @Override
    public String toString() {
        return "OperatorPacket{" +
                "packets=" + packets +
                ", packet=" + super.toString() +
                '}';
    }
}
