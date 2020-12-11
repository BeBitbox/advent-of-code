package be.bitbox.adventofcode.y2020.day1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ExpensesAccount {
    private final List<Integer> expenses;

    public ExpensesAccount(List<Integer> expenses) {
        this.expenses = expenses;
    }

    int getOutcomeFrom2() {
        var visited = new HashSet<>();
        var solution = 0;
        var iteratorExpenses = expenses.iterator();
        while (solution == 0 && iteratorExpenses.hasNext()) {
            int number = iteratorExpenses.next();
            int wanted = 2020 - number;
            if (visited.contains(wanted)) {
                solution = wanted * number;
            } else {
                visited.add(number);
            }
        }
        return solution;
    }

    public int getOutcomeFrom3() {
        var visited = new HashSet<Integer>();
        var sumOfTwos = new HashMap<Integer, Integer>();
        var solution = 0;
        var iteratorExpenses = expenses.iterator();
        while (solution == 0 && iteratorExpenses.hasNext()) {
            int current = iteratorExpenses.next();
            int wanted = 2020 - current;
            if (sumOfTwos.get(wanted) != null) {
                solution = sumOfTwos.get(wanted) * current;
            } else {
                visited.forEach(previous -> {
                    int sum = previous + current;
                    sumOfTwos.computeIfAbsent(sum, k -> previous * current);
                });
                visited.add(current);
            }
        }
        return solution;
    }
}
