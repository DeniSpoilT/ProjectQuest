package com.komarov.projectnlo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    @ParameterizedTest
    @CsvSource(value = {
            "question, answer1, answer2"
    }, ignoreLeadingAndTrailingWhitespace = true)
    public void question_constructorShouldSetAllFieldValuesWithThreeParameters(String question, String answer1, String answer2) {
        Question test = new Question(question, answer1, answer2);
        assertEquals("question", test.getQuestion());
        assertEquals("answer1", test.getAnswer1());
        assertEquals("answer2", test.getAnswer2());
    }

    @Test
    public void question_constructorShouldSetAllFieldValuesWithOneParameterUnsetParametersAreEmpty() {
        String question = "question";
        Question test = new Question(question);
        assertEquals("question", test.getQuestion());
        assertEquals("", test.getAnswer1());
        assertEquals("", test.getAnswer2());
    }
}
