package com.komarov.projectnlo.model;

import com.komarov.projectnlo.utils.Loader;
import com.komarov.projectnlo.utils.YAMLLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestionSupplierTest {
    private QuestionSupplier questionSupplier;
    private YAMLLoader yamlLoader;
    @BeforeEach
    void init() {
        yamlLoader = new YAMLLoader("questions.yaml");
        questionSupplier = new QuestionSupplier(yamlLoader);
    }

    @Test
    void getQuestion_shouldReturnCorrectValueCorrespondingCurrentStep() {
        Question question1 = questionSupplier.getQuestion(Step.GREETING);
        assertEquals("<h3>Ты потерял память.<br>Принять вызов НЛО?</h3>", question1.getQuestion());
        assertEquals("Принять вызов.", question1.getAnswer1());
        assertEquals("Отклонить вызов.", question1.getAnswer2());

        Question question2 = questionSupplier.getQuestion(Step.BRIDGE);
        assertEquals("<h3>Ты принял вызов.<br>Поднимешься на мостик к капитану инопланетян?</h3>", question2.getQuestion());
        assertEquals("Подняться на мостик.", question2.getAnswer1());
        assertEquals("Отказаться подниматься на мостик.", question2.getAnswer2());

        Question question3 = questionSupplier.getQuestion(Step.CAPTAIN);
        assertEquals("<h3>Ты поднялся на мостик.<br>Капитан инопланетян просит тебя рассказать о себе?</h3>", question3.getQuestion());
        assertEquals("Рассказать правду о себе.", question3.getAnswer1());
        assertEquals("Солгать о себе.", question3.getAnswer2());

        Question question4 = questionSupplier.getQuestion(Step.TELL_ME_ABOUT_YOURSELF);
        assertEquals("<h3>Тебя вернули домой.</h3><h2>Ты победил!</h2>", question4.getQuestion());
        assertEquals("", question4.getAnswer1());
        assertEquals("", question4.getAnswer2());
    }

    @Test
    void loadQuestions_ShouldPutParsedValuesInMapQuestions() {
        Map<String, Map<String, String>> map = yamlLoader.getMap();
        assertNotNull(map);
        assertEquals(4, map.size());
        Map<String, String> question1Data = map.get("question1");
        Map<String, String> question2Data = map.get("question2");
        Map<String, String> question3Data = map.get("question3");
        Map<String, String> question4Data = map.get("question4");
        assertNotNull(question1Data);
        assertNotNull(question2Data);
        assertNotNull(question3Data);
        assertNotNull(question4Data);
    }

    @Test
    void loadQuestions_ShouldThrowIOExceptionsIfFileNotFound() {
        Loader loader = new YAMLLoader("nonexistent_file.txt");

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            QuestionSupplier questionSupplier = new QuestionSupplier(loader);
        });
        Assertions.assertEquals("The file is unavailable or the file location is incorrect.", exception.getMessage());
    }
}