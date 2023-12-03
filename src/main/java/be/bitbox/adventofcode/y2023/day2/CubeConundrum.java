package be.bitbox.adventofcode.y2023.day2;

import java.util.List;

public class CubeConundrum {

    private final List<Game> games;

    public CubeConundrum(List<String> input) {
        this.games = input.stream()
                .map(Game::new)
                .toList();
    }

    public int sumOfMatchingGames(int red, int green, int blue) {
        return games.stream()
                .filter(game -> game.getRed() <= red && game.getGreen() <= green && game.getBlue() <= blue)
                .map(Game::getId)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    public int powerOfAllGames() {
        return games.stream()
                .map(Game::getPower)
                .reduce(Integer::sum)
                .orElseThrow();
    }
}
