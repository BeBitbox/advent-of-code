package be.bitbox.adventofcode.y2021.day13;

import be.bitbox.adventofcode.Tuple;
import be.bitbox.adventofcode.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class TransparentPaper {

    private final Set<Tuple<Integer, Integer>> dots;
    private final List<Tuple<Direction, Integer>> folds;

    public TransparentPaper(List<String> inputList) {
        dots = new HashSet<>();
        folds = new ArrayList<>();

        var iterator = inputList.iterator();
        var line = iterator.next();
        while (line.trim().length() > 0) {
            var split = line.split(",");
            dots.add(new Tuple<>(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
            line = iterator.next();
        }

        while (iterator.hasNext()) {
            line = iterator.next();
            var split = line.split("=");
            var direction = split[0].endsWith("x") ? Direction.X : Direction.Y;
            folds.add(new Tuple<>(direction, Integer.parseInt(split[1])));
        }
    }

    public int foldFirst() {
        System.out.println(Util.printMatrix(dots));

        var temp = new HashSet<>(dots);
        for (Tuple<Direction, Integer> fold : folds) {
            var collect = temp.stream()
                    .map(tuple -> fold.x.getFoldAction().apply(tuple, fold.y))
                    .collect(Collectors.toSet());
            temp = new HashSet<>(collect);
            System.out.println(Util.printMatrix(collect));
        }

        return temp.size();
    }

    enum Direction {
        X(((tuple, foldLine) -> (tuple.x > foldLine) ? new Tuple<>(2 * foldLine - tuple.x, tuple.y) : tuple)),
        Y(((tuple, foldLine) -> (tuple.y > foldLine) ? new Tuple<>(tuple.x, 2 * foldLine - tuple.y) : tuple));

        private final BiFunction<Tuple<Integer, Integer>, Integer, Tuple<Integer, Integer>> foldAction;

        Direction(BiFunction<Tuple<Integer, Integer>, Integer, Tuple<Integer, Integer>> foldAction) {
            this.foldAction = foldAction;
        }

        public BiFunction<Tuple<Integer, Integer>, Integer, Tuple<Integer, Integer>> getFoldAction() {
            return foldAction;
        }
    }

    public Set<Tuple<Integer, Integer>> getDots() {
        return dots;
    }

    public List<Tuple<Direction, Integer>> getFolds() {
        return folds;
    }
}
