package be.bitbox.adventofcode.y2021.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bingo {

    private final List<BingoBoard> bingoBoards;
    private final List<Integer> numbers;

    public Bingo(List<String> input) {
        numbers = Stream.of(input.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        bingoBoards = new ArrayList<>();
        for (int i = 2; i < input.size(); i = i + 6) {
            bingoBoards.add(new BingoBoard(input.subList(i, i + 5)));
        }
    }

    public int play() {
        for (Integer number : numbers) {
            System.out.println("Playing number " + number);
            for (BingoBoard bingoBoard : bingoBoards) {
                bingoBoard.playNumber(number);
            }

            BingoBoard winner = null;
            for (BingoBoard bingoBoard : bingoBoards) {
                if (bingoBoard.hasWon()) {
                    System.out.println("BINGO: " + bingoBoard);
                    winner = bingoBoard;
                }
            }

            if (winner != null) {
                return number * winner.sumOfAllUnmarkeds();
            }
        }
        return -1;
    }

    public int letTheSquidWin() {
        List<BingoBoard> notYetWon = new ArrayList<>(bingoBoards);
        for (Integer number : numbers) {
            System.out.println("Playing number " + number);
            for (BingoBoard bingoBoard : bingoBoards) {
                bingoBoard.playNumber(number);
            }

            for (BingoBoard bingoBoard : bingoBoards) {
                if (bingoBoard.hasWon()) {
                    notYetWon.remove(bingoBoard);
                    if (notYetWon.isEmpty()) {
                        System.out.println("Last Board remaining: " + bingoBoard);
                        return number * bingoBoard.sumOfAllUnmarkeds();
                    }
                }
            }
        }
        return -1;
    }


}
