package be.bitbox.adventofcode.y2020.day20;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Photo {
    private final Set<Tile> tiles;
    private final int size;

    public Photo(List<String> input) {
        tiles = new HashSet<>();
        Tile tile = null;
        size = (int) Math.round(Math.pow(input.size() / 12, 0.5));

        for (String inputLine : input) {
            if (inputLine.startsWith("Tile")) {
                var tileId = Integer.parseInt(inputLine.substring(5, inputLine.indexOf(':')));
                tile = new Tile(tileId);
                tiles.add(tile);
            } else if (inputLine.startsWith(".") || inputLine.startsWith("#")) {
                tile.addLine(inputLine);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Set<Tile> getTiles() {
        return tiles;
    }

    public long multiplyCornerTiles() {
        Set<Tile> twoNeighbours = findCornerTiles();

        return twoNeighbours.stream()
                .map(tile -> (long) tile.getId())
                .reduce((aLong, aLong2) -> aLong * aLong2)
                .orElse(0L);
    }

    private Set<Tile> findCornerTiles() {
        Set<Tile> twoNeighbours = new HashSet<>();

        for (Tile tile : tiles) {
            if (tiles.stream()
                    .filter(t -> !tile.equals(t))
                    .filter(t -> t.getBorders().stream().anyMatch(border -> tile.getBorders().contains(border)))
                    .count() == 2) {
                twoNeighbours.add(tile);
            }
        }
        return twoNeighbours;
    }

    public void findTroubledSea() {
        Tile[][] orderedTiles = new Tile[size][size];

        Tile topLeft = findCornerTiles().iterator().next();
        Tile current = topLeft;
        int row = 0;
        for (int i = 0; i < size; i++) {
            orderedTiles[row][i] = current;
        }
    }
}
