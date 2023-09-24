package com.komarov.projectnlo.model;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

public class FailResolverTest {
    FailResolver failResolver;
    @BeforeEach
    public void init() {
        failResolver = new FailResolver();
    }

    @ParameterizedTest
    @CsvSource({
            "GREETING, <h3>Ты отклонил вызов.</h3><h2>Поражение.</h2>",
            "BRIDGE, <h3>Ты не пошел на переговоры.</h3><h2>Поражение.</h2>",
            "CAPTAIN, <h3>Твою ложь разоблачили.</h3><h2>Поражение.</h2>"
    })
    public void getMessage_shouldGetMessageAppropriateStepState(Step state, String message) {
        failResolver.setState(state);
        String expectedMessage = message;
        String actualMessage = failResolver.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"TELL_ME_ABOUT_YOURSELF", "FAIL"})
    public void getMessage_shouldThrowRuntimeExceptionIfStepIncorrectOrNull(Step state) {
        failResolver.setState(state);
        String expectedMessage = "It's impossible";
        Exception ex = assertThrows(RuntimeException.class, () -> {
            failResolver.getMessage();
        });
        assertEquals(expectedMessage, ex.getMessage());
    }
}
