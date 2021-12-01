package be.bitbox.adventofcode.y2020.day6;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CustomFormTest {

    @Test
    void testOnePersonGroup() {
        var customForm = new CustomForm(List.of("a"));
        assertThat(customForm.questionsAnswered()).isEqualTo(1);
        assertThat(customForm.uniqueQuestionsAnswered()).isEqualTo(1);

        var customForm2 = new CustomForm(List.of("abcd"));
        assertThat(customForm2.questionsAnswered()).isEqualTo(4);
        assertThat(customForm2.uniqueQuestionsAnswered()).isEqualTo(4);

        var customForm3 = new CustomForm(List.of(""));
        assertThat(customForm3.questionsAnswered()).isEqualTo(0);
        assertThat(customForm3.uniqueQuestionsAnswered()).isEqualTo(0);

        var customForm4 = new CustomForm(List.of("aaad"));
        assertThat(customForm4.questionsAnswered()).isEqualTo(2);
        assertThat(customForm4.uniqueQuestionsAnswered()).isEqualTo(2);
    }

    @Test
    void testMultiplePersonGroup() {
        var customForm = new CustomForm(List.of("a", "b"));
        assertThat(customForm.questionsAnswered()).isEqualTo(2);
        assertThat(customForm.uniqueQuestionsAnswered()).isEqualTo(0);

        var customForm2 = new CustomForm(List.of("abcx", "abcy", "abcz"));
        assertThat(customForm2.questionsAnswered()).isEqualTo(6);
        assertThat(customForm2.uniqueQuestionsAnswered()).isEqualTo(3);

        var customForm3 = new CustomForm(List.of("xy", "yx", "yx", "xxxxyyxxx"));
        assertThat(customForm3.questionsAnswered()).isEqualTo(2);
        assertThat(customForm3.uniqueQuestionsAnswered()).isEqualTo(2);

        var customForm4 = new CustomForm(List.of("a", "b", "c"));
        assertThat(customForm4.questionsAnswered()).isEqualTo(3);
        assertThat(customForm4.uniqueQuestionsAnswered()).isEqualTo(0);
    }

    @Test
    void testMultipleGroup() {
        var customForm = new CustomForm(List.of(
                "abc",
                "   ",
                "a  ",
                "b  ",
                "c  ",
                "   ",
                "ab ",
                "ac ",
                "   ",
                "a  ",
                "a  ",
                "a  ",
                "a  ",
                "   ",
                "b  "));
        assertThat(customForm.questionsAnswered()).isEqualTo(3 + 3 + 3 + 1 + 1);
        assertThat(customForm.uniqueQuestionsAnswered()).isEqualTo(6);
    }

    @Test
    void testMultipleGroupFromFiles() {
        var customForm = new CustomForm(Util.readFileAsStringList("day6.txt"));

        assertThat(customForm.questionsAnswered()).isEqualTo(6662);
        assertThat(customForm.uniqueQuestionsAnswered()).isEqualTo(3382);
    }
}