package be.bitbox.adventofcode.y2021.day22;

public class Cuboid {
    public int x1;
    public int x2;
    public int y1;
    public int y2;
    public int z1;
    public int z2;
    public boolean on;

    public Cuboid(Instruction instruction) {
        this.x1 = instruction.getxRange().left;
        this.x2 = instruction.getxRange().right;
        this.y1 = instruction.getyRange().left;
        this.y2 = instruction.getyRange().right;
        this.z1 = instruction.getzRange().left;
        this.z2 = instruction.getzRange().right;
        assert x1 <= x2;
        assert y1 <= y2;
        assert z1 <= z2;
        this.on = instruction.isOn();
    }

    public Cuboid() {
    }

    public Cuboid intersect(Cuboid other, boolean sign) {
        if (other.x2 < x1 || other.x1 > x2
                || other.y2 < y1 || other.y1 > y2
                || other.z2 < z1 || other.z1 > z2)
            return null;

        var cuboid = new Cuboid();
        cuboid.x1 = Math.max(x1, other.x1);
        cuboid.x2 = Math.min(x2, other.x2);
        cuboid.y1 = Math.max(y1, other.y1);
        cuboid.y2 = Math.min(y2, other.y2);
        cuboid.z1 = Math.max(z1, other.z1);
        cuboid.z2 = Math.min(z2, other.z2);
        cuboid.on = sign;

        return cuboid;
    }

    public long size() {
        long sign = on ? 1L : -1L;
        return sign * (x2 - x1 + 1) * (y2 - y1 + 1) * (z2 - z1 + 1);
    }
}
