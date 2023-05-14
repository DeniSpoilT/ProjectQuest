package com.komarov.projectnlo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StepTest {
    @Test
    public void getValuesWithoutLastValue_shouldReturnArrayWithoutLastValue() {
        Step[] expected = {Step.GREETING, Step.BRIDGE, Step.CAPTAIN, Step.TELL_ME_ABOUT_YOURSELF};
        assertArrayEquals(expected, Step.getValuesWithoutLastValue());
    }

    @Test
    public void getValuesWithoutLastValue_shouldReturnArrayWithCorrectLength() {
        int expected = 4;
        assertEquals(expected, Step.getValuesWithoutLastValue().length);
    }

    @Test
    public void getValuesWithoutLastValue_shouldReturnDesiredElementByNumber() {
        Step expectedForIndex3 = Step.TELL_ME_ABOUT_YOURSELF;
        Step expectedForIndex0 = Step.GREETING;
        assertEquals(expectedForIndex3, Step.getValuesWithoutLastValue()[3]);
        assertEquals(expectedForIndex0, Step.getValuesWithoutLastValue()[0]);
    }

    @Test
    public void getValuesWithoutLastValue_shouldThrowsArrayIndexOutOfBoundsExceptionIfIndexIsInvalid() {
        Exception ex = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Step step = Step.getValuesWithoutLastValue()[4];
        });
        String expectedMessage = "Index 4 out of bounds for length 4";
        assertEquals(expectedMessage, ex.getMessage());
    }
}
