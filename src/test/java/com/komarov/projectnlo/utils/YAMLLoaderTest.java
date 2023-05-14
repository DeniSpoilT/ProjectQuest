package com.komarov.projectnlo.utils;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class YAMLLoaderTest {
    Loader loader;

    @Test
    public void getDataFromYAML_shouldThrowFileNotFoundExceptionIfFileNotExist() {
        loader = new YAMLLoader("non-existent path");
        Exception ex = assertThrows(FileNotFoundException.class, () ->
                loader.load());
        String expectedMessage = "The uploaded YAML file was not found.";
        assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    public void getDataFromYAML_shouldSetExpectedValuesToYAMLMapFieldIfValidFileExists() {
        Map<String, Map<String, String>> expectedMap = new HashMap<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("one1", "one1");
        map1.put("two1", "two1");
        map1.put("three1", "three1");
        Map<String, String> map2 = new HashMap<>();
        map2.put("one2", "one2");
        map2.put("two2", "two2");
        map2.put("three2", "three2");
        Map<String, String> map3 = new HashMap<>();
        map3.put("one3", "one3");
        map3.put("two3", "two3");
        map3.put("three3", "three3");
        Map<String, String> map4 = new HashMap<>();
        map4.put("one4", "one4");
        map4.put("two4", "two4");
        map4.put("three4", "three4");
        expectedMap.put("test1", map1);
        expectedMap.put("test2", map2);
        expectedMap.put("test3", map3);
        expectedMap.put("test4", map4);
        loader = new YAMLLoader("test.yaml");
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Map<String, String>> actualMap = loader.getMap();
        assertEquals(expectedMap, actualMap);
    }
}
