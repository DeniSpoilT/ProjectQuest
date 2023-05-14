package com.komarov.projectnlo.model;

import java.util.Arrays;

public enum Step {
    GREETING, BRIDGE, CAPTAIN, TELL_ME_ABOUT_YOURSELF, FAIL;
    public static Step[] getValuesWithoutLastValue() {
        return Arrays.copyOf(Step.values(), Step.values().length - 1);
    }


}