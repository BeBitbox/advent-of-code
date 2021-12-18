package be.bitbox.adventofcode.y2021.day18;


import java.util.LinkedList;

import static java.util.Optional.ofNullable;

public class SnailNumber {

    private final Pair pair;

    public SnailNumber(String input) {
        pair = new Pair(null);

        var currentPair = pair;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '[') {
                var child = new Pair(currentPair);
                if (currentPair.left == null) {
                    currentPair.left = child;
                } else {
                    currentPair.right = child;
                }
                currentPair = child;
            } else if (input.charAt(i) == ']') {
                currentPair = currentPair.parent;
            } else if (input.charAt(i) == ',') {
                var right = new Pair(currentPair.parent);
                currentPair.parent.right = right;
                currentPair = right;
            } else {
                currentPair.value = input.charAt(i) - '0';
            }
        }
    }

    public SnailNumber(Pair parent) {
        this.pair = parent;
    }

    public Pair getPair() {
        return pair;
    }

    public void reduce() {
        boolean doActions = true;

        while (doActions) {
            var needToExplode = this.pair.needToExplode();
            if (needToExplode == null) {
                var needToSplit = this.pair.needToSplit();
                if (needToSplit == null) {
                    doActions = false;
                } else {
                    split(needToSplit);
                }
            } else {
                explode(needToExplode);
            }
        }
    }

    private void split(Pair needToSplit) {
        var left = new Pair(needToSplit);
        left.value = Math.floorDiv(needToSplit.value, 2);
        needToSplit.left = left;
        var right = new Pair(needToSplit);
        right.value = needToSplit.value - left.value;
        needToSplit.right = right;
        needToSplit.value = null;
    }

    private Pair explode(Pair needToExplode) {
        var parent = needToExplode.parent;

        var leafs = new LinkedList<Pair>();
        this.pair.leafs(leafs);
        int leftIndex = -1;
        int rightIndex = -1;
        for (int i = 0; i < leafs.size(); i++) {
            if (leafs.get(i) == needToExplode.left) {
                leftIndex = i - 1;
            }
            if (leafs.get(i) == needToExplode.right) {
                rightIndex = i + 1;
            }
        }
        if (leftIndex > -1) {
            leafs.get(leftIndex).value += needToExplode.left.value;
        }
        if (rightIndex > -1 && rightIndex < leafs.size()) {
            leafs.get(rightIndex).value += needToExplode.right.value;
        }

        if (parent.left == needToExplode) {
            parent.left = new Pair(parent);
            parent.left.value = 0;
        }
        if (parent.right == needToExplode) {
            parent.right = new Pair(parent);
            parent.right.value = 0;
        }
        needToExplode = this.pair.needToExplode();
        return needToExplode;
    }

    @Override
    public String toString() {
        return pair.toString();
    }

    public SnailNumber add(SnailNumber snailNumber) {
        var parent = new Pair(null);
        var left = this.pair.copy(parent);
        var right = snailNumber.pair.copy(parent);
        parent.left = left;
        parent.right = right;
        var newNumber = new SnailNumber(parent);
        newNumber.reduce();
        return newNumber;
    }

    public static class Pair {
        Pair parent;
        Pair left;
        Pair right;
        Integer value;

        public Pair(Pair parent) {
            this.parent = parent;
        }

        int getDepth() {
            if (parent == null) {
                return 0;
            } else return parent.getDepth() + 1;
        }

        Pair needToExplode() {
            if (value != null) {
                return null;
            }
            if (getDepth() >= 4) {
                return this;
            }
            var leftResult = ofNullable(left).map(Pair::needToExplode).orElse(null);
            if (leftResult != null) {
                return leftResult;
            }
            return ofNullable(right).map(Pair::needToExplode).orElse(null);
        }

        Pair needToSplit() {
            if (value != null && value > 9) {
                return this;
            }
            var leftResult = ofNullable(left)
                    .map(Pair::needToSplit)
                    .orElse(null);
            if (leftResult != null) {
                return leftResult;
            }
            return ofNullable(right)
                    .map(Pair::needToSplit)
                    .orElse(null);
        }

        public void leafs(LinkedList<Pair> leafs) {
            if (left != null) {
                left.leafs(leafs);
                right.leafs(leafs);
            }
            if (value != null) {
                leafs.add(this);
            }
        }

        public long magnitude() {
            if (left != null) {
                return 3 * left.magnitude() + 2 * right.magnitude();
            } else {
                return value;
            }
        }

        public Pair copy(Pair copyParent) {
            var copyPair = new Pair(copyParent);
            if (value != null) {
                copyPair.value = Integer.valueOf(value.intValue());
            }
            if (left != null) {
                copyPair.left = left.copy(copyPair);
            }
            if (right != null) {
                copyPair.right = right.copy(copyPair);
            }
            return copyPair;
        }

        @Override
        public String toString() {
            String output = "";
            if (left != null) {
                output = "[" + left + "," + right + "]";
            } else {
                output = String.valueOf(value);
            }
            return output;
        }
    }
}
