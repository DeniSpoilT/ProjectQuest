package com.komarov.projectnlo.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LoaderTest {
    Loader loader;
    @BeforeEach
    public void initLoader() throws IOException {
        loader = new YAMLLoader("test.yaml");
        loader.load();
    }

    @Test
    public void testLoad() throws IOException {
        Loader loader = new YAMLLoader("test.yaml");
        loader.load();
        assertNotNull(loader.getMap());
    }

    @Test
    public void testGetMap() throws IOException {
        Map<String, Map<String, String>> map = loader.getMap();
        assertNotNull(map);
        assertTrue(map.containsKey("test1"));
        assertTrue(map.containsKey("test2"));
        assertEquals("one1", map.get("test1").get("one1"));
        assertEquals("two1", map.get("test1").get("two1"));
        assertEquals("three1", map.get("test1").get("three1"));
        assertEquals("one2", map.get("test2").get("one2"));
        assertEquals("two2", map.get("test2").get("two2"));
        assertEquals("three2", map.get("test2").get("three2"));
    }
}
