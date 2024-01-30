package be.bitbox.adventofcode.y2023.day19;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkflowTest {

    @Test
    void parsing() {
        var workflow = new Workflow("in{s<1351:px,qqz}");

        Assertions.assertThat(workflow.getId()).isEqualTo("in");
        Assertions.assertThat(workflow.getFlow()).hasSize(2);
        Assertions.assertThat(workflow.getFlow().get(0).workflow()).isEqualTo("px");
        Assertions.assertThat(workflow.getFlow().get(1).workflow()).isEqualTo("qqz");
    }
}