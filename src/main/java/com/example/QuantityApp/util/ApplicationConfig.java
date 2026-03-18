package com.example.QuantityApp.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final Properties props = new Properties();

    static {
        try {
            InputStream input =
                    ApplicationConfig.class.getClassLoader()
                            .getResourceAsStream("application.properties");

            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Cannot load properties");
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}