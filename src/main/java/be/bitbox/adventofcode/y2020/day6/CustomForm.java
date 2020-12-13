package be.bitbox.adventofcode.y2020.day6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomForm {
    private final List<Group> groups;

    public CustomForm(List<String> answerList) {
        groups = new ArrayList<>();

        var group = new Group();
        for (String answerline : answerList) {
            String trimmedAnswer = answerline.trim();
            if (trimmedAnswer.length() == 0) {
                groups.add(group);
                group = new Group();
            } else {
                group.addAnswerOfPerson(trimmedAnswer);
            }
        }
        groups.add(group);
    }

    public int questionsAnswered() {
        return groups.stream()
                .map(Group::answered)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public int uniqueQuestionsAnswered() {
        return groups.stream()
                .map(Group::uniquelyAnswered)
                .reduce(Integer::sum)
                .orElse(0);
    }

    private static class Group {
        private final Set<Character> answers;
        private final Set<Character> allUniqueAnswers;
        private int personsInGroup;

        private Group() {
            answers = new HashSet<>();
            allUniqueAnswers = new HashSet<>();
            personsInGroup = 0;
        }

        private void addAnswerOfPerson(String answer) {
            answer.chars().forEach(c -> answers.add((char) c));

            if (personsInGroup == 0) {
                answer.chars().forEach(c -> allUniqueAnswers.add((char) c));
            } else {
                var newAnswers = new HashSet<Character>();
                for (char c: answer.toCharArray()) {
                    newAnswers.add(c);
                }
                allUniqueAnswers.retainAll(newAnswers);
            }
            personsInGroup++;
        }

        private int answered() {
            return answers.size();
        }

        private int uniquelyAnswered() {
            return allUniqueAnswers.size();
        }
    }
}
