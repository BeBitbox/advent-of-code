package be.bitbox.adventofcode.y2021.day22;

import be.bitbox.adventofcode.Tuple;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cuboid {
    public int x1;
    public int x2;
    public int y1;
    public int y2;
    public int z1;
    public int z2;

    public LinkedList<Cuboid> detached = new LinkedList<>();

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
    }

    public Cuboid() {
    }

    public Cuboid intersect(Cuboid other) {
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

        var hereExcluded = detached.stream().filter(detach -> detach.contains(cuboid)).findAny();
        if (hereExcluded.isPresent()) {
            return null;
        }

        return cuboid;
    }

    public Cuboid minus(Cuboid intersect) {
        var cuboid = new Cuboid();
        if (x1 == intersect.x1) {
            cuboid.x1 = intersect.x2 + 1;
        } else {
            cuboid.x1 = x1;
        }
        if (x2 == intersect.x2) {
            cuboid.x2 = intersect.x1 - 1;
        } else {
            cuboid.x2 = x2;
        }
        if (y1 == intersect.y1) {
            cuboid.y1 = intersect.y2 + 1;
        } else {
            cuboid.y1 = y1;
        }
        if (y2 == intersect.y2) {
            cuboid.y2 = intersect.y1 - 1;
        } else {
            cuboid.y2 = y2;
        }
        if (z1 == intersect.z1) {
            cuboid.z1 = intersect.z2 + 1;
        } else {
            cuboid.z1 = z1;
        }
        if (z2 == intersect.z2) {
            cuboid.z2 = intersect.z1 - 1;
        } else {
            cuboid.z2 = z2;
        }
        return cuboid;
    }

    public boolean contains(Cuboid other) {
        return x1 <= other.x1
                && x2 >= other.x2
                && y1 <= other.y1
                && y2 >= other.y2
                && z1 <= other.z1
                && z2 >= other.z2;
    }

    public long size() {
        var baseSum = (long) (x2 - x1 + 1) * (y2 - y1 + 1) * (z2 - z1 + 1);
        var detachedSum = detached.stream().map(Cuboid::size).reduce(Long::sum).orElse(0L);
        return baseSum - detachedSum;
    }

    public void addDetached(Cuboid other) {
        detached.add(other);

        var tuple = detachedOverlap();
        while (tuple != null) {
            var cuboidX = detached.get(tuple.x);
            var cuboidY = detached.get(tuple.y);
            if (cuboidX.contains(cuboidY)) {
                detached.remove(cuboidY);
            } else if (cuboidY.contains(cuboidX)) {
                detached.remove(cuboidX);
            } else {
                var intersect = cuboidX.intersect(cuboidY);
                var minus = cuboidY.minus(intersect);
                if (cuboidX.intersect(minus) != null) {
                    System.out.println("aaaaaaaaa");
                }
                detached.add(minus);
                detached.remove(cuboidY);
            }
            tuple = detachedOverlap();
        }
    }

    public Tuple<Integer, Integer> detachedOverlap() {
        for (int i = 0; i < detached.size(); i++) {
            for (int j = i + 1; j < detached.size(); j++) {
                if (detached.get(i).intersect(detached.get(j)) != null) {
                    return new Tuple<>(i, j);
                }
            }
        }
        return null;
    }
}
