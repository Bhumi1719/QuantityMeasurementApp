package com.example.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static Properties properties = new Properties();

    static {
        try {
        	InputStream input = ApplicationConfig.class
        	        .getClassLoader()
        	        .getResourceAsStream("application.properties");

        	if (input == null) {
        	    throw new RuntimeException("Config file not found");
        	}

        	properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
