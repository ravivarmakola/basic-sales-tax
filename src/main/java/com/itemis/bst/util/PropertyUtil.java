package com.itemis.bst.util;

import lombok.extern.java.Log;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

@Log
public class PropertyUtil {
    private static Properties properties = new Properties();

    public static void load(String propsPath) {
        try {
            properties.load(new FileReader(Path.of(propsPath).toFile()));
            log.info("Properties loaded");
        } catch (IOException e) {
            log.severe("Unable to load properties");
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static double getPercent(String key) {
        return Double.parseDouble(properties.getProperty(key)) / 100.0;
    }
}
