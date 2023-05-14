package com.komarov.projectnlo.utils;

import java.io.IOException;
import java.util.Map;

public interface Loader {
    void load() throws IOException;
    Map<String, Map<String, String>> getMap();
}
