package be.bitbox.adventofcode.y2020.day3;

import java.util.List;

public class ForestMap {
    private final boolean[][] treeMatrix;
    private final int rightRepeatAtIndex;
    private final int maxForestDown;

    public ForestMap(List<String> layout) {
        rightRepeatAtIndex = layout.get(0).length();
        maxForestDown = layout.size();
        treeMatrix = new boolean[rightRepeatAtIndex][maxForestDown];
        for (int y = 0; y < layout.size(); y++) {
            String line = layout.get(y);
            for (int x = 0; x < rightRepeatAtIndex; x++) {
                treeMatrix[x][y] = line.charAt(x) == '#';
            }
        }
    }

    boolean isTree(int right, int down) {
        return treeMatrix[right % rightRepeatAtIndex][down];
    }

    int numberOfTrees(int moveRight, int moveDown) {
        int right = 0, down = 0, trees = 0;

        while (down < maxForestDown) {
            if (isTree(right, down)) {
                trees++;
            }
            right += moveRight;
            down += moveDown;
        }
        return trees;
    }
}
