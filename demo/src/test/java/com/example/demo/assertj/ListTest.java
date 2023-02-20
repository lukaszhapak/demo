package com.example.demo.assertj;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ListTest {

    @Test
    @DisplayName("Test name")
    void testName() {
        // Setup:
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();

        // When:
        firstList.add(2);
        firstList.add(5);
        firstList.add(4);

        secondList.add(4);
        secondList.add(2);
        secondList.add(5);

        // Then:
        assertThat(firstList).hasSameSizeAs(secondList).hasSameElementsAs(secondList);
    }
}
