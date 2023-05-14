package com.komarov.projectnlo.utils;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class YAMLLoader implements Loader{
    private Map<String, Map<String, String>> YAMLMap;
    private String path;
    public YAMLLoader(String path) {
        this.path = path;
    }

    public void load() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path)) {
            YAMLMap = yaml.load(inputStream);
        } catch (YAMLException | IOException e) {
            throw new FileNotFoundException("The uploaded YAML file was not found.");
        }
    }

    public Map<String, Map<String, String>> getMap() {
        return YAMLMap;
    }
}
