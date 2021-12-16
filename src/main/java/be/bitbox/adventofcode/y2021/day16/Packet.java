package be.bitbox.adventofcode.y2021.day16;

import java.util.Objects;

abstract class Packet {

    private final int version;
    protected final int typeId;

    protected Packet(int version, int typeId) {
        this.version = version;
        this.typeId = typeId;
    }

    public abstract int getVersionSum();

    public abstract long calculate();

    public int getVersion() {
        return version;
    }

    public int getTypeId() {
        return typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Packet packet = (Packet) o;
        return version == packet.version && typeId == packet.typeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, typeId);
    }

    @Override
    public String toString() {
        return "Packet{" +
                "version=" + version +
                ", typeId=" + typeId +
                '}';
    }
}
