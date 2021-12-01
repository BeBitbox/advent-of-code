package be.bitbox.adventofcode.y2020.day1;

import org.junit.jupiter.api.Test;

import java.util.List;

import be.bitbox.adventofcode.Util;
import static org.assertj.core.api.Assertions.assertThat;

class ExpensesAccountTest {

    @Test
    void trivialExpenses_Mode2() {
        assertThat(new ExpensesAccount(List.of(0, 2020)).getOutcomeFrom2()).isEqualTo(0);

        assertThat(new ExpensesAccount(List.of(1, 2019)).getOutcomeFrom2()).isEqualTo(2019);

        assertThat(new ExpensesAccount(List.of(100, 200, 1920)).getOutcomeFrom2()).isEqualTo(100 * 1920);

        assertThat(new ExpensesAccount(List.of(300, 500, 1920, 1520)).getOutcomeFrom2()).isEqualTo(500 * 1520);
    }

    @Test
    void trivialExpenses_Mode3() {
        assertThat(new ExpensesAccount(List.of(0, 0, 2020)).getOutcomeFrom3()).isEqualTo(0);

        assertThat(new ExpensesAccount(List.of(1, 2, 2017)).getOutcomeFrom3()).isEqualTo(1 * 2 * 2017);

        assertThat(new ExpensesAccount(List.of(2, 1, 44, 2017)).getOutcomeFrom3()).isEqualTo(2 * 1 * 2017);

        assertThat(new ExpensesAccount(List.of(33, 979, 54, 366, 1, 44, 675)).getOutcomeFrom3()).isEqualTo(979 * 366 * 675);
    }

    @Test
    void processFile() {
        var integerList = Util.readFileAsIntList("day1.txt");

        assertThat(new ExpensesAccount(integerList).getOutcomeFrom2()).isEqualTo(197451);

        assertThat(new ExpensesAccount(integerList).getOutcomeFrom3()).isEqualTo(138233720);
    }
}